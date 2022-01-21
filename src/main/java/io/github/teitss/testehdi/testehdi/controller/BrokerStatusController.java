package io.github.teitss.testehdi.testehdi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.teitss.testehdi.testehdi.domain.Broker;
import io.github.teitss.testehdi.testehdi.domain.BrokerData;
import io.github.teitss.testehdi.testehdi.domain.BrokerStatus;
import io.github.teitss.testehdi.testehdi.domain.InactiveError;

@RestController
public class BrokerStatusController {

    private WebClient webClient;
    private static final String BASE_URL = "https://607732991ed0ae0017d6a9b0.mockapi.io/insurance/v1";

    public BrokerStatusController() {
        this.webClient = WebClient.builder()
        .baseUrl(BASE_URL)
        .build();
    }

    @GetMapping("/status/{document}")
    ResponseEntity get(@PathVariable String document) {

        Broker broker = webClient.get().uri("/broker/" + document).retrieve().bodyToMono(Broker.class).block();
        BrokerData brokerData = webClient.get().uri("/brokerData/" + broker.getCode()).retrieve().bodyToMono(BrokerData.class).block();

        if (!brokerData.getActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new InactiveError());
        }

        return ResponseEntity.ok(new BrokerStatus(broker,brokerData)); 
        
    }
    
}
