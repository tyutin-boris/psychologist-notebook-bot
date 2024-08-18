package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.client.UserDtoToClientDtoMapper;
import ru.boris.psychologist.notebook.api.service.tg.ClientService;
import ru.boris.psychologist.notebook.api.service.tg.PhoneNumberService;
import ru.boris.psychologist.notebook.dto.bot.ClientDto;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.MessageEntityDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для сохранения номера телефона пациента.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final ClientService clientService;

    private final UserDtoToClientDtoMapper userDtoToClientDtoMapper;

    @Override
    public Optional<ResponseDto> saveNumber(UpdateDto dto, MessageEntityDto messageDto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Старт сохранения номера телефона. id: {}", updateId);

        Optional<MessageDto> message = Optional.ofNullable(dto.getMessage());
        Long chatId = message
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));


        if (messageDto == null) {
            log.error("Не удалось сохранить номер телефона, информация о нем не получена. updateId: {}", updateId);
            return errorResponse(chatId);
        }

        String messageType = messageDto.getType();
        if (!"phone_number".equals(messageType)) {
            log.error("Не удалось сохранить номер телефона, в полученном сообщении нет номера. updateId: {}", updateId);
            return errorResponse(chatId);
        }

        String phoneNumber = messageDto.getText();
        Optional<ClientDto> clientDto = message.map(MessageDto::getFrom)
                .map(userDtoToClientDtoMapper::toDto);

        if (clientDto.isEmpty()) {
            log.error("Не удалось сохранить номер телефона, нет информации о пациенте. updateId: {}", updateId);
            return errorResponse(chatId);
        }

        boolean phoneIsUpdated = clientService.savePhoneNumber(phoneNumber, clientDto.get());
        if (phoneIsUpdated) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setChatId(chatId);
            responseDto.setText("Ваш номер телефона успешно сохранён");

            log.debug("Конец сохранения номера телефона. id: {}", updateId);
            return Optional.of(responseDto);
        }

        return errorResponse(chatId);
    }

    private Optional<ResponseDto> errorResponse(Long chatId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setText("Не удалось сохранить номер телефона. Напишите <ТУТ ДОЛЖНА БЫТЬ ССЫЛКА>");
        responseDto.setChatId(chatId);

        return Optional.of(responseDto);
    }
}
