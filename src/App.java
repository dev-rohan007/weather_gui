import javax.swing.SwingUtilities;

import org.json.simple.JSONObject;

import backend.WeatherApp;
import gui.WeatherAppGui;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                //display our weather app gui
                new WeatherAppGui().setVisible(true);
                //testing: location geographical data form the API
                //System.out.println(WeatherApp.getCurrentTime());
               
                
            }
        });
    }
}