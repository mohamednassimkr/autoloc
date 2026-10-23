package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.EquipementService;

import java.util.List;

@RestController
@RequestMapping(value = {"/equipements", "/api/equipements"})
@RequiredArgsConstructor
public class EquipementController {

    private final EquipementService equipementService;

    @PostMapping
    public ResponseEntity<Equipement> create(@RequestBody Equipement equipement) {
        Equipement created = equipementService.create(equipement);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Equipement>> findAll() {
        return ResponseEntity.ok(equipementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(equipementService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.update(id, equipement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
