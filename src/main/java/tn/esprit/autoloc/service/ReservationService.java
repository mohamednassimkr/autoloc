package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface ReservationService extends CrudService<Reservation> {

    @Override
    Reservation create(Reservation reservation);

    @Override
    List<Reservation> findAll();

    @Override
    Reservation findById(Long id);

    @Override
    Reservation update(Long id, Reservation reservation);

    @Override
    void delete(Long id);
}
