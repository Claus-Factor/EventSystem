package ru.nicholas.eventgenerator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nicholas.eventgenerator.util.EventType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSendDto {
    private EventType type;
    private LocalDateTime time;
}
