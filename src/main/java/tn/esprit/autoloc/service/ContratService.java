package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface ContratService extends CrudService<Contrat> {

    @Override
    Contrat create(Contrat contrat);

    @Override
    List<Contrat> findAll();

    @Override
    Contrat findById(Long id);

    @Override
    Contrat update(Long id, Contrat contrat);

    @Override
    void delete(Long id);
}
