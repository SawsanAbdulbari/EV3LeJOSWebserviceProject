package main.java.motors;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import data.Config;

public class MotorRunWeb implements Runnable {    
    
	private Config config;

    private EV3LargeRegulatedMotor leftMotor;

    private EV3LargeRegulatedMotor rightMotor;
    

    public MotorRunWeb(Config config) {
        this.config = config;
        this.leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        this.rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    }

    @Override
    public void run() {
        while (true) {
            Delay.msDelay(100);
            if (config.getDirection() == 1) {
            	leftMotor.backward();
            	rightMotor.backward();
            } else {
            	rightMotor.forward();
            	leftMotor.forward();
            }
            
            if (config.getTurn() < 0) {
            	 rightMotor.setSpeed(config.getSpeed()* 2);
                 leftMotor.setSpeed(config.getSpeed());
            } else if (config.getTurn() > 0) {
	           	 rightMotor.setSpeed(config.getSpeed());
	             leftMotor.setSpeed(config.getSpeed()* 2);
            } else {
	           	 rightMotor.setSpeed(config.getSpeed());
	             leftMotor.setSpeed(config.getSpeed());
            }
            	
            HttpURLConnection con = null;
            OutputStream os = null;

            try {
                Thread.sleep(1);
                URL url = new URL("http://192.168.208.147:9191/rest/lego/sendvalues");
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                String postData = config.toJsonString();

                os = con.getOutputStream();
                os.write(postData.getBytes());
                os.flush();

                int responseCode = con.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    System.out.println("Failed to connect, response code: " + responseCode);
                } else {
                    System.out.println("Data sent successfully to the web service.");
                }
            } catch (Exception e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
        }
        
    }
}




