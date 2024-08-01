package com.softka.client.service.impl;

import com.softka.client.dto.ClientDto;
import com.softka.client.dto.ClientResponseDto;
import com.softka.client.entity.Client;
import com.softka.client.exception.ClientNotFoundException;
import com.softka.client.exception.ServiceErrorException;
import com.softka.client.mapper.Mapper;
import com.softka.client.repository.ClientRepository;
import com.softka.client.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Mapper mapper;

    @Override
    @Transactional
    public ClientResponseDto saveClient(ClientDto clientDto) throws ServiceErrorException {
        try {
            Client newClient = mapper.clientDtoToClient(clientDto);
            newClient.setStatus(true);
            Client clientDB = clientRepository.save(newClient);
            return mapper.clientToClientResponseDto(clientDB);
        }catch (Exception exception){
            throw new ServiceErrorException(exception.getMessage());
        }
    }

    @Override
    @Transactional
    public ClientResponseDto updateClient(ClientDto clientDto) throws  ServiceErrorException {
        try {
            Client clientDB =  clientRepository.findClientByIdAndStatusTrue(clientDto.getId())
                    .orElseThrow(() -> new ClientNotFoundException("Client with id: " + clientDto.getId() + " not found, or is already inactive"));
            Client clientToUpdated = mapper.updateProperties(clientDto, clientDB);
            clientToUpdated.setStatus(clientDB.getStatus());
            Client clientUpdated = clientRepository.save(clientToUpdated);
            return mapper.clientToClientResponseDto(clientUpdated);
        }catch (Exception exception){
            throw new ServiceErrorException(exception.getMessage());
        }
    }

    @Override
    @Transactional
    public ClientResponseDto findClientById(Long id) throws ClientNotFoundException {
        Client clientDB = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
        return mapper.clientToClientResponseDto(clientDB);
    }

    @Override
    @Transactional
    public String changeClientStatus(Long clientId) throws ClientNotFoundException {
        boolean currentStatus =  validateUser(clientId);
        boolean updatedStatus = !currentStatus;
        clientRepository.updateStatus(clientId, updatedStatus );
        return "Client status whit id: " + clientId + " updated.";
    }

    private boolean validateUser(Long clientId) throws ClientNotFoundException {
        Client clientStatus =  clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + clientId));
        return clientStatus.getStatus();
    }


}
