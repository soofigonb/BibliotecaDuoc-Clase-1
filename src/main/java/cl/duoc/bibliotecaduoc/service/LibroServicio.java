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

    public Libro obtenerPorId(int id){
        if (id > 0) {
            return repo.findById(id);
        }
        return null;
    }

    public List<Libro> buscarPorAutor(String palabra){
        if (palabra != null && !palabra.isEmpty()) {
            return repo.findByAutorContaining(palabra);
        }
        return List.of();
    }

    public Libro obtenerPorIsbn(String isbn){
        if (isbn != null && !isbn.isEmpty()) {
            return repo.findByIsbn(isbn);
        }
        return null;
    }

}
