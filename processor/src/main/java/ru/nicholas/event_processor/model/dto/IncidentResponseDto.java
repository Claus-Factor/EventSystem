package ru.nicholas.event_processor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nicholas.event_processor.model.entity.Event;
import ru.nicholas.event_processor.util.IncidentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentResponseDto {
    private UUID id;
    private IncidentType type;
    private LocalDateTime time;
    private List<Event> events;
}
