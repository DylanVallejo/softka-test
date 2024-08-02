package com.softka.client;


import com.softka.client.dto.ClientDto;
import com.softka.client.dto.ClientResponseDto;
import com.softka.client.entity.Client;
import com.softka.client.enums.Gender;
import com.softka.client.exception.ServiceErrorException;
import com.softka.client.mapper.Mapper;
import com.softka.client.repository.ClientRepository;
import com.softka.client.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;


    @Test
    void createClient_ShouldCreateClient() throws ServiceErrorException {

        Client mockClient = new Client();
        mockClient.setStatus(true);
        mockClient.setPassword("admin");
        mockClient.setAge(25);
        mockClient.setGender(Gender.MALE);
        mockClient.setAddress("Ecuador");
        mockClient.setIdentification("9999999");
        mockClient.setName("Dylan");
        mockClient.setPhone("999999");

        Client mockCreatedClient = new Client();
        mockCreatedClient.setId(1L);
        mockCreatedClient.setStatus(true);
        mockCreatedClient.setPassword("admin");
        mockCreatedClient.setAge(25);
        mockCreatedClient.setGender(Gender.MALE);
        mockCreatedClient.setAddress("Ecuador");
        mockCreatedClient.setIdentification("9999999");
        mockCreatedClient.setName("Dylan");
        mockCreatedClient.setPhone("999999");

        ClientDto clientDto = new ClientDto();
        clientDto.setPassword("admin");
        clientDto.setAge(25);
        clientDto.setGender(Gender.MALE);
        clientDto.setAddress("Ecuador");
        clientDto.setIdentification("9999999");
        clientDto.setName("Dylan");
        clientDto.setPhone("999999");

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(1L);
        clientResponseDto.setAge(25);
        clientResponseDto.setGender(Gender.MALE);
        clientResponseDto.setAddress("Ecuador");
        clientResponseDto.setIdentification("9999999");
        clientResponseDto.setName("Dylan");
        clientResponseDto.setPhone("999999");
        clientResponseDto.setStatus(true);

        Mockito.when(mapper.clientDtoToClient(clientDto)).thenReturn(mockClient);

        Mockito.when(clientRepository.save(mockClient)).thenReturn(mockCreatedClient);
        Mockito.when(mapper.clientToClientResponseDto(mockCreatedClient)).thenReturn(clientResponseDto);

        ClientResponseDto clientResponseDtoFinal = clientServiceImpl.saveClient(clientDto);

        assertEquals(1L, clientResponseDtoFinal.getId());
    }


}
