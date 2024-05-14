package main;

import main.java.colorSensor.ColorSensor;
import data.Config;
import main.java.LCD.LCDandSound;
import main.java.motors.MotorControl;
import main.java.ultraSonicSensor.*;
import lejos.utility.Delay;


/**
 * This documentation demonstrates how LeJOS EV3 robot has been programmed to follow a line and avoid an obstacle,
 * utilising color sensing, motor control, LCD display and sounds as well as ultrasonic distance sensing.
 * The classes rely on a shared configuration object to manage application state and settings.
 * The EV3 robot has been programmed for HAMK Applied application project course.
 * @date: April 2024
 * @authors Sawsan Abdulbari, Linda Marin, Sonja Lahti & Olga Hakasuo
 * @version 2.0
 */
public class MainClass 
{
    /**
     * This is the main entry point of the application.
     * Initialises and starts separate threads for LCD and sound feedback,
     * color sensing, motor control, and ultrasonic distance sensing based on a shared configuration.
     * Monitors the application state and gracefully shuts down all components upon termination.
     */
    public static void main(String[] args) 
    {
        /** 
         * Initialize shared configuration 
         */
        Config config = new Config();
        
        /**
         * Initialize and start the LCD and Sound handling thread, 
         * with configuration class passed to the constructor
         */
        LCDandSound timer = new LCDandSound(config);
        Thread lcdAndSoundThread = new Thread(timer);
        
        /**
         * Initialize and start the Color Sensor handling thread, 
         * with configuration class passed to the constructor 
         */
        ColorSensor colorSensor = new ColorSensor(config);
        Thread colorSensorThread = new Thread(colorSensor);
        
        /**
         * Initialize and start the Motor Control handling thread, 
         * with configuration class passed to the constructor 
         */
        MotorControl motorControl = new MotorControl(config);
        Thread motorControlThread = new Thread(motorControl);
        
        /**
         * Initialize and start the Ultrasonic Sensor handling thread, 
         * with configuration class passed to the constructor 
         */
        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(config);
        Thread ultrasonicSensorThread = new Thread(ultrasonicSensor);

        /** 
         * Start all threads 
         */
        colorSensorThread.start();
        lcdAndSoundThread.start();
        motorControlThread.start();
        ultrasonicSensorThread.start();

        /** 
         * Main loop to monitor application state 
         */
        while (true) {
            if (!config.isRunning()) {
                /** 
                 * Delay before shutting down to allow for any final operations 
                 */
                Delay.msDelay(10000);
                
                /** 
                 * Interrupt threads to signal them to stop 
                 */
                colorSensorThread.interrupt();
                motorControlThread.interrupt();
                lcdAndSoundThread.interrupt();
                ultrasonicSensorThread.interrupt();
                
                /** 
                 * Wait for all threads to finish their execution 
                 */
                try {
                    colorSensorThread.join();
                    motorControlThread.join();
                    lcdAndSoundThread.join();
                    ultrasonicSensorThread.join();
                    
                } catch (InterruptedException e) {
                    /**
                     * Restore the interrupted status of the current thread 
                     */ 
                    Thread.currentThread().interrupt();
                }
                
                /**
                 * Exit the application 
                 */
                System.exit(0);
            }
        }
    }
}