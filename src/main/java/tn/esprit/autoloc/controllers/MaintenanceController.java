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
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping(value = {"/maintenances", "/api/maintenances"})
@RequiredArgsConstructor
@Tag(name = "Maintenance", description = "Endpoints pour la gestion des opérations de maintenance des véhicules")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/maintenances
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Maintenance",
            description = "Enregistre un(e) nouveau/nouvelle Maintenance dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Maintenance> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Maintenance à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Maintenance.class)))
            @RequestBody Maintenance maintenance) {
        Maintenance created = maintenanceService.create(maintenance);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/maintenances
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les maintenances",
            description = "Retourne la liste complète de tous les maintenances enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maintenance.class)))
    @GetMapping
    public ResponseEntity<List<Maintenance>> findAll() {
        return ResponseEntity.ok(maintenanceService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/maintenances/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Maintenance par ID",
            description = "Retourne le/la Maintenance correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> findById(
            @Parameter(description = "Identifiant unique du/de la Maintenance", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/maintenances/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Maintenance",
            description = "Effectue une mise à jour complète des attributs du/de la Maintenance identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Maintenance.class))),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> update(
            @Parameter(description = "Identifiant unique du/de la Maintenance à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Maintenance", required = true,
                    content = @Content(schema = @Schema(implementation = Maintenance.class)))
            @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.update(id, maintenance));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/maintenances/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Maintenance",
            description = "Supprime définitivement le/la Maintenance identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Maintenance à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        maintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
