package com.bil.erp.controller;

import com.bil.erp.dto.client.ClientRequest;
import com.bil.erp.dto.client.ClientResponse;
import com.bil.erp.intefaces.mapper.ClientMapper;
import com.bil.erp.intefaces.service.ClientService;
import com.bil.erp.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> getAll(Pageable pageable) {
        Page<Client> clients = clientService.getAll(pageable);
        Page<ClientResponse> responses = clients.map(clientMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{clientId}")
    private ResponseEntity<ClientResponse> getById(@PathVariable final Long clientId) {
        final Client client = clientService.getById(clientId);
        return ResponseEntity.ok(clientMapper.toResponse(client));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody final ClientRequest request) {
        var ct = clientService.create(clientMapper.toEntity(request));
        return ResponseEntity.ok(clientMapper.toResponse(ct));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponse> update(@PathVariable final Long clientId,
                                                 @RequestBody ClientRequest request) {
        var cl = clientMapper.toEntity(request);
        var client = clientService.update(clientId, cl);
        return ResponseEntity.ok(clientMapper.toResponse(client));
    }

    @DeleteMapping("/{clientId}")
    private void delete(@PathVariable final Long clientId) {
        clientService.delete(clientId);
    }
}
