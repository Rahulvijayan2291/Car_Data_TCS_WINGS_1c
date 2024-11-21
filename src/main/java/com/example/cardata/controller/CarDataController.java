package com.example.cardata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardata.model.CarData;
import com.example.cardata.service.CarDataService;

@RestController
@RequestMapping("/carData")
public class CarDataController {

    @Autowired
    private CarDataService carDataService;

    @PostMapping("/add")
    public ResponseEntity<Object> addCarData(@RequestBody CarData carData) {
        if(carData.getModel() == ""){
            return ResponseEntity.badRequest().build();
        }
        return carDataService.addCarData(carData);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAllCarData() {
        return carDataService.listAllCarData();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateCarData(@PathVariable int id,@RequestBody CarData carData) {
        if(carData.getPrice() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Data Provided");
        }
        return carDataService.updateCarData(id, carData.getPrice());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCarData(@PathVariable int id) {
        return carDataService.deleteCarData(id);
    }
}
