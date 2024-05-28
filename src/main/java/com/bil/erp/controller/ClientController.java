package com.bil.erp.controller;

import com.bil.erp.dto.client.ClientRequest;
import com.bil.erp.dto.client.ClientResponse;
import com.bil.erp.intefaces.mapper.ClientMapper;
import com.bil.erp.intefaces.service.ClientService;
import com.bil.erp.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/client")
@RequiredArgsConstructor
@Tag(name = "client")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    @Operation(operationId = "clientGetAll")
    public ResponseEntity<Page<ClientResponse>> getAll(Pageable pageable) {
        Page<Client> clients = clientService.getAll(pageable);
        Page<ClientResponse> responses = clients.map(clientMapper::toResponse);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{clientId}")
    @Operation(operationId = "clientGetById")
    private ResponseEntity<ClientResponse> getById(@PathVariable final Long clientId) {
        final Client client = clientService.getById(clientId);
        return ResponseEntity.ok(clientMapper.toResponse(client));
    }

    @PostMapping
    @Operation(operationId = "clientCreate")
    public ResponseEntity<ClientResponse> create(@RequestBody final ClientRequest request) {
        var ct = clientService.create(clientMapper.toEntity(request));
        return ResponseEntity.ok(clientMapper.toResponse(ct));
    }

    @PutMapping("/{clientId}")
    @Operation(operationId = "clientUpdate")
    public ResponseEntity<ClientResponse> update(@PathVariable final Long clientId,
                                                 @RequestBody ClientRequest request) {
        var cl = clientMapper.toEntity(request);
        var client = clientService.update(clientId, cl);
        return ResponseEntity.ok(clientMapper.toResponse(client));
    }

    @DeleteMapping("/{clientId}")
    @Operation(operationId = "clientDelete")
    private void delete(@PathVariable final Long clientId) {
        clientService.delete(clientId);
    }
}
