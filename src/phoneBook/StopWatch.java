// Abstract data type to mimic elapsed time for a program to complete
package phoneBook;

public class StopWatch {

    // Start time variable
    private final long start;

    // Constructor
    public StopWatch() {
        start = System.currentTimeMillis();
    }

    // Compute elapsed time
    public double elapsedTime() {

        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    // Convert time in seconds to print in hours, minutes, and seconds
    public void convertTime() {

        int time = (int) elapsedTime(); // cast to int

        // convert time from sec to min ranging from 0 to 60 min
        int min;
        min = (time / 60) % 60;

        // convert time from sec to hrs ranging from 0 to 24 hrs
        int hr;
        hr = (time / 3600) % 24;

        // Convert remaining seconds ranging from 0 to 60 seconds
        int seconds_remain;
        seconds_remain = time % 60;

        System.out.println("Elasped Time is: " + hr + " hours, " +
                            min + " minutes, " + seconds_remain + " seconds.");

    }

}
