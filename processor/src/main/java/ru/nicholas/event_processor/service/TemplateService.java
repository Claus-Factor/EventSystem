package ru.nicholas.event_processor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nicholas.event_processor.model.entity.Event;
import ru.nicholas.event_processor.model.entity.Incident;
import ru.nicholas.event_processor.repository.EventRepository;
import ru.nicholas.event_processor.repository.IncidentRepository;
import ru.nicholas.event_processor.util.EventType;
import ru.nicholas.event_processor.util.IncidentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для обработки событий по шаблонам
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateService {

    private final EventRepository eventRepository;
    private final IncidentRepository incidentRepository;

    /**
     * Обрабатывает событие по всем доступным шаблонам
     */
    @Transactional
    public void processEventByTemplates(Event event) {
        log.debug("Processing event by templates: {}", event);
        
        // Сначала проверяем составной шаблон (имеет приоритет)
        if (processCompositeTemplate(event)) {
            log.info("Event processed by composite template");
            return;
        }
        
        // Затем простой шаблон
        if (processSimpleTemplate(event)) {
            log.info("Event processed by simple template");
        }
    }

    /**
     * Шаблон №1 (простой): если получено событие с Event.Type = 1 то создать инцидент 1 типа
     */
    private boolean processSimpleTemplate(Event event) {
        if (event.getType() == EventType.TYPE1) {
            createIncident(IncidentType.TYPE1, List.of(event));
            return true;
        }
        return false;
    }

    /**
     * Шаблон №2 (составной): если получено событие с Event.Type = 2, 
     * а затем в течении 20 секунд получено событие с Event.Type = 1, 
     * то создать инцидент с Incident.Type = 2
     */
    private boolean processCompositeTemplate(Event currentEvent) {
        if (currentEvent.getType() == EventType.TYPE1) {
            // Ищем событие TYPE2 за последние 20 секунд
            LocalDateTime twentySecondsAgo = currentEvent.getTime().minusSeconds(20);
            
            Optional<Event> type2Event = eventRepository.findFirstByTypeAndTimeBetweenOrderByTimeDesc(
                    EventType.TYPE2, 
                    twentySecondsAgo, 
                    currentEvent.getTime()
            );
            
            if (type2Event.isPresent()) {
                // Проверяем, что событие TYPE2 еще не использовано в составном инциденте
                boolean alreadyUsed = incidentRepository.existsByEventsContainingAndType(
                        type2Event.get(), 
                        IncidentType.TYPE2
                );
                
                if (!alreadyUsed) {
                    createIncident(IncidentType.TYPE2, List.of(type2Event.get(), currentEvent));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Создает инцидент указанного типа на основе списка событий
     */
    private void createIncident(IncidentType type, List<Event> events) {
        Incident incident = Incident.builder()
                .type(type)
                .time(LocalDateTime.now())
                .events(events)
                .build();
        
        incidentRepository.save(incident);
        log.info("Created incident: {} based on events: {}", incident.getId(), 
                events.stream().map(Event::getId).toList());
    }
}