package cl.duoc.bibliotecaduoc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.bibliotecaduoc.service.LibroServicio;
import cl.duoc.bibliotecaduoc.model.Libro;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroControlador {

    private final LibroServicio servicio;

    @GetMapping
    public List<Libro> obtenerTodos(){
        return servicio.obtenerTodos();
    }

}
