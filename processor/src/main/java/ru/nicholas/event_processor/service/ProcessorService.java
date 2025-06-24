package ru.nicholas.event_processor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nicholas.event_processor.converter.EventConverter;
import ru.nicholas.event_processor.model.entity.Event;
import ru.nicholas.event_processor.model.entity.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.nicholas.event_processor.model.dto.EventRequestDto;
import ru.nicholas.event_processor.repository.EventRepository;
import ru.nicholas.event_processor.repository.IncidentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessorService {

    private final IncidentRepository incidentRepository;
    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    private final TemplateService templateService;

    /**
     * Обрабатывает входящее событие согласно шаблонам
     */
    @Transactional
    public void processEvent(EventRequestDto eventDto) {
        log.info("Processing event: {}", eventDto);

        Event event = eventConverter.toEntity(eventDto);

        eventRepository.save(event);

        templateService.processEventByTemplates(event);
    }

    /**
     * Получает список инцидентов с пагинацией и сортировкой
     *
     * @param page номер страницы (начинается с 0)
     * @param size количество элементов на странице
     * @param sortBy поле для сортировки (например, "time")
     * @param direction направление сортировки (ASC/DESC)
     * @return страница с инцидентами
     */
    public Page<Incident> getIncidents(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return incidentRepository.findAll(pageable);
    }

}
