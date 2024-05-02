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

import com.senac.pizzademo.model.Cardapio;
import com.senac.pizzademo.repository.CardapioRepository;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    @Autowired
    private CardapioRepository cardapioRepository;

    @GetMapping
    public List<Cardapio> getAllCardapios() {
        return cardapioRepository.findAll();
    }

    @PostMapping
    public Cardapio createCardapio(@RequestBody Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }

    // Adicionar métodos para atualização e exclusão conforme necessário
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCardapio(@PathVariable Long id) {
        return cardapioRepository.findById(id)
            .map(cardapio -> {
                cardapioRepository.delete(cardapio);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cardapio> updateCardapio(@PathVariable Long id, @RequestBody Cardapio cardapioDetails) {
        return cardapioRepository.findById(id)
            .map(cardapio -> {
                cardapio.setValor(cardapioDetails.getValor());
                cardapio.setTamanho(cardapioDetails.getTamanho());
                cardapio.setPizza(cardapioDetails.getPizza()); // Ajuste de acordo com a necessidade de mudar a associação de pizza
                Cardapio updatedCardapio = cardapioRepository.save(cardapio);
                return ResponseEntity.ok(updatedCardapio);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
