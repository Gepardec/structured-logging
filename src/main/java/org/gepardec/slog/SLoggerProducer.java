package org.gepardec.slog;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;

@ApplicationScoped
public class SLoggerProducer {

    @Produces
    @Dependent
    @Default
    public SLogger produceLogSystem(InjectionPoint ip) {
        Class<?> targetType = SLogger.class; // init with default type

        if (ip != null) {
            if (ip.getMember() != null) {
                targetType = ip.getMember().getDeclaringClass();
            } else if (ip.getBean() != null) {
                targetType = ip.getBean().getBeanClass();
            }
        }

        return new SLogger(LogManager.getLogger(targetType));
    }
    
    public SLogger produceLogSystem(Class clazz) {
        return new SLogger(LogManager.getLogger(clazz));
    }

}
