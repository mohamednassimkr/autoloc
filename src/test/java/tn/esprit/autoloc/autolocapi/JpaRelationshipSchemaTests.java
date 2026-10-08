package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaRelationshipSchemaTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void createsAssociationTablesAndForeignKeys() {
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
                String.class
        );

        assertThat(tables).contains(
                "AGENCE",
                "CLIENT",
                "CONTRAT",
                "CONTRAT_PAIEMENTS",
                "EQUIPEMENT",
                "MAINTENANCE",
                "PAIEMENT",
                "RESERVATION",
                "VEHICULE",
                "VEHICULE_EQUIPEMENT"
        );

        Integer foreignKeyCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS "
                        + "WHERE CONSTRAINT_SCHEMA = 'PUBLIC' AND CONSTRAINT_TYPE = 'FOREIGN KEY'",
                Integer.class
        );
        assertThat(foreignKeyCount).isNotNull().isGreaterThanOrEqualTo(9);
    }
}
