package io.github.teitss.hdichallenge.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.teitss.hdichallenge.domain.ActiveDTO;
import io.github.teitss.hdichallenge.domain.Broker;
import io.github.teitss.hdichallenge.domain.BrokerData;
import io.github.teitss.hdichallenge.domain.BrokerStatus;
import io.github.teitss.hdichallenge.error.InactiveBrokerException;
import reactor.core.publisher.Mono;

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
    ResponseEntity<?> get(@PathVariable String document) {

        Broker broker = webClient.get()
            .uri("/broker/" + document)
            .retrieve()
            .bodyToMono(Broker.class)
            .block();

        BrokerData brokerData = webClient.get()
            .uri("/brokerData/" + broker.getCode())
            .retrieve()
            .bodyToMono(BrokerData.class).block();

        if (!brokerData.getActive()) {
            throw new InactiveBrokerException();
        }

        return ResponseEntity.ok(new BrokerStatus(broker,brokerData)); 
        
    }

    @PutMapping("/status/{document}")
    Mono<BrokerData> put(@PathVariable String document, @RequestBody ActiveDTO active) {
        
        Broker broker = webClient.get().uri("/broker/" + document).retrieve().bodyToMono(Broker.class).block();
        return webClient.put()
            .uri("/brokerData/" + broker.getCode())
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just("{\"active\":" + active.getActive() + "}"), String.class)
            .retrieve()
            .bodyToMono(BrokerData.class);

    }
    
}
