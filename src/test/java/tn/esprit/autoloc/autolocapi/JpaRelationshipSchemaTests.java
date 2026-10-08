package tn.esprit.autoloc.autolocapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaRelationshipSchemaTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Test
    void createsAssociationTablesAndForeignKeys() throws SQLException {
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
                String.class
        );

        assertThat(tables).contains(
                "AGENCE",
                "CLIENT",
                "CONTRAT",
                "CONTRAT_PAIEMENTS",
                "EMPLOYE",
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
        assertThat(foreignKeyCount).isNotNull().isGreaterThanOrEqualTo(10);

        try (Connection connection = dataSource.getConnection();
             ResultSet importedKeys = connection.getMetaData().getImportedKeys(null, "PUBLIC", "EMPLOYE")) {
            boolean referencesAgence = false;
            while (importedKeys.next()) {
                if ("AGENCE".equalsIgnoreCase(importedKeys.getString("PKTABLE_NAME"))
                        && "ID_AGENCE".equalsIgnoreCase(importedKeys.getString("FKCOLUMN_NAME"))) {
                    referencesAgence = true;
                    break;
                }
            }
            assertThat(referencesAgence).isTrue();
        }
    }
}
