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
        List<Libro> lista = servicio.obtenerTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        try {
            return ResponseEntity.ok(servicio.obtenerPorId(id));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(List.of(e.getMessage()));
        }
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<?> buscarPorAutor(@RequestParam String autor) {
        try {
            List<Libro> lista = servicio.buscarPorAutor(autor);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(lista);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> obtenerPorIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok(servicio.obtenerPorIsbn(isbn));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(List.of(e.getMessage()));
        }
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<?> buscarPorTitulo(@RequestParam String palabra) {
        try {
            List<Libro> lista = servicio.buscarPorTitulo(palabra);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(lista);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> guardarLibro(@RequestBody Libro libro) {
        try {
            return ResponseEntity.status(201).body(servicio.guardarLibro(libro));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(servicio.actualizarLibro(libro));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(List.of(e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            servicio.eliminarLibro(id);
            return ResponseEntity.ok(List.of("Libro eliminado correctamente"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(List.of(e.getMessage()));
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

            return ResponseEntity.ok(lista);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of("Formato de fecha inválido (yyyy-MM-dd)"));
        }
    }

    @GetMapping("/buscar/editorial")
    public ResponseEntity<?> buscarPorEditorial(@RequestParam String editorial) {
        try {
            List<Libro> lista = servicio.buscarPorEditorial(editorial);

            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(lista);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> totalLibros(){
        try {
            int total = servicio.totalLibros();
            return ResponseEntity.ok(total);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(List.of("Error al obtener el total de libros"));
        }
    }
}
