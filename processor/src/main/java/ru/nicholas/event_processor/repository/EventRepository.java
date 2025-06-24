package ru.nicholas.event_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nicholas.event_processor.model.entity.Event;
import ru.nicholas.event_processor.util.EventType;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Optional<Event> findFirstByTypeAndTimeBetweenOrderByTimeDesc(EventType eventType, LocalDateTime twentySecondsAgo, LocalDateTime time);
}
