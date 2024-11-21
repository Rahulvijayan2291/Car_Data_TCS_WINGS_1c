package com.example.cardata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cardata.model.CarData;

@Repository
public interface CarDataRepository extends JpaRepository<CarData, Integer> {

    
}
