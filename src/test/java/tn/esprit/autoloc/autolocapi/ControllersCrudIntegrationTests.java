package tn.esprit.autoloc.autolocapi;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.autoloc.controllers.*;
import tn.esprit.autoloc.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ControllersCrudIntegrationTests {

    @Autowired
    private AgenceController agenceController;

    @Autowired
    private ClientController clientController;

    @Autowired
    private VehiculeController vehiculeController;

    @Autowired
    private EquipementController equipementController;

    @Autowired
    private EmployeController employeController;

    @Autowired
    private ContratController contratController;

    @Autowired
    private PaiementController paiementController;

    @Autowired
    private MaintenanceController maintenanceController;

    @Autowired
    private ReservationController reservationController;

    @Test
    @DisplayName("AgenceController: CRUD operations")
    void testAgenceController() {
        Agence agence = new Agence();
        agence.setNom("Agence Centrale");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");
        agence.setTelephone("+21671000000");

        ResponseEntity<Agence> created = agenceController.create(agence);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getBody()).isNotNull();
        Long id = created.getBody().getIdAgence();
        assertThat(id).isNotNull();

        ResponseEntity<List<Agence>> list = agenceController.findAll();
        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody()).extracting(Agence::getIdAgence).contains(id);

        ResponseEntity<Agence> found = agenceController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getNom()).isEqualTo("Agence Centrale");

        Agence toUpdate = new Agence();
        toUpdate.setNom("Agence Centrale Modifiee");
        toUpdate.setVille("Ariana");
        ResponseEntity<Agence> updated = agenceController.update(id, toUpdate);
        assertThat(updated.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updated.getBody().getVille()).isEqualTo("Ariana");

        ResponseEntity<Void> deleted = agenceController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThatThrownBy(() -> agenceController.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("ClientController: CRUD operations")
    void testClientController() {
        Client client = new Client();
        client.setNom("Ben Salah");
        client.setPrenom("Ahmed");
        client.setEmail("ahmed.bensalah@email.com");
        client.setTelephone("+21698000111");
        client.setNumPermis("B-12345678");
        client.setDateInscription(LocalDate.now());

        ResponseEntity<Client> created = clientController.create(client);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdClient();

        ResponseEntity<Client> found = clientController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getEmail()).isEqualTo("ahmed.bensalah@email.com");

        client.setTelephone("+21698999888");
        ResponseEntity<Client> updated = clientController.update(id, client);
        assertThat(updated.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updated.getBody().getTelephone()).isEqualTo("+21698999888");

        ResponseEntity<Void> deleted = clientController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("VehiculeController: CRUD operations")
    void testVehiculeController() {
        Vehicule v = new Vehicule();
        v.setImmatriculation("240-TUN-9999");
        v.setMarque("Peugeot");
        v.setModele("208");
        v.setCategorie(CategorieVehicule.CITADINE);
        v.setTarifJournalier(new BigDecimal("120.00"));
        v.setStatut(StatutVehicule.DISPONIBLE);

        ResponseEntity<Vehicule> created = vehiculeController.create(v);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdVehicule();

        ResponseEntity<Vehicule> found = vehiculeController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getMarque()).isEqualTo("Peugeot");

        v.setStatut(StatutVehicule.LOUE);
        ResponseEntity<Vehicule> updated = vehiculeController.update(id, v);
        assertThat(updated.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updated.getBody().getStatut()).isEqualTo(StatutVehicule.LOUE);

        ResponseEntity<Void> deleted = vehiculeController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("EquipementController: CRUD operations")
    void testEquipementController() {
        Equipement eq = new Equipement();
        eq.setLibelle("GPS Navigation Pro");

        ResponseEntity<Equipement> created = equipementController.create(eq);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdEquipement();

        ResponseEntity<Equipement> found = equipementController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getLibelle()).isEqualTo("GPS Navigation Pro");

        ResponseEntity<Void> deleted = equipementController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("EmployeController: CRUD operations")
    void testEmployeController() {
        Employe emp = new Employe();
        emp.setNom("Trabelsi");
        emp.setPrenom("Sami");
        emp.setRole(RoleEmploye.AGENT);

        ResponseEntity<Employe> created = employeController.create(emp);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdEmploye();

        ResponseEntity<Employe> found = employeController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getRole()).isEqualTo(RoleEmploye.AGENT);

        ResponseEntity<Void> deleted = employeController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("ContratController: CRUD operations")
    void testContratController() {
        Contrat c = new Contrat();
        c.setDateSignature(LocalDate.now());
        c.setMontantTotal(new BigDecimal("750.50"));
        c.setValide(true);

        ResponseEntity<Contrat> created = contratController.create(c);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdContrat();

        ResponseEntity<Contrat> found = contratController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().isValide()).isTrue();

        ResponseEntity<Void> deleted = contratController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("PaiementController: CRUD operations")
    void testPaiementController() {
        Paiement p = new Paiement();
        p.setMontant(new BigDecimal("250.00"));
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.CARTE);

        ResponseEntity<Paiement> created = paiementController.create(p);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdPaiement();

        ResponseEntity<Paiement> found = paiementController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getModePaiement()).isEqualTo(ModePaiement.CARTE);

        ResponseEntity<Void> deleted = paiementController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("MaintenanceController: CRUD operations")
    void testMaintenanceController() {
        Maintenance m = new Maintenance();
        m.setDateDebut(LocalDate.now());
        m.setDateFin(LocalDate.now().plusDays(2));
        m.setDescription("Vidange et revision des freins");

        ResponseEntity<Maintenance> created = maintenanceController.create(m);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdMaintenance();

        ResponseEntity<Maintenance> found = maintenanceController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getDescription()).isEqualTo("Vidange et revision des freins");

        ResponseEntity<Void> deleted = maintenanceController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("ReservationController: CRUD operations")
    void testReservationController() {
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.now().plusDays(1));
        r.setDateFin(LocalDate.now().plusDays(5));
        r.setStatut(StatutReservation.CONFIRMEE);

        ResponseEntity<Reservation> created = reservationController.create(r);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = created.getBody().getIdReservation();

        ResponseEntity<Reservation> found = reservationController.findById(id);
        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(found.getBody().getStatut()).isEqualTo(StatutReservation.CONFIRMEE);

        ResponseEntity<Void> deleted = reservationController.delete(id);
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
