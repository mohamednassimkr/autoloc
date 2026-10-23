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
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.EmployeService;

import java.util.List;

@RestController
@RequestMapping(value = {"/employes", "/api/employes"})
@RequiredArgsConstructor
@Tag(name = "Employe", description = "Endpoints pour la gestion des employés rattachés aux agences")
public class EmployeController {

    private final EmployeService employeService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/employes
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Employe",
            description = "Enregistre un(e) nouveau/nouvelle Employe dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employe créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employe.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Employe> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Employe à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Employe.class)))
            @RequestBody Employe employe) {
        Employe created = employeService.create(employe);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/employes
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les employes",
            description = "Retourne la liste complète de tous les employes enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employe.class)))
    @GetMapping
    public ResponseEntity<List<Employe>> findAll() {
        return ResponseEntity.ok(employeService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/employes/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Employe par ID",
            description = "Retourne le/la Employe correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employe trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employe.class))),
            @ApiResponse(responseCode = "404", description = "Employe introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employe> findById(
            @Parameter(description = "Identifiant unique du/de la Employe", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(employeService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/employes/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Employe",
            description = "Effectue une mise à jour complète des attributs du/de la Employe identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employe mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employe.class))),
            @ApiResponse(responseCode = "404", description = "Employe introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Employe> update(
            @Parameter(description = "Identifiant unique du/de la Employe à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Employe", required = true,
                    content = @Content(schema = @Schema(implementation = Employe.class)))
            @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.update(id, employe));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/employes/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Employe",
            description = "Supprime définitivement le/la Employe identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employe supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employe introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Employe à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        employeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
