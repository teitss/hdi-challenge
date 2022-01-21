package io.github.teitss.testehdi.testehdi.domain;

public class InactiveError {

    private String code;
    private String message;

    public InactiveError() {
        this.code = "400";
        this.message = "Não foi possível retornar os dados solicitados, pois o corretor está inativo.";
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
}
