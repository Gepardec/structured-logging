package org.gepardec.slog;

import jakarta.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class CalcService {
    private static AtomicInteger instanceNo = new AtomicInteger(0);

    private final int iNo = instanceNo.incrementAndGet();

    public int getiNo() {
        return iNo;
    }

    public String getName() {
        return "calculation service";
    }
//    @Inject
//    private CalcLogObject logObject;

    static Logger logger = LogManager.getLogger(CalcService.class);

    public CalcResponse calc(CalcRequest request) {
        MyLogObj logObj = new MyLogObj();

//        JTC.put("service", getClass().getName());
        JTC.put(getClass().getName(),logObj);

        try {
            logObj.setInstanceNo(iNo);
            logObj.setRequest(request);
//        logObject.setRequest(request);

            Operation operation = request.getOperation();
            int operand1 = request.getOperand1();
            int operand2 = request.getOperand2();

            int result = getOperation(operation).performCalculation(operand1, operand2);

            CalcResponse response = new CalcResponse(result);

            logObj.setResponse(response);
//        logObject.setResponse(response);

            logger.info(CalcLogObject.of(request, response, "calc"));
            return response;
        }finally{
            JTC.remove("service");
            JTC.remove("logObj");
        }
    }

    private CalcOperation getOperation(Operation op) {
        return new SumOperation();
    }

    public class MyLogObj {
        private CalcRequest request;
        private CalcResponse response;
        private int instanceNo;

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

        public int getInstanceNo() {
            return instanceNo;
        }

        public void setInstanceNo(int instanceNo) {
            this.instanceNo = instanceNo;
        }
    }
}
