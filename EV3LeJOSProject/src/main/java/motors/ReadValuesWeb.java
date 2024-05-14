package main.java.motors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import data.Config;
import lejos.hardware.Button;

public class ReadValuesWeb implements Runnable
{	
	private Config config;

    public ReadValuesWeb(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        while (Button.ESCAPE.isUp()) {
        	HttpURLConnection con = null;
        	BufferedReader br = null;
            try {
                Thread.sleep(1);
            	URL url = new URL("http://192.168.208.147:9191/rest/lego/getdirSpeed");

                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(10000); // 10 seconds connection timeout
                con.connect();
                                
                int responseCode = con.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    System.out.println("Failed to connect, response code: " + responseCode);
                    continue; // Skip processing if connection is not OK
                }
                
                InputStream is = null;
                System.out.println(con.toString());
                try {
                    is = con.getInputStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    String s = br.readLine();  
                    String[] ds = s.split("#");
                    config.setSpeed(ds[0]);
                    config.setDirection(ds[1]);
                    config.setRun(ds[2]);
                    config.setTurn(ds[3]);
                    
                } catch (Exception e) {
                    System.out.println("Exception caught getting InputStream");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            }
        }
    }
}

























