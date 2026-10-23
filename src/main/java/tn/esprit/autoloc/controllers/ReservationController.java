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
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping(value = {"/reservations", "/api/reservations"})
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "Endpoints pour la gestion des réservations de véhicules par les clients")
public class ReservationController {

    private final ReservationService reservationService;

    // ──────────────────────────────────────────────────────────────────────
    // POST  /api/reservations
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Créer un(e) Reservation",
            description = "Enregistre un(e) nouveau/nouvelle Reservation dans la base de données et retourne la ressource créée avec son identifiant auto-généré."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reservation créé(e) avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Reservation> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du/de la Reservation à créer", required = true,
                    content = @Content(schema = @Schema(implementation = Reservation.class)))
            @RequestBody Reservation reservation) {
        Reservation created = reservationService.create(reservation);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/reservations
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Lister tous les reservations",
            description = "Retourne la liste complète de tous les reservations enregistrés dans la base de données."
    )
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class)))
    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET   /api/reservations/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Obtenir un(e) Reservation par ID",
            description = "Retourne le/la Reservation correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation trouvé(e)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "Reservation introuvable pour cet identifiant", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(
            @Parameter(description = "Identifiant unique du/de la Reservation", required = true, example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    // ──────────────────────────────────────────────────────────────────────
    // PUT   /api/reservations/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Mettre à jour un(e) Reservation",
            description = "Effectue une mise à jour complète des attributs du/de la Reservation identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation mis(e) à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "Reservation introuvable pour cet identifiant", content = @Content),
            @ApiResponse(responseCode = "400", description = "Corps de la requête invalide", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> update(
            @Parameter(description = "Identifiant unique du/de la Reservation à modifier", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du/de la Reservation", required = true,
                    content = @Content(schema = @Schema(implementation = Reservation.class)))
            @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.update(id, reservation));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE /api/reservations/{id}
    // ──────────────────────────────────────────────────────────────────────
    @Operation(
            summary = "Supprimer un(e) Reservation",
            description = "Supprime définitivement le/la Reservation identifié(e) par l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reservation supprimé(e) avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reservation introuvable pour cet identifiant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant unique du/de la Reservation à supprimer", required = true, example = "1")
            @PathVariable Long id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
