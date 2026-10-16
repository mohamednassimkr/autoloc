package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl extends AbstractCrudService<Client> implements ClientService {

    public ClientServiceImpl(ClientRepository clientRepository) {
        super(clientRepository, Client::setIdClient);
    }
}
