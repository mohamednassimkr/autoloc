package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface PaiementService extends CrudService<Paiement> {

    @Override
    Paiement create(Paiement paiement);

    @Override
    List<Paiement> findAll();

    @Override
    Paiement findById(Long id);

    @Override
    Paiement update(Long id, Paiement paiement);

    @Override
    void delete(Long id);
}
