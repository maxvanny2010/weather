package weather;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

/**
 * WeatherControl.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/30/2020
 */
@RestController
public class WeatherControl {
    private final WeatherService weathers;

    public WeatherControl(WeatherService weathers) {
        this.weathers = weathers;
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> all() {
        Flux<Weather> data = this.weathers.all();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    @GetMapping(value = "/get/{id}")
    public Mono<Weather> get(@PathVariable final Integer id) {
        return this.weathers.findById(id);
    }

    @GetMapping("/hottest")
    public Mono<Weather> getHottest() {
        return this.weathers.getMaxTemp();
    }

    @GetMapping(value = "/citiesGreatThen/{tmp}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> getTmpCitiesGreatThen(@PathVariable final Integer tmp) {
        final Flux<Weather> data = this.weathers.getTempGreatThen(tmp);
        final Flux<Long> delay = Flux.interval(Duration.ofSeconds(2));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }
}

