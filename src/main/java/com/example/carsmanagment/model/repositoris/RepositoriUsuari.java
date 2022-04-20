package com.example.carsmanagment.model.repositoris;

import com.example.carsmanagment.model.entitats.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepositoriUsuari extends JpaRepository<Usuari, Long> {
    Optional<Usuari> findByUsername(String username);
}
