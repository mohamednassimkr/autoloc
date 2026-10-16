package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface VehiculeService extends CrudService<Vehicule> {

    @Override
    Vehicule create(Vehicule vehicule);

    @Override
    List<Vehicule> findAll();

    @Override
    Vehicule findById(Long id);

    @Override
    Vehicule update(Long id, Vehicule vehicule);

    @Override
    void delete(Long id);
}
