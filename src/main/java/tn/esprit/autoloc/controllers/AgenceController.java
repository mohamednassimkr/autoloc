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
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceService;

import java.util.List;

@RestController
@RequestMapping(value = {"/agences", "/api/agences"})
@RequiredArgsConstructor
@Tag(name = "Agence", description = "Endpoints pour la gestion des agences de location AutoLoc")
public class AgenceController {

    private final AgenceService agenceService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/agences
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Agence",
            description = "Enregistre un(e) nouveau/nouvelle Agence dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Agence> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Agence à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Agence.class)))
            @RequestBody Agence agence) {
        Agence created = agenceService.create(agence);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/agences
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les agences",
            description = "Retourne la liste complète de tous les agences enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class)))
    @GetMapping
    public ResponseEntity<List<Agence>> findAll() {
        return ResponseEntity.ok(agenceService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/agences/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Agence par ID",
            description = "Retourne le/la Agence correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class))),
            @ApiResponse(responseCode = "404", description = "Agence introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Agence> findById(
            @Parameter(description = "Identifiant unique du/de la Agence", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(agenceService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/agences/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Agence",
            description = "Effectue une mise à jour complète des attributs du/de la Agence identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Agence.class))),
            @ApiResponse(responseCode = "404", description = "Agence introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Agence> update(
            @Parameter(description = "Identifiant unique du/de la Agence à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Agence", required = true,
                    content = @Content(schema = @Schema(implementation = Agence.class)))
            @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.update(id, agence));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/agences/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Agence",
            description = "Supprime définitivement le/la Agence identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Agence introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Agence à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        agenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
