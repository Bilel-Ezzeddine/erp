package com.bil.erp.intefaces.mapper;

import com.bil.erp.dto.client.ClientRequest;
import com.bil.erp.dto.client.ClientResponse;
import com.bil.erp.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientRequest clientRequest);

    ClientResponse toResponse(Client client);
}
