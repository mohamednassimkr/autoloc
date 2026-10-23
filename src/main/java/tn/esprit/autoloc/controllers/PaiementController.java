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
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.PaiementService;

import java.util.List;

@RestController
@RequestMapping(value = {"/paiements", "/api/paiements"})
@RequiredArgsConstructor
@Tag(name = "Paiement", description = "Endpoints pour la gestion des paiements associés aux contrats")
public class PaiementController {

    private final PaiementService paiementService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/paiements
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Paiement",
            description = "Enregistre un(e) nouveau/nouvelle Paiement dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Paiement créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paiement.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Paiement> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Paiement à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Paiement.class)))
            @RequestBody Paiement paiement) {
        Paiement created = paiementService.create(paiement);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/paiements
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les paiements",
            description = "Retourne la liste complète de tous les paiements enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paiement.class)))
    @GetMapping
    public ResponseEntity<List<Paiement>> findAll() {
        return ResponseEntity.ok(paiementService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/paiements/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Paiement par ID",
            description = "Retourne le/la Paiement correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paiement.class))),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> findById(
            @Parameter(description = "Identifiant unique du/de la Paiement", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(paiementService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/paiements/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Paiement",
            description = "Effectue une mise à jour complète des attributs du/de la Paiement identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paiement.class))),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Paiement> update(
            @Parameter(description = "Identifiant unique du/de la Paiement à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Paiement", required = true,
                    content = @Content(schema = @Schema(implementation = Paiement.class)))
            @RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementService.update(id, paiement));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/paiements/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Paiement",
            description = "Supprime définitivement le/la Paiement identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Paiement supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Paiement à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        paiementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
