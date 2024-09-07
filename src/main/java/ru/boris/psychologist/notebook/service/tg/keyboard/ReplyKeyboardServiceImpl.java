package ru.boris.psychologist.notebook.service.tg.keyboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boris.psychologist.notebook.api.service.tg.keyboard.ReplyKeyboardService;
import ru.boris.psychologist.notebook.dto.domain.callback.PossibleCallTime;
import ru.boris.psychologist.notebook.dto.tg.ReplyKeyboardDto;

import java.util.List;

/**
 * Реализация сервиса для создания кнопок у сообщения.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyKeyboardServiceImpl implements ReplyKeyboardService {

    @Override
    public ReplyKeyboardDto getKeyboardForMakeAnAppointment() {
        ReplyKeyboardDto replyMarkup = new ReplyKeyboardDto();
        replyMarkup.setText("Записаться на приём");
        replyMarkup.setCallbackData("make_an_appointment");
        return replyMarkup;
    }

    @Override
    public List<ReplyKeyboardDto> getKeyboardForPossibleCallTime() {
        ReplyKeyboardDto fromNineToTwelve = new ReplyKeyboardDto();
        fromNineToTwelve.setText(PossibleCallTime.FROM_NINE_TO_TWELVE.getTime());
        fromNineToTwelve.setCallbackData(PossibleCallTime.FROM_NINE_TO_TWELVE.getName());

        ReplyKeyboardDto fromTwelveToThree = new ReplyKeyboardDto();
        fromTwelveToThree.setText(PossibleCallTime.FROM_TWELVE_TO_THREE.getTime());
        fromTwelveToThree.setCallbackData(PossibleCallTime.FROM_TWELVE_TO_THREE.getName());

        ReplyKeyboardDto fromThreeToSix = new ReplyKeyboardDto();
        fromThreeToSix.setText(PossibleCallTime.FROM_THREE_TO_SIX.getTime());
        fromThreeToSix.setCallbackData(PossibleCallTime.FROM_THREE_TO_SIX.getName());

        return List.of(fromNineToTwelve, fromTwelveToThree, fromThreeToSix);
    }
}
