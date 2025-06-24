package ru.nicholas.event_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nicholas.event_processor.model.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

}
