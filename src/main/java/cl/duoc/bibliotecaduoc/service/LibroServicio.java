package cl.duoc.bibliotecaduoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.bibliotecaduoc.model.Libro;
import cl.duoc.bibliotecaduoc.repository.LibroRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServicio {

    private final LibroRepositorio repo;

    public List<Libro> obtenerTodos(){
        return repo.findAll();
    }

}
