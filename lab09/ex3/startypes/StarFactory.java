package ex3.startypes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StarFactory {
    public static final Map<String, CommonProperties> cache = new HashMap<>();

    private StarFactory() {
    }

    public static CommonProperties getCommonProps(int size, Color color) {
        String key = size + color.toString();
        cache.computeIfAbsent(key, k -> new CommonProperties(size, color));
        return cache.get(key);
    }
}
