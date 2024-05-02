package com.senac.pizzademo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.pizzademo.model.Pizza;
import com.senac.pizzademo.repository.PizzaRepository;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @PostMapping
    public Pizza createPizza(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable Long id) {
        return pizzaRepository.findById(id)
            .map(pizza -> {
                pizzaRepository.delete(pizza); // Cascata deletará ingredientes e cardápio
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }
 
    @PatchMapping("/{id}")
    public ResponseEntity<Pizza> patchPizza(@PathVariable Long id, @RequestBody Pizza pizzaDetails) {
        return pizzaRepository.findById(id)
            .map(pizza -> {
                if (pizzaDetails.getSabor() != null) {
                    pizza.setSabor(pizzaDetails.getSabor());
                }
                // Note that we do not update ingredientes or cardapio here
                Pizza updatedPizza = pizzaRepository.save(pizza);
                return ResponseEntity.ok(updatedPizza);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable Long id, @RequestBody Pizza pizzaDetails) {
        return pizzaRepository.findById(id)
            .map(pizza -> {
                pizza.setSabor(pizzaDetails.getSabor());
                pizza.setIngredientes(pizzaDetails.getIngredientes());
                pizza.setCardapio(pizzaDetails.getCardapio());
                Pizza updatedPizza = pizzaRepository.save(pizza);
                return ResponseEntity.ok(updatedPizza);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
