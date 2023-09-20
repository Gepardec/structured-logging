package org.gepardec.structured_logging;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CalcLogObject {
    private CalcRequest request;
    private CalcResponse response;
    private String method;

    public CalcLogObject() {

    }
    public CalcLogObject(CalcRequest request, CalcResponse response, String method) {
        this.request = request;
        this.response = response;
        this.method = method;
    }

    public CalcRequest getRequest() {
        return request;
    }

    public void setRequest(CalcRequest request) {
        this.request = request;
    }

    public CalcResponse getResponse() {
        return response;
    }

    public void setResponse(CalcResponse response) {
        this.response = response;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
