package ru.nicholas.event_processor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nicholas.event_processor.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.nicholas.event_processor.repository.IncidentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessorService {

    private final IncidentRepository incidentRepository;

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
