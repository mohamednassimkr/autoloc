package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.VehiculeService;

@Service
@Transactional
public class VehiculeServiceImpl extends AbstractCrudService<Vehicule> implements VehiculeService {

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        super(vehiculeRepository, Vehicule::setIdVehicule);
    }
}
