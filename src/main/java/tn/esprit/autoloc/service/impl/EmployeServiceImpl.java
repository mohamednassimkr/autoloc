package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;
import tn.esprit.autoloc.service.EmployeService;

@Service
@Transactional
public class EmployeServiceImpl extends AbstractCrudService<Employe> implements EmployeService {

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        super(employeRepository, Employe::setIdEmploye);
    }
}
