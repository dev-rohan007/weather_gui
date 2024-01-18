package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.simple.JSONObject;

import backend.WeatherApp;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;

    public WeatherAppGui() {
        super("Weather app");
        // Setup for gui and title
        setSize(450, 650);
        // load gui at the centre of the screen
        setLocationRelativeTo(null);
        // connfigure the gui to close the proram
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // prevent resizable by user
        setResizable(false);
        // Disable the by default layout manager
        setLayout(null);

        // Component
        addGuiComponents();

    }

    private void addGuiComponents() {
        // search field
        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(15, 15, 351, 45);
        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        // Weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src\\Assets\\cloudy.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);

        // Temperature text
        JLabel tempText = new JLabel("10 C");
        tempText.setBounds(0, 350, 450, 54);
        tempText.setFont(new Font("Dialog", Font.BOLD, 48));
        tempText.setHorizontalAlignment(SwingConstants.CENTER);

        // Weather condition description
        JLabel weatherConditioDesc = new JLabel("Cloudy");
        weatherConditioDesc.setBounds(0, 405, 450, 36);
        weatherConditioDesc.setFont(new Font("Dialog", Font.BOLD, 30));
        weatherConditioDesc.setHorizontalAlignment(SwingConstants.CENTER);

        // Humidity image
        JLabel humidityImage = new JLabel(loadImage("src\\Assets\\humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);

        // Himidity text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.BOLD, 16));
        humidityText.setHorizontalAlignment(SwingConstants.CENTER);

        // Windspeed image
        JLabel windSpeedImage = new JLabel(loadImage("src\\Assets\\windspeed.png"));
        windSpeedImage.setBounds(220, 500, 74, 66);

        // Windspeed text
        JLabel windSpeedText = new JLabel("<html><b>Windspeed</b> 100%</html>");
        windSpeedText.setBounds(310, 500, 85, 55);
        windSpeedText.setFont(new Font("Dialog", Font.BOLD, 16));
        windSpeedText.setHorizontalAlignment(SwingConstants.CENTER);

        // button
        JButton searchButton = new JButton(loadImage("src\\Assets\\search.png"));
        searchButton.setBounds(375, 13, 47, 45);
         searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchTextField.getText();

                // validate input - remove whitespace to ensure non-empty text
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                // retrieve weather data
                weatherData = WeatherApp.getWeatherData(userInput);

                // update gui

                // update weather image
                String weatherCondition = (String) weatherData.get("weather_condition");

                // depending on the condition, we will update the weather image that corresponds
                // with the condition
                // replace the file location of image with your 
                switch (weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("bin\\Assets\\clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("bin\\Assets\\cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("bin\\Assets\\rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("bin\\Assets\\snow.png"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                tempText.setText(temperature + " C");

                // update weather condition text
                weatherConditioDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b> " + humidity + "%</html>");

                // update windspeed text
                double windspeed = (double) weatherData.get("windspeed");
                windSpeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");
            }
        });

        // Add the component
        add(searchTextField);
        add(searchButton);
        add(weatherConditionImage);
        add(tempText);
        add(weatherConditioDesc);
        add(humidityImage);
        add(humidityText);
        add(windSpeedImage);
        add(windSpeedText);

    }

    // Use to create images in gui commponents
    private Icon loadImage(String resoucePath) {
        try {
            BufferedImage image = ImageIO.read(new File(resoucePath));
            // render it
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not load the image");
        return null;
    }
}
