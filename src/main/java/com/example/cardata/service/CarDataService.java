package com.example.cardata.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cardata.model.CarData;
import com.example.cardata.repository.CarDataRepository;

@Service
public class CarDataService {

    @Autowired
    private CarDataRepository carDataRepository;

    public ResponseEntity<Object> addCarData(CarData carData) {
        try {
            CarData newcCarData = new CarData(carData.getModel(), carData.getManufacturedYear(), carData.getCompany(), carData.getBodyType(),carData.getPrice(),carData.getFuelType(),carData.getSeatingCapacity());
            carDataRepository.save(newcCarData);
            return ResponseEntity.status(HttpStatus.CREATED).body(newcCarData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Object> listAllCarData() {
        List<CarData> carDataList = carDataRepository.findAll();
        if(carDataList.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Data Available");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carDataList);
    }

    public ResponseEntity<Object> updateCarData(int id, double price) {
        Optional<CarData> carDataOptional = carDataRepository.findById(id);
        if(carDataOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        CarData carData = carDataOptional.get();
        carData.setPrice(price);
        carDataRepository.save(carData);
        return ResponseEntity.status(HttpStatus.OK).body(carData);
    }

    public ResponseEntity<Object> deleteCarData(int id) {
        if(!carDataRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        carDataRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
