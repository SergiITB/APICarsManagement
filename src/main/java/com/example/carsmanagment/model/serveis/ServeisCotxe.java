package com.example.carsmanagment.model.serveis;

import com.example.carsmanagment.model.entitats.Cotxe;
import com.example.carsmanagment.model.repositoris.RepositoriCotxe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServeisCotxe {
    private final RepositoriCotxe repoCotxes;

    public ServeisCotxe(RepositoriCotxe repoCotxes){this.repoCotxes = repoCotxes;}

    //LListar tots els cotxes
    public List<Cotxe> llistarCotxes(){return repoCotxes.findAll();}

    //Consultar cotxe per Id
    public Cotxe consultarCotxe(Long id){return repoCotxes.findById(id).orElse(null);}

    //Afegir cotxe
    public Cotxe afegirCotxe(Cotxe cotxe){return repoCotxes.save(cotxe);}

    //Modificar sencer, si existeix el canvia, si no retorna null
    public Cotxe modificarCotxe(Cotxe cotxe){
        Cotxe cot = null;
        if (repoCotxes.existsById(cotxe.getId())){
            cot = repoCotxes.save(cotxe);
        }
        return cot;
    }

    //Eliminar cotxe per Id, si no existeix retorna null
    public Cotxe eliminarCotxe(Long id){
        Cotxe cotxe = repoCotxes.findById(id).orElse(null);
        if (cotxe!=null){
            repoCotxes.deleteById(id);
        }
        return cotxe;
    }
}
