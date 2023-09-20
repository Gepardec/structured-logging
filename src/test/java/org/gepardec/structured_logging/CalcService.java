package org.gepardec.structured_logging;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;

@RequestScoped
public class CalcService {
    // @Inject
    // private RequestContext context;

    @Inject
    private CalcLogObject logObject;

    static Logger logger = LogManager.getLogger(CalcService.class);

    public CalcResponse calc(CalcRequest request) {
//        CalcLogObject logObject = new CalcLogObject();
        // logObject.setContext(context)
        logObject.setRequest(request);

        Operation operation = request.getOperation();
        int operand1 = request.getOperand1();
        int operand2 = request.getOperand2();

        int result = getOperation(operation).performCalculation(operand1, operand2);

        CalcResponse response = new CalcResponse(result);

        logObject.setResponse(response);

        logger.info(new ObjectMessage(logObject));
        return response;
    }

    private CalcOperation getOperation(Operation op){
        return new SumOperation();
    }
}
