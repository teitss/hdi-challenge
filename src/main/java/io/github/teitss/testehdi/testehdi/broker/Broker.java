package io.github.teitss.testehdi.testehdi.broker;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Broker {

    private String name;
    private String document;
    private Integer code;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Calendar createDate;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Calendar getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }
    @Override
    public String toString() {
        return "Broker [code=" + code + ", createDate=" + createDate + ", document=" + document + ", name=" + name
                + "]";
    }

    
    
}
