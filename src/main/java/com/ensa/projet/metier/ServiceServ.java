package com.ensa.projet.metier;

import com.ensa.projet.models.Servicee;
import com.ensa.projet.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceServ {
    Page<Servicee> getUserServicesAsChef(long user_id, Pageable pageable);
    Page<Servicee> getUserServicesAsEmployee(long user_id,Pageable pageable);
    Servicee getServiceById(long service_id);
    void addService(Servicee service);
    void updateService(Servicee servicee);
}
