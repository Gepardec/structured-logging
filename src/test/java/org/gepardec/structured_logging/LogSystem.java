package org.gepardec.structured_logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;

import java.time.LocalDateTime;
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
        toFlush.forEach(message -> delegate.info(new ObjectMessage(message)));
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
