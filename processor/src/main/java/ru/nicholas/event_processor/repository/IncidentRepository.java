package ru.nicholas.event_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nicholas.event_processor.model.entity.Event;
import ru.nicholas.event_processor.model.entity.Incident;
import ru.nicholas.event_processor.util.IncidentType;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    boolean existsByEventsContainingAndType(Event event, IncidentType incidentType);
}
