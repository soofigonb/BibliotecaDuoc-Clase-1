package cl.duoc.bibliotecaduoc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.bibliotecaduoc.service.LibroServicio;
import cl.duoc.bibliotecaduoc.model.Libro;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

  


@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroControlador {

    private final LibroServicio servicio;

    @GetMapping("") //http://localhost:8080/api/libros
    public List<Libro> obtenerTodos(){
        return servicio.obtenerTodos();
    }

    @GetMapping("/id/{id}")  //http://localhost:8080/api/libros/id/1
    public Libro obtenerPorId(@PathVariable int id) {
        return servicio.obtenerPorId(id);
    }

    @GetMapping("/buscar") //http://localhost:8080/api/libros/buscar?autor=l
    public List<Libro> buscarPorAutor(@RequestParam String autor){
        return servicio.buscarPorAutor(autor);
    }

    @GetMapping("/isbn/{isbn}") //http://localhost:8080/api/libros/isbn/978-84-376-0494-7
    public Libro obtenerPorIsbn(@PathVariable String isbn) {
        return servicio.obtenerPorIsbn(isbn);
    }
    
    

}
