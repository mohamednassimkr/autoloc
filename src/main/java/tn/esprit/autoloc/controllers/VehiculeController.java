package tn.esprit.autoloc.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;

import java.util.List;

@RestController
@RequestMapping(value = {"/vehicules", "/api/vehicules"})
@RequiredArgsConstructor
@Tag(name = "Vehicule", description = "Endpoints pour la gestion du parc de véhicules disponibles à la location")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/vehicules
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Vehicule",
            description = "Enregistre un(e) nouveau/nouvelle Vehicule dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Vehicule> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Vehicule à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Vehicule.class)))
            @RequestBody Vehicule vehicule) {
        Vehicule created = vehiculeService.create(vehicule);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/vehicules
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les vehicules",
            description = "Retourne la liste complète de tous les vehicules enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class)))
    @GetMapping
    public ResponseEntity<List<Vehicule>> findAll() {
        return ResponseEntity.ok(vehiculeService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/vehicules/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Vehicule par ID",
            description = "Retourne le/la Vehicule correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> findById(
            @Parameter(description = "Identifiant unique du/de la Vehicule", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/vehicules/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Vehicule",
            description = "Effectue une mise à jour complète des attributs du/de la Vehicule identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehicule.class))),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> update(
            @Parameter(description = "Identifiant unique du/de la Vehicule à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Vehicule", required = true,
                    content = @Content(schema = @Schema(implementation = Vehicule.class)))
            @RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.update(id, vehicule));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/vehicules/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Vehicule",
            description = "Supprime définitivement le/la Vehicule identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Vehicule à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        vehiculeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
