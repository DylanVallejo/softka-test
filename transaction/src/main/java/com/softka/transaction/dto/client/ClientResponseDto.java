package com.softka.transaction.dto.client;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private Boolean status;

}
