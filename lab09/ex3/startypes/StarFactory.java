package ex3.startypes;

import java.util.HashMap;
import java.util.Map;

public class StarFactory {
    private static final Map<Character, StarType> cache = new HashMap<>();

    static {
        cache.put('A', new AStar());
        cache.put('B', new BStar());
        cache.put('F', new FStar());
        cache.put('G', new GStar());
        cache.put('K', new KStar());
        cache.put('M', new MStar());
        cache.put('O', new OStar());
    }

    private StarFactory() {
    }

    public static StarType getStar(char type) {
        return cache.get(type);
    }
}
