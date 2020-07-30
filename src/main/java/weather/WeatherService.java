package weather;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

/**
 * WeatherService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/30/2020
 */
@Service
public class WeatherService {

    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();

    {
        weathers.put(1, new Weather(1, "Msw", 20));
        weathers.put(2, new Weather(2, "SPb", 15));
        weathers.put(3, new Weather(3, "Bryansk", 15));
        weathers.put(4, new Weather(4, "Smolensk", 15));
        weathers.put(5, new Weather(5, "Kiev", 15));
        weathers.put(6, new Weather(6, "Minsk", 10));
    }

    public final Mono<Weather> findById(final Integer id) {
        return Mono.justOrEmpty(this.weathers.get(id));
    }

    public final Flux<Weather> all() {
        return Flux.fromIterable(this.weathers.values());
    }

    public final Mono<Weather> getMaxTemp() {
        return Mono.justOrEmpty(Collections.max(
                this.weathers.entrySet(),
                comparingInt(o -> o.getValue().getTemperature()))
                .getValue());
    }

    public final Flux<Weather> getTempGreatThen(final Integer tmp) {
        return Flux.fromIterable(this.weathers.values()
                .stream()
                .filter(w -> w.getTemperature() > tmp)
                .collect(Collectors.toList()));
    }

}
