package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface EquipementService extends CrudService<Equipement> {

    @Override
    Equipement create(Equipement equipement);

    @Override
    List<Equipement> findAll();

    @Override
    Equipement findById(Long id);

    @Override
    Equipement update(Long id, Equipement equipement);

    @Override
    void delete(Long id);
}
