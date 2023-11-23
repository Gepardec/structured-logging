package org.gepardec.slog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.gepardec.slog.level.*;

import java.lang.annotation.Annotation;
import java.util.SortedSet;
import java.util.TreeSet;

public class SLogger {
    private final TreeSet<TimedMessage> messages = new TreeSet<>();

    private final Logger delegate;

    public SLogger(Logger delegate) {
        this.delegate = delegate;
    }

    public <T> T add(T message) {
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
            if (a instanceof SLogFatal) {
                return Level.FATAL;
            }
            if (a instanceof SLogError) {
                return Level.ERROR;
            }
            if (a instanceof SLogWarn) {
                return Level.WARN;
            }
            if (a instanceof SLogInfo) {
                return Level.INFO;
            }
            if (a instanceof SLogDebug) {
                return Level.DEBUG;
            }
            if (a instanceof SLogTrace) {
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

    public void info(String string) {
        delegate.info(string);
    }
}
