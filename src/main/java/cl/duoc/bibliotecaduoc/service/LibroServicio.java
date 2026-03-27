package cl.duoc.bibliotecaduoc.service;

import java.time.LocalDate;
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

    public List<Libro> buscarPorTitulo(String palabra){
        if (palabra != null && !palabra.isEmpty()) {
            return repo.findByTituloContaining(palabra);
        }
        return List.of();
    }

    public Libro guardarLibro (Libro libro){
        if (libro != null && !libro.getTitulo().isEmpty()) {
            return repo.save(libro);
        } 
        return null;
    }

    public Libro actualizarLibro(Libro libro){
        if (libro != null && libro.getId() > 0) {

            Libro actualizado = repo.update(libro);

            if (actualizado != null) {
                return actualizado; 
            }
        }
        return null;
    }

    public boolean eliminarLibro(int id) {

        if (id <= 0) {
            return false;
        }

        return repo.delete(id);
    }

    public List<Libro> buscarPorFecha(LocalDate fecha){
        if (fecha != null) {
            return repo.findByFechaPublicacion(fecha);
        }
        return List.of();
    }

    public List<Libro> buscarPorEditorial(String editorial){
        if (editorial != null && !editorial.isEmpty()) {
            return repo.findByEditorial(editorial);
        }
        return List.of();
    }

}
