package com.bil.erp.service;

import com.bil.erp.intefaces.repository.ClientRepository;
import com.bil.erp.intefaces.service.ClientService;
import com.bil.erp.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client update(Long clientId, Client client) {
        var c = clientRepository.findById(clientId).orElseThrow();
        c.setAddress(client.getAddress());
        c.setCity(client.getCity());
        c.setEmail(client.getEmail());
        c.setCountry(client.getCountry());
        c.setFirstname(client.getFirstname());
        c.setLastname(client.getLastname());
        c.setCity(client.getCity());
        c.setZipCode(client.getZipCode());
        c.setPhone(client.getPhone());
        c.setGsmPhone(client.getGsmPhone());
        c.setSales(client.getSales());
        return clientRepository.save(c);
    }

    @Override
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client getById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow();
    }
}
