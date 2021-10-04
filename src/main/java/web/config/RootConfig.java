package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import web.model.Car;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("web")
public class RootConfig {
    @Bean
    public List<Car> getListOfCars() {
        return Arrays.asList(
                new Car("Bugatti", "Veyron", 3262362),
                new Car("Lamborghini", "Gallardo", 36229532),
                new Car("Lamborghini", "Aventador", 75436262),
                new Car("Lamborghini", "Diablo", 8659670),
                new Car("Ford", "Mustang", 3262362),
                new Car("Rolls-Royce", "Phantom", 32632734),
                new Car("Aston Martin", "One-77", 53232632),
                new Car("Koenigsegg", "One", 66325325),
                new Car("McLaren", "Speedtail", 32632632));
    }
}
