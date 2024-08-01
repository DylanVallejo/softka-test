package com.softka.client.dto;

import com.softka.client.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;

}
