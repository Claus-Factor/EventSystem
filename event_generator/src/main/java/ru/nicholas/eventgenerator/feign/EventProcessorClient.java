package ru.nicholas.eventgenerator.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "event_processor", url = "${generator.processor.url}")
public interface EventProcessorClient {


}
