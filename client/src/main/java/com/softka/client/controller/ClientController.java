package com.softka.client.controller;


import com.softka.client.dto.ClientDto;
import com.softka.client.dto.ClientResponseDto;
import com.softka.client.exception.ClientNotFoundException;
import com.softka.client.exception.ServiceErrorException;
import com.softka.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientResponseDto> saveClient(@RequestBody ClientDto clientDto) throws ServiceErrorException {
        return new ResponseEntity<>(clientService.saveClient(clientDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientResponseDto> updateClient( @RequestBody ClientDto clientDto ) throws ServiceErrorException, ClientNotFoundException {
        return new ResponseEntity<>(clientService.updateClient(clientDto), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable("clientId") Long clientId) throws ClientNotFoundException {
        return new ResponseEntity<>(clientService.findClientById(clientId), HttpStatus.OK);
    }

    @PutMapping("/status/{clientId}")
    public ResponseEntity<String> updatedClientStatus(@PathVariable("clientId") Long clientId) throws  ClientNotFoundException {
        return new ResponseEntity<>(clientService.changeClientStatus(clientId), HttpStatus.OK);
    }






}
