package ru.nicholas.eventgenerator.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.nicholas.eventgenerator.util.EventType;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * Автоматически генерирует события.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BackgroundEventService {

    private final GeneratorService generatorService;
    private final Random random = new Random();

    /**
     * Автоматически генерирует события каждые 100-2000 мс
     */
    @Scheduled(fixedRate = 100)
    @Async
    public void generateRandomEvent() {
        CompletableFuture.runAsync(() -> {
            try {
                // Случайная задержка от 0 до 2000 мс
                Thread.sleep(random.nextInt(2000));

                // Генерируем случайный тип события
                EventType[] eventTypes = EventType.values();
                EventType randomType = eventTypes[random.nextInt(eventTypes.length)];

                generatorService.generateAndSendEvent(randomType);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Background event generation interrupted", e);
            } catch (Exception e) {
                log.error("Error during background event generation", e);
            }
        });
    }
}
