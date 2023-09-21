package org.gepardec.structured_logging;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@StructuredLogged
public class StrucuredLoggingInterceptor {

    public StrucuredLoggingInterceptor() {
    }

    @AroundInvoke
    public Object log(InvocationContext invocationContext) throws Exception {
        System.out.println("Interceptor begin");

        return invocationContext.proceed();
    }
}
