package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.service.AgenceService;

@Service
@Transactional
public class AgenceServiceImpl extends AbstractCrudService<Agence> implements AgenceService {

    public AgenceServiceImpl(AgenceRepository agenceRepository) {
        super(agenceRepository, Agence::setIdAgence);
    }
}
