package com.softka.client.mapper;


import com.softka.client.dto.ClientDto;
import com.softka.client.dto.ClientResponseDto;
import com.softka.client.entity.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

@Component
public class Mapper {

    public Client clientDtoToClient(ClientDto clientDto){
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
    }

    public ClientResponseDto clientToClientResponseDto(Client client){
        ClientResponseDto  clientResponseDto = new ClientResponseDto();
        BeanUtils.copyProperties(client, clientResponseDto, getNullPropertyNames(client));
        return  clientResponseDto;
    }

    public Client updateProperties(ClientDto clientDto, Client actualProperties){
        BeanUtils.copyProperties(clientDto, actualProperties, getNullPropertyNames(clientDto));
        return actualProperties;
    }



    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

}
