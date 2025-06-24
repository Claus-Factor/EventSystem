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
import ru.nicholas.event_processor.model.Incident;
import ru.nicholas.event_processor.service.ProcessorService;

@RestController
@Tag(name = "Контроллер для обработки событий")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

/*    @PostMapping
    public void processEvent(@RequestBody Event event) {
        processorService.processEvent(event);
    }*/

    @GetMapping("/incidents")
    @Operation(summary = "Получить список инцидентов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получено страница инцидентов"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры сортировки")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Page<Incident> getIncidents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "time") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        return processorService.getIncidents(page, size, sortBy, direction);
    }

}
