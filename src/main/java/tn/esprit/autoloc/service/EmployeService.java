package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface EmployeService extends CrudService<Employe> {

    @Override
    Employe create(Employe employe);

    @Override
    List<Employe> findAll();

    @Override
    Employe findById(Long id);

    @Override
    Employe update(Long id, Employe employe);

    @Override
    void delete(Long id);
}
