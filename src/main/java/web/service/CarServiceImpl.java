package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private List<Car> cars;

    @Override
    public List<Car> getList() {
        return cars;
    }

    @Override
    public List<Car> getList(int num) {
        return num >= 5 ? getList() : cars.subList(0, num);
    }
}
