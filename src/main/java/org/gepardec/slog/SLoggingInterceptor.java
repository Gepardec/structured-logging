package org.gepardec.slog;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Field;

@Interceptor
@SLogged
public class SLoggingInterceptor {

    public SLoggingInterceptor() {
    }

    @AroundInvoke
    public Object log(InvocationContext invocationContext) throws Exception {
        System.out.println("Interceptor begin");

        Object result = invocationContext.proceed();
        Object target = invocationContext.getTarget();
        Class currentType = target.getClass();
        do {
            for (Field field : currentType.getDeclaredFields()) {
                if (field.getType().equals(SLogger.class)) {
                    if(!field.canAccess(target)){
                        field.setAccessible(true);
                    }
                    SLogger log = (SLogger) field.get(target);
                    log.flush();
                }
            }
            currentType = currentType.getSuperclass();
        }while(!currentType.equals(Object.class));
        return result;
    }
}
