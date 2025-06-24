package ru.nicholas.eventgenerator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nicholas.eventgenerator.model.dto.EventResponseDto;
import ru.nicholas.eventgenerator.service.GeneratorService;
import ru.nicholas.eventgenerator.util.EventType;

/**
 * Контроллер для управления событиями системы.
 * <p>
 * Предоставляет API для генерации событий различных типов.
 * </p>
 */
@RestController
@Tag(name = "Контроллер для управления событиями")
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class GeneratorController {

    private final GeneratorService generatorService;

    /**
     * Генерирует новое событие указанного типа.
     * <p>
     * Созданное событие автоматически отправляется на обработку в систему.
     * </p>
     *
     * @param type тип создаваемого события {@link EventType}
     * @return созданное событие с заполненными полями
     */
    @PostMapping("/generate")
    @Operation(summary = "Создание события", description = """
            Создает новое событие определенного типа.
            После создания событие автоматически отправляется на обработку.
            Доступные типы событий:
            - TYPE1: Событие типа 1
            - TYPE2: Событие типа 2
            - TYPE3: Событие типа 3
            - TYPE4: Событие типа 4
    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно зарегистрировано новое событие")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDto generateEvent(@RequestParam EventType type) {
        return generatorService.generateAndSendEvent(type);
    }

}
