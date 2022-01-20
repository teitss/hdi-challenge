package io.github.teitss.testehdi.testehdi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.teitss.testehdi.testehdi.domain.Broker;
import io.github.teitss.testehdi.testehdi.domain.BrokerData;
import io.github.teitss.testehdi.testehdi.domain.BrokerStatus;

@RestController
public class BrokerStatusController {

    private WebClient webClient;
    private static final String BASE_URL = "https://607732991ed0ae0017d6a9b0.mockapi.io/insurance/v1";
    private static final String TYPE = "application/json";

    public BrokerStatusController() {
        this.webClient = WebClient.builder()
        .baseUrl(BASE_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, TYPE)
        .build();
    }

    //Would be great to implement a cache to prevent a DoS attack.
    //TODO Refactor with async code
    @GetMapping("/status/{document}")
    ResponseEntity get(@PathVariable String document) {

        Broker broker = webClient.get().uri("/broker/" + document).retrieve().bodyToMono(Broker.class).block();
        BrokerData brokerData = webClient.get().uri("/brokerData/" + broker.getCode()).retrieve().bodyToMono(BrokerData.class).block();

        if (!brokerData.getActive()) {
            return ResponseEntity.status(HttpStatus.OK).body("O corretor não está ativo.");
        }

        return ResponseEntity.ok(new BrokerStatus(broker,brokerData)); 
        
    }
    
}
