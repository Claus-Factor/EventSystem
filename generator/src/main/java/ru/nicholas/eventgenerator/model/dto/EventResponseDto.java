package ru.nicholas.eventgenerator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nicholas.eventgenerator.util.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private UUID id;
    private EventType type;
    private LocalDateTime time;
}
