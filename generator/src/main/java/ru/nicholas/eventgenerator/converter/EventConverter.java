package ru.nicholas.eventgenerator.converter;

import org.mapstruct.Mapper;
import ru.nicholas.eventgenerator.model.dto.EventResponseDto;
import ru.nicholas.eventgenerator.model.dto.EventSendDto;
import ru.nicholas.eventgenerator.model.entity.Event;

@Mapper(componentModel = "spring")
public interface EventConverter {
    Event toEntity(EventResponseDto eventResponseDto);
    EventResponseDto toEventResponseDto(Event event);
    EventSendDto toEventSendDto(Event event);
}
