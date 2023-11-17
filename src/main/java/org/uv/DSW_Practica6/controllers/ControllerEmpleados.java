/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package org.uv.DSW_Practica6.controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.uv.DSW_Practica6.data.Empleado;
import org.uv.DSW_Practica6.data.RepositoryEmpleado;

/**
 *
 * @author ojeda
 */
@RestController
@RequestMapping("/api/empleado")
public class ControllerEmpleados {
    @Autowired
    RepositoryEmpleado repositoryEmpleado;
    
    
    @GetMapping("/")
    public List<Empleado> list() {
        return repositoryEmpleado.findAll();
    }
    
    @GetMapping("/{id}")
    public Empleado get(@PathVariable Long id) {
        Optional<Empleado> optRes = repositoryEmpleado.findById(id);
        return optRes.isPresent() ? optRes.get():null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping("/")
    public ResponseEntity<Empleado> post(@RequestBody Empleado emp) {
        Empleado _emp = repositoryEmpleado.save(emp);
        return ResponseEntity.ok(_emp);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
