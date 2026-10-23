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
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.EquipementService;

import java.util.List;

@RestController
@RequestMapping(value = {"/equipements", "/api/equipements"})
@RequiredArgsConstructor
@Tag(name = "Equipement", description = "Endpoints pour la gestion des équipements proposés avec les véhicules")
public class EquipementController {

    private final EquipementService equipementService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/equipements
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Equipement",
            description = "Enregistre un(e) nouveau/nouvelle Equipement dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipement créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipement.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Equipement> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Equipement à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Equipement.class)))
            @RequestBody Equipement equipement) {
        Equipement created = equipementService.create(equipement);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/equipements
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les equipements",
            description = "Retourne la liste complète de tous les equipements enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipement.class)))
    @GetMapping
    public ResponseEntity<List<Equipement>> findAll() {
        return ResponseEntity.ok(equipementService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/equipements/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Equipement par ID",
            description = "Retourne le/la Equipement correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipement trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipement.class))),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Equipement> findById(
            @Parameter(description = "Identifiant unique du/de la Equipement", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(equipementService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/equipements/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Equipement",
            description = "Effectue une mise à jour complète des attributs du/de la Equipement identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipement mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipement.class))),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Equipement> update(
            @Parameter(description = "Identifiant unique du/de la Equipement à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Equipement", required = true,
                    content = @Content(schema = @Schema(implementation = Equipement.class)))
            @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.update(id, equipement));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/equipements/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Equipement",
            description = "Supprime définitivement le/la Equipement identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Equipement supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Equipement à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        equipementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
