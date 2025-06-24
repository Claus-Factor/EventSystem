package ru.nicholas.event_processor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nicholas.event_processor.util.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDto {
    private EventType type;
    private LocalDateTime time;
}
