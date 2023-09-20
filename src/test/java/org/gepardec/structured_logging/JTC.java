package org.gepardec.structured_logging;

import java.util.HashMap;
import java.util.Map;

public class JTC {
    private static ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, Object value) {
        context.get().put(key, value);
    }

    public static void remove(String key) {
        context.get().remove(key);
    }

    public static Map<String,Object> getContext(){return context.get();}
}
