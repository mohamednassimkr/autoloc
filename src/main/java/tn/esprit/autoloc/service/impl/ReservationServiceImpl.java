package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl extends AbstractCrudService<Reservation> implements ReservationService {

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        super(reservationRepository, Reservation::setIdReservation);
    }
}
