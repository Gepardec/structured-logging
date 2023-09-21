package org.gepardec.structured_logging;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gepardec.structured_logging.level.LogWarn;

import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class CalcService2 {
    private static AtomicInteger instanceNo = new AtomicInteger(0);

    private final int iNo = instanceNo.incrementAndGet();

    public int getiNo() {
        return iNo;
    }

    public String getName() {
        return "calculation service";
    }

    static Logger logger = LogManager.getLogger(CalcService2.class);

    @Inject
    private LogSystem log;

    @StructuredLogged
    public CalcResponse calc(CalcRequest request) {
        CalcLogMessage logObj = log.tell(new CalcLogMessage()); // proxy the log object

        logObj.setRequest(request);


        Operation operation = request.getOperation();
        int operand1 = request.getOperand1();
        int operand2 = request.getOperand2();

        try {
            int result = getOperation(operation).performCalculation(operand1, operand2);
            CalcResponse response = new CalcResponse(result);
            logObj.setResponse(response);
            return response;
//        }catch(RuntimeException ex){
//            logObj.setError(ex);
        } finally {
            log.flush();
        }
    }

    private CalcOperation getOperation(Operation op) {
        return new SumOperation();
    }

    @LogWarn
    public static class CalcLogMessage {
        //        @LogInfo
        private CalcRequest request;
        //        @LogInfo
        private CalcResponse response;
        //        @LogDebug
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
