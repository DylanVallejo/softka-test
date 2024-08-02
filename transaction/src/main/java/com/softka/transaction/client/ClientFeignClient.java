package com.softka.transaction.client;

import com.softka.transaction.dto.client.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client", url = "localhost:8080/api/v1/client")
public interface ClientFeignClient {

    @GetMapping(value = "/{clientId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ClientResponseDto findClientById(@PathVariable("clientId") Long clientId);

}
