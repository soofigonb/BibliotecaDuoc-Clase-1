package cl.duoc.bibliotecaduoc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.bibliotecaduoc.service.LibroServicio;
import cl.duoc.bibliotecaduoc.model.Libro;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroControlador {

    private final LibroServicio servicio;

    @GetMapping("") 
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Libro> lista = servicio.obtenerTodos();

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build(); 
            }

            return ResponseEntity.status(200).body(lista);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/id/{id}") 
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        try {
            Libro libro = servicio.obtenerPorId(id);

            if (libro == null) {
                return ResponseEntity.status(404).body("Libro no encontrado");
            }

            return ResponseEntity.status(200).body(libro);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno");
        }
    }

    @GetMapping("/buscar/autor") 
    public ResponseEntity<?> buscarPorAutor(@RequestParam String autor) {
        try {
            List<Libro> lista = servicio.buscarPorAutor(autor);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(200).body(lista);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno");
        }
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> obtenerPorIsbn(@PathVariable String isbn) {
        try {
            Libro libro = servicio.obtenerPorIsbn(isbn);

            if (libro == null) {
                return ResponseEntity.status(404).body("Libro no encontrado");
            }

            return ResponseEntity.status(200).body(libro);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno");
        }
    }
    
    @GetMapping("/buscar/titulo")
    public ResponseEntity<?> buscarPorTitulo(@RequestParam String palabra) {
        try {
            List<Libro> lista = servicio.buscarPorTitulo(palabra);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(200).body(lista);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno");
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> guardarLibro(@RequestBody Libro libro) {
        try {
            Libro nuevo = servicio.guardarLibro(libro);

            if (nuevo == null) {
                return ResponseEntity.badRequest().body("No se pudo agregar el libro");
            }

            return ResponseEntity.status(201).body(nuevo);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    
    @PutMapping("/actualizar")
    public Libro actualizar(@RequestBody Libro libro) {
        return servicio.actualizarLibro(libro);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminarLibro(id);
    }

}
