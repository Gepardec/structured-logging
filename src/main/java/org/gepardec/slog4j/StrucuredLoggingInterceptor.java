package org.gepardec.slog4j;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Field;

@Interceptor
@StructuredLogged
public class StrucuredLoggingInterceptor {

    public StrucuredLoggingInterceptor() {
    }

    @AroundInvoke
    public Object log(InvocationContext invocationContext) throws Exception {
        System.out.println("Interceptor begin");

        Object result = invocationContext.proceed();
        Object target = invocationContext.getTarget();
        Class currentType = target.getClass();
        do {
            for (Field field : currentType.getDeclaredFields()) {
                if (field.getType().equals(LogSystem.class)) {
                    if(!field.canAccess(target)){
                        field.setAccessible(true);
                    }
                    LogSystem log = (LogSystem) field.get(target);
                    log.flush();
                }
            }
            currentType = currentType.getSuperclass();
        }while(!currentType.equals(Object.class));
        return result;
    }
}
