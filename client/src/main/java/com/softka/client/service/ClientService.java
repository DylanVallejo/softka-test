package com.softka.client.service;

import com.softka.client.dto.ClientDto;
import com.softka.client.dto.ClientResponseDto;
import com.softka.client.exception.ClientNotFoundException;
import com.softka.client.exception.ServiceErrorException;

public interface ClientService {

    ClientResponseDto saveClient(ClientDto clientDto) throws ServiceErrorException;
    ClientResponseDto updateClient(ClientDto clientDto) throws ClientNotFoundException, ServiceErrorException;
    ClientResponseDto findClientById(Long id) throws ClientNotFoundException;
    String changeClientStatus(Long id) throws ClientNotFoundException;

}
