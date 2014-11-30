package main.utilities;

/**
 * @author aeyoa
 */
public class Stopwatch {

    private static long time = 0;

    public static void start() {
        time = System.currentTimeMillis();
    }

    public static long stop() {
        return System.currentTimeMillis() - time;
    }
}
