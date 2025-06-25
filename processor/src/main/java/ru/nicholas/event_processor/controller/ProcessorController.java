package ru.nicholas.event_processor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nicholas.event_processor.model.dto.EventRequestDto;
import ru.nicholas.event_processor.model.dto.IncidentResponseDto;
import ru.nicholas.event_processor.service.ProcessorService;

/**
 * Контроллер для обработки событий и управления инцидентами.
 * <p>Предоставляет API для:</p>
 * <p>- Приема событий</p>
 * <p>- Получения инцидентов с пагинацией и сортировкой</p>
 */
@RestController
@Tag(name = "Контроллер для обработки событий")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

    /**
     * Обрабатывает входящее событие
     *
     * @param eventRequestDto событие для обработки
     */
    @PostMapping("/events")
    public void processEvent(@RequestBody EventRequestDto eventRequestDto) {
        processorService.processEvent(eventRequestDto);
    }

    /**
     * Возвращает страницу инцидентов с возможностью сортировки
     *
     * @param page номер страницы (начиная с 0)
     * @param size количество элементов на странице
     * @param sortBy поле для сортировки (по умолчанию "time")
     * @param direction направление сортировки (по умолчанию DESC)
     * @return страница с инцидентами
     */
    @GetMapping("/incidents")
    @Operation(summary = "Получить список инцидентов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получено страница инцидентов"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры сортировки")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Page<IncidentResponseDto> getIncidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "time") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        return processorService.getIncidents(page, size, sortBy, direction);
    }

}
