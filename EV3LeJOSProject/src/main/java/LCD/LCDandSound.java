package main.java.LCD;

/**
 * Date: April 2024
 * This is LCD
 * @auhor Olga
 * @version 2.0
 */

import data.Config;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;

public class LCDandSound implements Runnable {
	/**
	 * 	Config object for configuration settings.
	 */
    private Config config;
    /**
     * Stopwatch object for taking time
     */
    private Stopwatch stopwatch;
    /**
     * In this constructor we initialize object confiq and  Stopwatch.
     * Instanciates new stopwatch.
     * 
     * @param config Configuration settings for the lCDandSound object.
     * 
     */
    public LCDandSound(Config config) {
        this.config = config;
        this.stopwatch = new Stopwatch();
    }
    /**
     * This method initiates the start of the course. 
     * Clear the lcd display.
     * Message indicating star of the course.
     * Reset stopwatch.
     * Play a predefined song.
     * Additional tone for immediate feedback.
     */

    public void startCourse() {
        LCD.clear();
        LCD.drawString("Starting course", 0, 0);
        stopwatch.reset();
        playSong(); // Play a predefined song
        Sound.playTone(440, 500); // Additional tone for immediate feedback
    }
    
    /**
     * 	This method initiates playing of the song
     * Array containing frequency and duration pairs for each note.
     * Under it there are frequences and durations in ms.
     */
    public void playSong() {
        int[][] notes = {
            {440, 500}, {494, 500}, {523, 500},
            {587, 500}, {659, 500}, {698, 500}, {784, 500}
        };
        /**
         * Calculate how many times to repeat the melody to fill the desired duration
         * in milliseconds.
         */ 
        int totalDuration = 0; 
        /**
         *  1 minute and 20 seconds in milliseconds.
         */
        int targetDuration = 80000; 

        for (int[] note : notes) {
            totalDuration += note[1] + 100; // Add note duration + pause duration
        }
        int repeats = targetDuration / totalDuration;
        /**
         *   Play the melody in a loop to fill the desired duration.
         *   
         *   Play the current note.
         *   
         *   Pause for 100ms between notes.
         *   
         *   /If interupted return from the method.
         */
     // Play the melody in a loop to fill the desired duration
        for (int i = 0; i < repeats; i++) {
            for (int[] note : notes) {
                Sound.playTone(note[0], note[1]);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    }
    /**
     * Check if timer is running else return without updating display.
     * Get elapsed time from stopwatch.
     * Elapsed time shown on LCD screen in seconds.
     * Reduce update frequency to save CPU.
     * 
     */
    public void updateDisplay() {
        if (!config.RUNNING) return;
        int elapsedTime = stopwatch.elapsed();
        LCD.drawString("Time: " + ((float) elapsedTime / 1000) + "s", 0, 2);
        try {
            Thread.sleep(1000); // Reduce update frequency to save CPU
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            config.setRunning(false);
        }
    }
    /**
     * Stops the current course, calculating and displaying the elapsed time since the course started.
     * It sets the running status to false to ensure the main loop stops. After stopping,
     * it clears the display and shows a completion message along with the total time taken for the course.
     * The time is formatted in seconds and displayed on the LCD screen.
     */
    public void stopCourse() {
    	config.setRunning(false); // Ensure this method stops the loop
        int elapsedTime = stopwatch.elapsed();
        LCD.clear();
        LCD.drawString("Course complete", 0, 0);
        LCD.drawString("Time: " + ((float)elapsedTime / 1000) + "s", 0, 2);
    }
    /**
     * This is the main execution point for the thread. It waits for a latch, indicating that some form of
     * initialization or calibration (presumably of the system or device) has been completed. After the
     * calibration is done, it proceeds with the execution of the course by calling startCourse().
     * While the configuration's RUNNING flag is true, it continually updates the display and checks if the
     * goal has been reached. If the goal is reached, it invokes stopCourse() to terminate the course.
     * If the thread is interrupted while waiting on the latch, it will catch the InterruptedException,
     * re-interrupt the current thread, and exit, ensuring graceful handling of thread interruptions.
     */
	@Override
	public void run() {
        try {
            config.latch.await();
            // Proceed with the rest of the thread's logic after calibration is done
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
            return; // Exit the method
        }
		startCourse();
		while(config.RUNNING) {
			updateDisplay();
			if(config.hasReachedGoal)
			{
				stopCourse();
			}
		}
	}

}
