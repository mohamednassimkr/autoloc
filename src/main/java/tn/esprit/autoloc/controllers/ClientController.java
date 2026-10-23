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
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(value = {"/clients", "/api/clients"})
@RequiredArgsConstructor
@Tag(name = "Client", description = "Endpoints pour la gestion des clients enregistrés dans AutoLoc")
public class ClientController {

    private final ClientService clientService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/clients
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Client",
            description = "Enregistre un(e) nouveau/nouvelle Client dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Client> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Client à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Client.class)))
            @RequestBody Client client) {
        Client created = clientService.create(client);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/clients
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les clients",
            description = "Retourne la liste complète de tous les clients enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/clients/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Client par ID",
            description = "Retourne le/la Client correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Client introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(
            @Parameter(description = "Identifiant unique du/de la Client", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/clients/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Client",
            description = "Effectue une mise à jour complète des attributs du/de la Client identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "404", description = "Client introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(
            @Parameter(description = "Identifiant unique du/de la Client à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Client", required = true,
                    content = @Content(schema = @Schema(implementation = Client.class)))
            @RequestBody Client client) {
        return ResponseEntity.ok(clientService.update(id, client));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/clients/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Client",
            description = "Supprime définitivement le/la Client identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Client introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Client à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
