package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.service.EquipementService;

@Service
@Transactional
public class EquipementServiceImpl extends AbstractCrudService<Equipement> implements EquipementService {

    public EquipementServiceImpl(EquipementRepository equipementRepository) {
        super(equipementRepository, Equipement::setIdEquipement);
    }
}
