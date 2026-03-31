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

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }

        Libro libro = repo.findById(id);

        if (libro == null) {
            throw new RuntimeException("Libro no encontrado");
        }

        return libro;
    }

    public List<Libro> buscarPorAutor(String palabra){

        if (palabra == null || palabra.isEmpty()) {
            throw new IllegalArgumentException("La palabra de búsqueda no puede estar vacía");
        }

        return repo.findByAutorContaining(palabra);
    }

    public Libro obtenerPorIsbn(String isbn){

        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío");
        }

        Libro libro = repo.findByIsbn(isbn);

        if (libro == null) {
            throw new RuntimeException("Libro no encontrado con ese ISBN");
        }

        return libro;
    }

    public List<Libro> buscarPorTitulo(String palabra){

        if (palabra == null || palabra.isEmpty()) {
            throw new IllegalArgumentException("La palabra de búsqueda no puede estar vacía");
        }

        return repo.findByTituloContaining(palabra);
    }

    public Libro guardarLibro (Libro libro){

        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }

        if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título del libro es obligatorio");
        }

        return repo.save(libro);
    }

    public Libro actualizarLibro(Libro libro){

        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }

        if (libro.getId() <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }

        Libro actualizado = repo.update(libro);

        if (actualizado == null) {
            throw new RuntimeException("No se pudo actualizar, libro no encontrado");
        }

        return actualizado;
    }

    public boolean eliminarLibro(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }

        boolean eliminado = repo.delete(id);

        if (!eliminado) {
            throw new RuntimeException("No se pudo eliminar, libro no encontrado");
        }

        return true;
    }

    public List<Libro> buscarPorFecha(LocalDate fecha){

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        return repo.findByFechaPublicacion(fecha);
    }

    public List<Libro> buscarPorEditorial(String editorial){

        if (editorial == null || editorial.isEmpty()) {
            throw new IllegalArgumentException("La editorial no puede estar vacía");
        }

        return repo.findByEditorial(editorial);
    }

    public int totalLibros(){
        return repo.totalLibros();
    }

}
