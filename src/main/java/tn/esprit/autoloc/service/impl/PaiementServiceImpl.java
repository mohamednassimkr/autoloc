package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;
import tn.esprit.autoloc.service.PaiementService;

@Service
@Transactional
public class PaiementServiceImpl extends AbstractCrudService<Paiement> implements PaiementService {

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        super(paiementRepository, Paiement::setIdPaiement);
    }
}
