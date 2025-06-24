package ru.nicholas.eventgenerator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nicholas.eventgenerator.model.Event;
import ru.nicholas.eventgenerator.repository.EventRepository;
import ru.nicholas.eventgenerator.util.EventType;

import java.time.LocalDateTime;

/**
 * Сервисная логика по манипулированию событиями и их отправке процессору.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneratorService {

    private final EventRepository eventRepository;

    /**
     * Генерирует новое событие указанного типа, сохраняет его, отправляет на обработку процессором.
     *
     * @param type тип создаваемого события {@link EventType}
     * @return созданное событие с заполненными полями
     */
    public Event generateAndSendEvent(EventType type) {
        Event newEvent = new Event();
        newEvent.setType(type);
        newEvent.setTime(LocalDateTime.now());

        log.info("Generated event: {}", newEvent);

        return eventRepository.save(newEvent);

    }



}
