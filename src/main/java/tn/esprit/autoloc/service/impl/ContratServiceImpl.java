package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.service.ContratService;

@Service
@Transactional
public class ContratServiceImpl extends AbstractCrudService<Contrat> implements ContratService {

    public ContratServiceImpl(ContratRepository contratRepository) {
        super(contratRepository, Contrat::setIdContrat);
    }
}
