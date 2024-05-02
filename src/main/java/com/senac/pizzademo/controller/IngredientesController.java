package com.senac.pizzademo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.pizzademo.model.Ingredientes;
import com.senac.pizzademo.repository.IngredientesRepository;

@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {

    @Autowired
    private IngredientesRepository ingredientesRepository;

    @GetMapping
    public List<Ingredientes> getAllIngredientes() {
        return ingredientesRepository.findAll();
    }

    @PostMapping
    public Ingredientes createPizza(@RequestBody Ingredientes ingredientes) {
        return ingredientesRepository.save(ingredientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredientes(@PathVariable Long id) {
        return ingredientesRepository.findById(id)
            .map(ingredientes -> {
                ingredientesRepository.delete(ingredientes);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredientes> updateIngredientes(@PathVariable Long id, @RequestBody Ingredientes ingredientesDetails) {
        return ingredientesRepository.findById(id)
            .map(ingredientes -> {
                ingredientes.setIngrediente(ingredientesDetails.getIngrediente());
                ingredientes.setQuantidade(ingredientesDetails.getQuantidade());
                Ingredientes updatedIngredientes = ingredientesRepository.save(ingredientes);
                return ResponseEntity.ok(updatedIngredientes);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
