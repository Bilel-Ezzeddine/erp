package com.bil.erp.intefaces.service;

import com.bil.erp.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Client create(Client client);

    Page<Client> getAll(Pageable pageable);

    Client update(Long clientId, Client client);

    void delete(Long clientId);

    Client getById(Long clientId);
}
