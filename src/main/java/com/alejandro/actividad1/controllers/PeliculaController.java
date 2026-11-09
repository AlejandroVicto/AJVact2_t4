package com.alejandro.actividad1.controllers;

import com.alejandro.actividad1.models.dto.PeliculaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Value("${cine.nombre}")
    private String nombreCine;

    // Lista de películas en memoria
    private List<PeliculaDTO> peliculas = new ArrayList<>(Arrays.asList(
            new PeliculaDTO(1, "Inception", "Christopher Nolan", "Ciencia Ficcion"),
            new PeliculaDTO(2, "El Padrino", "Francis Ford Coppola", "Drama"),
            new PeliculaDTO(3, "Matrix", "Hermanas Wachowski", "Accion"),
            new PeliculaDTO(4, "Pulp Fiction", "Quentin Tarantino", "Crimen")));

    /**
     * Endpoint 1:
     * URL: http://localhost:8084/peliculas/cartelera
     * URL con filtro: http://localhost:8084/peliculas/cartelera?genero=Accion
     */
    @GetMapping("/cartelera")
    public String cartelera(@RequestParam(required = false) String genero, Model model) {
        List<PeliculaDTO> listaFiltrada;

        if (genero != null && !genero.trim().isEmpty()) {
            listaFiltrada = new ArrayList<>();
            for (PeliculaDTO p : peliculas) {
                if (p.getGenero() != null && p.getGenero().equalsIgnoreCase(genero.trim())) {
                    listaFiltrada.add(p);
                }
            }
            model.addAttribute("generoFiltro", genero.trim());
        } else {
            listaFiltrada = peliculas;
        }

        model.addAttribute("nombreCine", nombreCine);
        model.addAttribute("peliculas", listaFiltrada);
        model.addAttribute("nuevaPelicula", new PeliculaDTO());

        return "cartelera";
    }

    /**
     * Endpoint 2
     * URL: http://localhost:8084/peliculas/detalle/{id}
     */
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable int id, Model model) {
        PeliculaDTO peliculaEncontrada = null;

        for (PeliculaDTO p : peliculas) {
            if (p.getId() == id) {
                peliculaEncontrada = p;
                break;
            }
        }

        model.addAttribute("nombreCine", nombreCine);
        model.addAttribute("pelicula", peliculaEncontrada);

        return "detalle_pelicula";
    }

    /**
     * Endpoint 3
     * URL POST: http://localhost:8084/peliculas/guardar
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("nuevaPelicula") PeliculaDTO pelicula, Model model) {
        if (pelicula != null) {
            peliculas.add(pelicula);
        }

        model.addAttribute("nombreCine", nombreCine);
        model.addAttribute("peliculaCreada", pelicula);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("nuevaPelicula", new PeliculaDTO());
        model.addAttribute("mensajeExito", "¡Película agregada exitosamente a la cartelera!");

        return "cartelera";
    }

    /**
     * Endpoint 4
     * URL POST: http://localhost:8084/peliculas/api/peliculas
     */
    @PostMapping({ "/api/peliculas", "/api" })
    @ResponseBody
    public PeliculaDTO guardarPeliculaRest(@RequestBody PeliculaDTO pelicula) {
        if (pelicula != null) {
            peliculas.add(pelicula);
        }
        return pelicula;
    }
}
