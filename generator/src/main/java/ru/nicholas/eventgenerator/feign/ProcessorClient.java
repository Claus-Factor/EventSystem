package ru.nicholas.eventgenerator.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.nicholas.eventgenerator.model.dto.EventSendDto;

@FeignClient(name = "processor", url = "${generator.processor.url}")
public interface ProcessorClient {

    @PostMapping("api/events")
    void processEvent(@RequestBody EventSendDto eventSendDto);

}
