package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface MaintenanceService extends CrudService<Maintenance> {

    @Override
    Maintenance create(Maintenance maintenance);

    @Override
    List<Maintenance> findAll();

    @Override
    Maintenance findById(Long id);

    @Override
    Maintenance update(Long id, Maintenance maintenance);

    @Override
    void delete(Long id);
}
