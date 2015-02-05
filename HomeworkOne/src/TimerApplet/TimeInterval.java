package TimerApplet;

/**
 * A timing utility class useful for timing code segments
 * Source directory: http://www.cs.columbia.edu/~allen/S14/src/graphexample/
*/
public class TimeInterval {
	private long startTime, endTime;
    private long elapsedTime; // Time interval in milliseconds

    /**
     * Starts timing a piece of code<br>
     */
    public void startTiming() {
        elapsedTime = 0;
        startTime = System.currentTimeMillis();
    }

    /**
     * Ends timing a piece of code<br>
     */
    public void endTiming() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
    }

    /**
     * Gets the elapsed time in seconds <br>
     * @return the elapsed time in seconds
     */
    public double getElapsedTime() {
        return (double) elapsedTime / 1000.0;
    }
}
