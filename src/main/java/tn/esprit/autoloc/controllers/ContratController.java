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
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.ContratService;

import java.util.List;

@RestController
@RequestMapping(value = {"/contrats", "/api/contrats"})
@RequiredArgsConstructor
@Tag(name = "Contrat", description = "Endpoints pour la gestion des contrats de location signés")
public class ContratController {

    private final ContratService contratService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/contrats
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Contrat",
            description = "Enregistre un(e) nouveau/nouvelle Contrat dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrat créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contrat.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Contrat> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Contrat à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Contrat.class)))
            @RequestBody Contrat contrat) {
        Contrat created = contratService.create(contrat);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/contrats
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les contrats",
            description = "Retourne la liste complète de tous les contrats enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contrat.class)))
    @GetMapping
    public ResponseEntity<List<Contrat>> findAll() {
        return ResponseEntity.ok(contratService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/contrats/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Contrat par ID",
            description = "Retourne le/la Contrat correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contrat.class))),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Contrat> findById(
            @Parameter(description = "Identifiant unique du/de la Contrat", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(contratService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/contrats/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Contrat",
            description = "Effectue une mise à jour complète des attributs du/de la Contrat identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contrat.class))),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Contrat> update(
            @Parameter(description = "Identifiant unique du/de la Contrat à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Contrat", required = true,
                    content = @Content(schema = @Schema(implementation = Contrat.class)))
            @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.update(id, contrat));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/contrats/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Contrat",
            description = "Supprime définitivement le/la Contrat identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrat supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Contrat à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        contratService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
