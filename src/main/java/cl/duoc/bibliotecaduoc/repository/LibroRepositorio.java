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

        // 4. Fuego y Sangre
        listaLibros.add(new Libro(4, "9789569646638", "Fuego y Sangre", 
        "Penguin Random House Grupo Editorial", LocalDate.of(2018, 11, 20), "George R. R. Martin"));

        // 5. Quique Hache
        listaLibros.add(new Libro(5, "9789563494150", "Quique Hache: El Mall Embrujado y Otras Historias", 
        "Sm Ediciones", LocalDate.of(2014, 3, 1), "Sergio Gomez"));

        // 6. Spring Boot
        listaLibros.add(new Libro(6, "9781484256251", "Spring Boot Persistence Best Practices", 
        "Apress", LocalDate.of(2020, 6, 10), "Anghel Leonard"));

        // 7. Harry Potter y la piedra filosofal (edición moderna en español)
        listaLibros.add(new Libro(7, "9789566075752", "Harry Potter y la piedra filosofal", 
        "Salamandra", LocalDate.of(2024, 1, 15), "J. K. Rowling"));

        // 8. Harry Potter y el prisionero de Azkaban
        listaLibros.add(new Libro(8, "9780439139601", "Harry Potter y el prisionero de Azkaban", 
        "Scholastic", LocalDate.of(1999, 7, 8), "J. K. Rowling"));

        // 9. Harry Potter y el cáliz de fuego
        listaLibros.add(new Libro(9, "9780439136365", "Harry Potter y el cáliz de fuego", 
        "Scholastic", LocalDate.of(2000, 7, 8), "J. K. Rowling"));

        // 10. Effective Java (2nd Edition)
        listaLibros.add(new Libro(10, "9780321127426", "Effective Java", 
        "Addison-Wesley", LocalDate.of(2008, 5, 28), "Joshua Bloch"));

        // 11. Clean Architecture
        listaLibros.add(new Libro(11, "9780134685991", "Clean Architecture", 
        "Prentice Hall", LocalDate.of(2017, 9, 20), "Robert C. Martin"));

        // 12. Design Patterns
        listaLibros.add(new Libro(12, "9780201633610", "Design Patterns", 
        "Addison-Wesley", LocalDate.of(1994, 10, 21), "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));

        // 13. Clean Code
        listaLibros.add(new Libro(13, "9780132350884", "Clean Code", 
        "Prentice Hall", LocalDate.of(2008, 8, 1), "Robert C. Martin"));

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
            .filter(libro -> libro.getAutor() != null &&
                            palabra != null &&
                            libro.getAutor().toLowerCase().contains(palabra.toLowerCase()))
            .toList();
    }

    //Obtener un libro por ISBN
    public Libro findByIsbn(String isbn){
        return listaLibros.stream()
            .filter(libro -> libro.getIsbn() != null &&
                            isbn != null &&
                            libro.getIsbn().equals(isbn))
            .findFirst()
            .orElse(null);
    }

    //Obtener libros por titulo que contengan X palabra
    public List<Libro> findByTituloContaining(String palabra){
        return listaLibros.stream()
            .filter(libro -> libro.getTitulo() != null &&
                            palabra != null &&
                            libro.getTitulo().toLowerCase().contains(palabra.toLowerCase()))
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
            .filter(libro -> libro.getFechaPublicacion() != null &&
                            fecha != null &&
                            libro.getFechaPublicacion().equals(fecha))
            .toList();
    }


    //Obtener libros por editorial
    public List<Libro> findByEditorial(String editorial){
        return listaLibros.stream()
            .filter(libro -> libro.getEditorial() != null &&
                            editorial != null &&
                            libro.getEditorial().toLowerCase().contains(editorial.toLowerCase()))
            .toList();   
    }

    //Total Libros
    public int totalLibros(){
        return listaLibros.size();
    }




}
