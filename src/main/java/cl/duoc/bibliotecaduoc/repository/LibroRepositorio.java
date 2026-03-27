package cl.duoc.bibliotecaduoc.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.duoc.bibliotecaduoc.model.Libro;

@Repository
public class LibroRepositorio {

    private List<Libro> listaLibros = new ArrayList<>();

    private LibroRepositorio(){

        // 1. Cien años de soledad
        listaLibros.add(new Libro(1, "978-84-376-0494-7", "Cien años de soledad", "Sudamericana", LocalDate.of(1967, 5, 30), "Gabriel García Márquez"));

        // 2. Rayuela
        listaLibros.add(new Libro(2, "978-84-233-4233-5", "Rayuela", "Sudamericana", LocalDate.of(1963, 6, 28), "Julio Cortázar"));

        // 3. El amor en los tiempos del cólera
        listaLibros.add(new Libro(3, "978-84-670-5974-2", "El amor en los tiempos del cólera", "Debolsillo", LocalDate.of(1985, 9, 5), "Gabriel García Márquez"));

    }

    // Bucar todos los libros
    public List<Libro> findAll(){
        return listaLibros;
    }

    // Obtener un libro por ID
    public Libro findById(int id){
        return listaLibros.stream()
        .filter(libro -> libro.getId() == id)
        .findFirst().orElse(null);
    }

    //Obtener libro por autores que contengan X palabra
    public List<Libro> findByAutorContaining(String palabra){
        return listaLibros.stream()
        .filter(libro -> libro.getAutor()
        .toLowerCase()
        .contains(palabra.toLowerCase()))
        .toList();
    }

    //Obtener un libro por ISBN
    public Libro findByIsbn(String isbn){
        return listaLibros.stream()
        .filter(libro -> libro.getIsbn().equals(isbn))
        .findFirst()
        .orElse(null);
    }

    //Obtener libros por titulo que contengan X palabra
    public List<Libro> findByTituloContaining(String palabra){
        return listaLibros.stream()
        .filter(libro -> libro.getTitulo()
        .toLowerCase()
        .contains(palabra.toLowerCase()))
        .toList();
        
    }

    //Guardar un libro
    public Libro save(Libro libro){
        listaLibros.add(libro);
        return libro;
    }


    //Actualizar libro
    public Libro update(Libro libro) {
        Libro libroBuscado = findById(libro.getId());
        if (libroBuscado != null) {
            listaLibros.remove(libroBuscado);
            listaLibros.add(libro);
            return libro; 
        }
        return null;
    }


    //Borrar un libro por ID
    public boolean delete(int id) {

        Libro libroBuscado = findById(id);

        if (libroBuscado != null) {
            listaLibros.remove(libroBuscado);
            return true; 
        }

        return false; 
    }


    //Obtener libros por fecha de publicación
    public List<Libro> findByFechaPublicacion(LocalDate fecha){
        return listaLibros.stream()
        .filter(libro -> libro.getFechaPublicacion().equals(fecha))
        .toList();
    }


    //Obtener libros por editorial
    public List<Libro> findByEditorial(String editorial){
        return listaLibros.stream()
        .filter(libro -> libro.getEditorial()
        .toLowerCase()
        .contains(editorial.toLowerCase()))
        .toList();   
    }




}
