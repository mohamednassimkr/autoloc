package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.service.MaintenanceService;

@Service
@Transactional
public class MaintenanceServiceImpl extends AbstractCrudService<Maintenance> implements MaintenanceService {

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        super(maintenanceRepository, Maintenance::setIdMaintenance);
    }
}
