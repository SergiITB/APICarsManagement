package com.example.carsmanagment.controladors;

import com.example.carsmanagment.model.entitats.Cotxe;
import com.example.carsmanagment.model.serveis.ServeisCotxe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorCotxe {
    private final ServeisCotxe serveiCotxe;

    public ControladorCotxe(ServeisCotxe serveiCotxe){this.serveiCotxe = serveiCotxe;}

    @GetMapping("")
    public ResponseEntity<Object> listarCotxes(){
        List<Cotxe> res = serveiCotxe.llistarCotxes();
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }

    @GetMapping("")
    public ResponseEntity<Cotxe> consultarCotxe(@PathVariable Long id) {
        Cotxe res = serveiCotxe.consultarCotxe(id);
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }

    @PostMapping("")
    public ResponseEntity<Cotxe> crearCotxe(@RequestBody Cotxe nou){
        Cotxe res = serveiCotxe.afegirCotxe(nou);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<Cotxe> eliminarCotxe(@PathVariable Long id){
        Cotxe res = serveiCotxe.eliminarCotxe(id);
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Cotxe> modificarCotxe(@RequestBody Cotxe mod){
        Cotxe res = serveiCotxe.modificarCotxe(mod);
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }
}
