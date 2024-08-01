package com.softka.client.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;


}
