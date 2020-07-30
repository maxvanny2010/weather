package weather;

/**
 * Weather.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/30/2020
 */
public class Weather {
    private int id;
    private String city;
    private int temperature;

    public Weather(int id, String city, int temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getTemperature() {
        return temperature;
    }
}
