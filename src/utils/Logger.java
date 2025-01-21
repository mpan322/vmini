package utils;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Logger implements ILogger {
    private static PrintWriter wtr;
    private static OutputStream os;
    private static Logger instance;
    private static LogLevel level;

    /**
     * Get the logger instance.
     *
     * @return the logger instance.
     */
    public static ILogger getInstance() {
        if (Logger.instance == null) {
            Logger.instance = new Logger();
            Logger.wtr = new PrintWriter(System.out);
        }
        return Logger.instance;
    }


    @Override
    public void setOutputStream(OutputStream os) {
        Logger.os = os;
        Logger.wtr = new PrintWriter(os);
    }

    @Override
    public void setLogLevel(LogLevel level) {
        Logger.level = level;
    }

    @Override
    public void log(String message) {
        Logger.wtr.println(message);
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.compareTo(Logger.level) < 0) {
            Logger.wtr.println(message);
        }
    }

    @Override
    public void logf(String format, Object... params) {
        Logger.wtr.printf(format, params);
    }

    @Override
    public void logf(LogLevel level, String format, Object... params) {
        if (level.compareTo(Logger.level) < 0) {
            Logger.wtr.printf(format, params);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        return Logger.os;
    }
}
