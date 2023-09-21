package org.gepardec.slog;

import jakarta.enterprise.context.Dependent;
import org.apache.logging.log4j.message.ObjectMessage;

import java.util.Map;

@Dependent
public class CalcLogObject {
//    private String context = ThreadContext.getContext().entrySet().stream().map(e->e.getKey() + ":" + e.getValue()).collect(Collectors.joining());
//    private String context = "Hallo";

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

    public Map<String, Object> getContext() {
        return JTC.getContext();
    }

//    public void setContext(String context) {
////        this.context = context;
//    }

    public static ObjectMessage of(CalcRequest request, CalcResponse response, String method){
        return new ObjectMessage(new CalcLogObject(request, response, method));
    }
}
