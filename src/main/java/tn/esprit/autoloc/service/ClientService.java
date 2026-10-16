package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface ClientService extends CrudService<Client> {

    @Override
    Client create(Client client);

    @Override
    List<Client> findAll();

    @Override
    Client findById(Long id);

    @Override
    Client update(Long id, Client client);

    @Override
    void delete(Long id);
}
