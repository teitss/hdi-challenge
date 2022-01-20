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

@RestController
public class BrokerStatusController {

    //Would be great to implement a cache to prevent a DoS attack.
    //TODO Refactor with async code
    @GetMapping("/status/{document}")
    ResponseEntity get(@PathVariable String document) {
        
        WebClient brokerClient = WebClient.create("https://607732991ed0ae0017d6a9b0.mockapi.io/insurance/v1/broker/" + document);
        Broker broker = brokerClient.get().retrieve().bodyToMono(Broker.class).block();
        WebClient brokerDataClient = WebClient.create("https://607732991ed0ae0017d6a9b0.mockapi.io/insurance/v1/brokerData/" + broker.getCode());
        BrokerData brokerData = brokerDataClient.get().retrieve().bodyToMono(BrokerData.class).block();

        if (!brokerData.getActive()) {
            return ResponseEntity.status(HttpStatus.OK).body("O corretor não está ativo.");
        }

        return ResponseEntity.ok(new BrokerStatus(broker,brokerData)); 
        
    }
    
}
