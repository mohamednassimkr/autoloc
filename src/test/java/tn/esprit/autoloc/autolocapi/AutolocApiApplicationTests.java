package tn.esprit.autoloc.autolocapi;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AutolocApiApplicationTests {

    @Autowired
    private AgenceService agenceService;

    @Test
    void contextLoads() {
    }

    @Test
    void agenceServiceSupportsCrudOperations() {
        Agence agence = new Agence();
        agence.setNom("Agence test");
        agence.setVille("Tunis");

        Agence created = agenceService.create(agence);

        assertThat(agenceService.findById(created.getIdAgence()).getNom()).isEqualTo("Agence test");
        assertThat(agenceService.findAll()).extracting(Agence::getIdAgence).contains(created.getIdAgence());

        Agence updated = new Agence();
        updated.setNom("Agence modifiee");
        updated.setVille("Sousse");
        Agence savedUpdate = agenceService.update(created.getIdAgence(), updated);

        assertThat(savedUpdate.getIdAgence()).isEqualTo(created.getIdAgence());
        assertThat(agenceService.findById(created.getIdAgence()).getNom()).isEqualTo("Agence modifiee");

        agenceService.delete(created.getIdAgence());
        assertThatThrownBy(() -> agenceService.findById(created.getIdAgence()))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
