package org.gepardec.structured_logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.gepardec.structured_logging.level.*;

import java.lang.annotation.Annotation;
import java.util.SortedSet;
import java.util.TreeSet;

public class LogSystem {
    private final TreeSet<TimedMessage> messages = new TreeSet<>();

    private final Logger delegate;

    public LogSystem(Logger delegate) {
        this.delegate = delegate;
    }

    public <T> T tell(T message) {
        synchronized (messages) {
            messages.add(new TimedMessage(message));
        }
        return message;
    }

    public void flush() {
        SortedSet<TimedMessage> toFlush;
        synchronized (messages) {
            toFlush = new TreeSet<>(messages);
            messages.clear();
        }
        toFlush.forEach(message -> log(message));
    }

    private void log(TimedMessage message) {
        Level logLevel = getLogLevel(message.getMessage());
        delegate.log(logLevel, new ObjectMessage(message));
    }

    private Level getLogLevel(Object message) {
        if(message instanceof Throwable) {
            return Level.ERROR;
        }
        for (Annotation a : message.getClass().getDeclaredAnnotations()) {
            if (a instanceof LogFatal) {
                return Level.FATAL;
            }
            if (a instanceof LogError) {
                return Level.ERROR;
            }
            if (a instanceof LogWarn) {
                return Level.WARN;
            }
            if (a instanceof LogInfo) {
                return Level.INFO;
            }
            if (a instanceof LogDebug) {
                return Level.DEBUG;
            }
            if (a instanceof LogTrace) {
                return Level.TRACE;
            }
        }
        return Level.DEBUG;
    }

    private static class TimedMessage implements Comparable<TimedMessage> {
        private final long time = System.currentTimeMillis();
        private final Object message;

        private TimedMessage(Object message) {
            this.message = message;
        }

        public long getTime() {
            return time;
        }

        public Object getMessage() {
            return message;
        }

        @Override
        public int compareTo(TimedMessage o) {
            int result = Math.toIntExact(time - o.time);
            if (result == 0) {
                return System.identityHashCode(message) - System.identityHashCode(o.message);
            }
            return result;
        }
    }
}
