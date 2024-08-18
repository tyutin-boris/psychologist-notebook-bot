package ru.boris.psychologist.notebook.service.tg;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.mapper.client.UserDtoToClientDtoMapper;
import ru.boris.psychologist.notebook.api.service.tg.ClientService;
import ru.boris.psychologist.notebook.api.service.tg.DescriptionService;
import ru.boris.psychologist.notebook.dto.bot.ClientDto;
import ru.boris.psychologist.notebook.dto.tg.ChatDto;
import ru.boris.psychologist.notebook.dto.tg.MessageDto;
import ru.boris.psychologist.notebook.dto.tg.ResponseDto;
import ru.boris.psychologist.notebook.dto.tg.UpdateDto;

import java.util.Optional;

/**
 * Реализация сервиса для.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DescriptionServiceImpl implements DescriptionService {

    private final ClientService clientService;

    private final UserDtoToClientDtoMapper userDtoToClientDtoMapper;

    @Override
    public Optional<ResponseDto> saveDescription(UpdateDto dto) {
        Integer updateId = dto.getUpdateId();
        log.debug("Старт сохранения описания проблемы. id: {}", updateId);

        Optional<MessageDto> message = Optional.ofNullable(dto.getMessage());
        Long chatId = message
                .map(MessageDto::getChat)
                .map(ChatDto::getId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Не удалось определить идентификатор чата. messageId: %s", updateId)));


        Optional<String> description = message.map(MessageDto::getText);
        if (description.isEmpty()) {
            log.error("Не удалось добавить описание проблемы, нет описания. updateId: {}", updateId);
            return errorResponse(chatId);
        }

        Optional<ClientDto> clientDto = message.map(MessageDto::getFrom)
                .map(userDtoToClientDtoMapper::toDto);


        if (clientDto.isEmpty()) {
            log.error("Не удалось добавить описание проблемы, нет информации о пациенте. updateId: {}", updateId);
            return errorResponse(chatId);
        }

        boolean descriptionSaved = clientService.saveDescription(description.get(), clientDto.get());
        if(descriptionSaved) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setChatId(chatId);
            responseDto.setText("Описание вышей проблемы успешно добавлено");

            log.debug("Конец сохранения описания проблемы. id: {}", updateId);
            return Optional.of(responseDto);
        }

        return errorResponse(chatId);
    }

    private Optional<ResponseDto> errorResponse(Long chatId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setText("Не удалось добавить описание вашей проблемы. Напишите <ТУТ ДОЛЖНА БЫТЬ ССЫЛКА>");
        responseDto.setChatId(chatId);

        return Optional.of(responseDto);
    }
}
