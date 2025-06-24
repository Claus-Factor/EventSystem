package ru.nicholas.event_processor.converter;

import org.mapstruct.Mapper;
import ru.nicholas.event_processor.model.entity.Event;
import ru.nicholas.event_processor.model.dto.EventRequestDto;

@Mapper(componentModel = "spring")
public interface EventConverter {
    Event toEntity(EventRequestDto eventDto);
}
