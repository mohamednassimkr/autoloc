package tn.esprit.autoloc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autoLocOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8081");
        localServer.setDescription("Serveur de développement local");

        Contact contact = new Contact();
        contact.setName("AutoLoc — Esprit School of Engineering");
        contact.setEmail("mohamednassim.kerkeni@esprit.tn");

        Info info = new Info()
                .title("AutoLoc API — Documentation REST")
                .version("1.0.0")
                .description(
                        "API REST complète pour la gestion de l'application de location de véhicules AutoLoc. " +
                        "Couvre les entités : Agence, Client, Contrat, Employe, Equipement, Maintenance, " +
                        "Paiement, Reservation et Vehicule avec les opérations CRUD complètes."
                )
                .contact(contact)
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
