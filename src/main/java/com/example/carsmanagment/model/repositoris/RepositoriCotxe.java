package com.example.carsmanagment.model.repositoris;

import com.example.carsmanagment.model.entitats.Cotxe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoriCotxe extends JpaRepository<Cotxe, Long> {

}
