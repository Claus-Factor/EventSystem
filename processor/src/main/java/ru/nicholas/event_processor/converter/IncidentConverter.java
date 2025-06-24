package ru.nicholas.event_processor.converter;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nicholas.event_processor.model.dto.IncidentResponseDto;
import ru.nicholas.event_processor.model.entity.Incident;

@Mapper(componentModel = "spring")
public interface IncidentConverter {
    IncidentResponseDto toDto(Incident incident);

    default Page<IncidentResponseDto> toDtoPage(Page<Incident> incidents) {
        return incidents.map(this::toDto);
    }
}
