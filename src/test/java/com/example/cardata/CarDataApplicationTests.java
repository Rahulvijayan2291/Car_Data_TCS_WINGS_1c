package com.example.cardata;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(classes = CarDataApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class CarDataApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void testAddCarData() throws Exception {
        mvc.perform(post("/carData/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCarDataDetails("City", 2024, "Honda", "sedan", 2000000.0, "petrol", 5)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value("City"))
                .andExpect(jsonPath("$.manufacturedYear").value(2024))
                .andExpect(jsonPath("$.company").value("Honda"))
                .andExpect(jsonPath("$.bodyType").value("sedan"))
                .andExpect(jsonPath("$.price").value(2000000.0))
                .andExpect(jsonPath("$.fuelType").value("petrol"))
                .andExpect(jsonPath("$.seatingCapacity").value(5));
    }

    @Test
    @Order(2)
    public void testListAllCarData() throws Exception {
        mvc.perform(get("/carData/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].model").value("City"));
    }

    @Test
    @Order(3)
    public void testUpdateCarData() throws Exception {
        mvc.perform(patch("/carData/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"price\": 1600000.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(1600000.0));
    }

    @Test
    @Order(4)
    public void testDeleteCarData() throws Exception {
        mvc.perform(delete("/carData/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testAddInvalidCarData() throws Exception {
        mvc.perform(post("/carData/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCarDataDetails("", 2024, "Honda", "sedan", 2000000.0, "petrol", 5)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(6)
    public void testGetNonExistentCarData() throws Exception {
        mvc.perform(get("/carData/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    public void testUpdateNonExistentCarData() throws Exception {
        mvc.perform(patch("/carData/update/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"price\": 1600000.0 }"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(8)
    public void testDeleteNonExistentCarData() throws Exception {
        mvc.perform(delete("/carData/delete/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private String getCarDataDetails(String model, int manufacturedYear, String company, String bodyType, double price, String fuelType, int seatingCapacity) throws JsonProcessingException {
        Map<String, Object> carData = new HashMap<>();
        carData.put("model", model);
        carData.put("manufacturedYear", manufacturedYear);
        carData.put("company", company);
        carData.put("bodyType", bodyType);
        carData.put("price", price);
        carData.put("fuelType", fuelType);
        carData.put("seatingCapacity", seatingCapacity);
        return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(carData);
    }
}
