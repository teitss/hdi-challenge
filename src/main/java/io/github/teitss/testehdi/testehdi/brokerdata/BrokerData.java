package io.github.teitss.testehdi.testehdi.brokerdata;

public class BrokerData {
    
    private Integer code;
    private Boolean active;
    private Float commissionRate;
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Float getCommissionRate() {
        return commissionRate;
    }
    public void setCommissionRate(Float commissionRate) {
        this.commissionRate = commissionRate;
    }
    @Override
    public String toString() {
        return "BrokerData [active=" + active + ", code=" + code + ", commissionRate=" + commissionRate + "]";
    }

    

}
