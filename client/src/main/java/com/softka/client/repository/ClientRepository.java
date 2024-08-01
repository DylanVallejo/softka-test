package com.softka.client.repository;


import com.softka.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client c SET c.status = :newStatus WHERE c.id = :clientId")
    void updateStatus(@Param("clientId") Long clientId, @Param("newStatus") Boolean newStatus);

    Optional<Client> findClientByIdAndStatusTrue(Long clientId);

}
