package org.gepardec.slog4j;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;

@ApplicationScoped
public class LogSystemProducer {

    @Produces
    @Dependent
    @Default
    public LogSystem produceLogSystem(InjectionPoint ip) {
        Class<?> targetType = LogSystem.class; // init with default type

        if (ip != null) {
            if (ip.getMember() != null) {
                targetType = ip.getMember().getDeclaringClass();
            } else if (ip.getBean() != null) {
                targetType = ip.getBean().getBeanClass();
            }
        }

        return new LogSystem(LogManager.getLogger(targetType));
    }
}
