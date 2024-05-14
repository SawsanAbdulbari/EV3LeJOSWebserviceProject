package main;

import data.Config;
import main.java.motors.MotorRunWeb;
import main.java.motors.ReadValuesWeb;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class MainWeb 
{

    public static void main(String[] args) 
    {
        Config config = new Config();

        ReadValuesWeb readValuesWeb = new ReadValuesWeb(config);
        Thread readValuesWebThread = new Thread(readValuesWeb);

        MotorRunWeb motorRunWeb = new MotorRunWeb(config);
        Thread motorRunWebThread = new Thread(motorRunWeb);

        readValuesWebThread.start();
        motorRunWebThread.start();

        while (Button.ESCAPE.isUp()) {
            Delay.msDelay(1000);  // Regular delay check, adjust according to need
        }

        // Shut down threads gracefully
        readValuesWebThread.interrupt();
        motorRunWebThread.interrupt();
        try {
            readValuesWebThread.join();
            motorRunWebThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Properly handle thread interruption
        }

        System.exit(0);
    }
}