package cl.duoc.bibliotecaduoc.controller;

import java.time.LocalDate;
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
    public ResponseEntity<?> actualizar(@RequestBody Libro libro) {
        try {
            Libro actualizado = servicio.actualizarLibro(libro);

            if (actualizado == null) {
                return ResponseEntity.status(404).body("No se pudo actualizar el libro");
            }

            return ResponseEntity.status(200).body(actualizado);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            boolean eliminado = servicio.eliminarLibro(id);

            if (!eliminado) {
                return ResponseEntity.status(404).body("Libro no encontrado");
            }

            return ResponseEntity.status(200).body("Libro eliminado correctamente");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/buscar/fecha")
    public ResponseEntity<?> buscarPorFecha(@RequestParam String fecha) {
        try {
            LocalDate fechaParseada = LocalDate.parse(fecha);

            List<Libro> lista = servicio.buscarPorFecha(fechaParseada);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(200).body(lista);

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Formato de fecha inválido (yyyy-MM-dd)");
        }
    }
    
    @GetMapping("/buscar/editorial")
    public ResponseEntity<?> buscarPorEditorial(@RequestParam String editorial) {
        try {
            List<Libro> lista = servicio.buscarPorEditorial(editorial);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.status(200).body(lista);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

}
