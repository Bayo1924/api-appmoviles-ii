package com.cibertec.api.elmilagritos.elmilagritos.controller;

import com.cibertec.api.elmilagritos.elmilagritos.entities.DishDetailEntity;
import com.cibertec.api.elmilagritos.elmilagritos.entities.DishEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dishes")
public class DishController {

    private final List<DishEntity> dishes = Arrays.asList(
            new DishEntity("Pizza italiana con masa artesanal y pepperoni", 1L, "Pizza Pepperoni", 25.0, "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg"),
            new DishEntity("Hamburguesa doble con queso cheddar y papas", 2L, "Hamburguesa Doble", 18.5, "https://www.recetasnestle.com.ec/sites/default/files/srh_recipes/4e4293857c03d819e4ae51de1e86d66a.jpg")
    );

    private final List<DishDetailEntity> dishDetails = Arrays.asList(
            new DishDetailEntity(dishes.get(0), 101L, "Masa artesanal", "Salsa de tomate", "Queso mozzarella", "Pepperoni", "Orégano"),
            new DishDetailEntity(dishes.get(1), 102L, "Pan artesanal", "Carne de res", "Queso cheddar", "Papas fritas", "Salsa especial")
    );

    @GetMapping
    public ResponseEntity<List<DishEntity>> getDishes() {
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/detail/{dishId}")
    public ResponseEntity<DishDetailEntity> getDishDetailByDishId(@PathVariable Long dishId) {
        return dishDetails.stream()
                .filter(detail -> detail.getDish().getId().equals(dishId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
