package utils;

import java.io.OutputStream;

public interface ILogger {
    /**
     * Sets output stream to be logged to.
     * Does not close the current output stream.
     *
     * @param os the output stream to use.
     */
    void setOutputStream(OutputStream os);

    /**
     * Set the current log level.
     *
     * @param level the log level to use.
     */
    void setLogLevel(LogLevel level);

    /**
     * Logs a message at all levels.
     *
     * @param message the message to log.
     */
    void log(String message);

    /**
     * Logs a message at a particular level and above.
     *
     * @param level the minimum log level to start logging at.
     * @param message the message to log.
     */
    void log(LogLevel level, String message,);

    /**
     * Log a formatted message.
     *
     * @param format the format to use.
     * @param params parameters for the formatting.
     */
    void logf(String format, Object... params);

    /**
     * Logs a formatted message at a particular level and above.
     *
     * @param level the minimum level to start logging at.
     * @param format the format to use.
     * @param params parameters for the formatting.
     */
    void logf(LogLevel level, String format, Object... params);

    /**
     * @return the logger's current output stream.
     */
    OutputStream getOutputStream();
}
