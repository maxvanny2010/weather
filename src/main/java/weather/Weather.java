package weather;

/**
 * Weather.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/30/2020
 */
public class Weather {
    private final int id;
    private final String city;
    private final int temperature;

    public Weather(final int id, String city, final int temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    public final int getId() {
        return this.id;
    }

    public final String getCity() {
        return this.city;
    }

    public final int getTemperature() {
        return this.temperature;
    }
}
