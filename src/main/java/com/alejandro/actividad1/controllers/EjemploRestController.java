package com.alejandro.actividad1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.actividad1.models.Empleados;
import com.alejandro.actividad1.models.dto.ClaseDTO;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api") 

public class EjemploRestController {

    @GetMapping(path="/detalles_info2")    
    
    public ClaseDTO detalles_info2() {

      //  Empleados empleado1 = new Empleados("Alejandro", "Jimenez", "Calle 123", "Desarrollador", 22, 12345, 001);

       // Map<String, Object> respuesta = new HashMap<>();
     //   respuesta.put("Empleado", "Datos empleado");
      //  respuesta.put("Informacion", empleado1);
        
        ClaseDTO usuario1 = new ClaseDTO();
        usuario1.setTitulo("Administrador");
        usuario1.setUsuario("Alejandro");


        return usuario1;
    } 
}
