package io.github.teitss.hdichallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class BrokerStatus {

    @JsonUnwrapped
    private Broker broker;
    @JsonUnwrapped
    @JsonIgnoreProperties({"code"})
    private BrokerData brokerData;
    
    public BrokerStatus(Broker broker, BrokerData brokerData) {
        this.broker = broker;
        this.brokerData = brokerData;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public BrokerData getBrokerData() {
        return brokerData;
    }

    public void setBrokerData(BrokerData brokerData) {
        this.brokerData = brokerData;
    }

    @Override
    public String toString() {
        return "BrokerStatus [broker=" + broker + ", brokerData=" + brokerData + "]";
    }
    
    
}
