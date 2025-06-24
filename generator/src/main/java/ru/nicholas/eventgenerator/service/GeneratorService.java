package ru.nicholas.eventgenerator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.nicholas.eventgenerator.converter.EventConverter;
import ru.nicholas.eventgenerator.feign.ProcessorClient;
import ru.nicholas.eventgenerator.model.dto.EventResponseDto;
import ru.nicholas.eventgenerator.model.entity.Event;
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
    private final EventConverter eventConverter;
    private final ProcessorClient processorClient;

    @Value("${generator.processor.url}")
    private String processorUrl;

    /**
     * Генерирует новое событие указанного типа, сохраняет его, отправляет на обработку процессором.
     *
     * @param type тип создаваемого события {@link EventType}
     * @return созданное событие с заполненными полями
     */
    public EventResponseDto generateAndSendEvent(EventType type) {
        Event newEvent = new Event();
        newEvent.setType(type);
        newEvent.setTime(LocalDateTime.now());

        log.info("Generated event: {}", newEvent);
        eventRepository.save(newEvent);

        processorClient.processEvent(eventConverter.toDto(newEvent));

        return eventConverter.toDto(newEvent);

    }



}
