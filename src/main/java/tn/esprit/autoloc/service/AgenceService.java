package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface AgenceService extends CrudService<Agence> {

    @Override
    Agence create(Agence agence);

    @Override
    List<Agence> findAll();

    @Override
    Agence findById(Long id);

    @Override
    Agence update(Long id, Agence agence);

    @Override
    void delete(Long id);
}
