package cl.duoc.bibliotecaduoc.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Libro {
    
    private int id;
    private String isbn;
    private String titulo;
    private String editorial;
    private LocalDate fechaPublicacion;
    private String autor;

}
