/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package org.uv.DSW_Practica6.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.uv.DSW_Practica6.data.DTOVistaVenta;
import org.uv.DSW_Practica6.data.DetalleVenta;
import org.uv.DSW_Practica6.data.RepositoryDetalleVenta;
import org.uv.DSW_Practica6.data.RepositoryVenta;
import org.uv.DSW_Practica6.data.Venta;

/**
 *
 * @author ojeda
 */
@RestController
@RequestMapping("/api/ventas")
public class ControllerVenta {

    @Autowired
    RepositoryDetalleVenta detalleVentaRepository;
    @Autowired
    RepositoryVenta ventRepository;

    @GetMapping("/")
    public List<DTOVistaVenta> list() {
        List<Venta> data = ventRepository.findAll();
        List<DTOVistaVenta> lista = new ArrayList<>();
        for (Venta v : data) {
            DTOVistaVenta ventaVista = new DTOVistaVenta();
            List<DetalleVenta> list = detalleVentaRepository.findByVentaId(v.getId());
            ventaVista.llenarVista(v, list);
            lista.add(ventaVista);
        }
        return lista;
    }

    @GetMapping("/{id}")
    public DTOVistaVenta get(@PathVariable Long id) {
        Optional<Venta> v = ventRepository.findById(id);
        DTOVistaVenta ventaVista = new DTOVistaVenta();
            List<DetalleVenta> list = detalleVentaRepository.findByVentaId(id);
            ventaVista.llenarVista(v.get(), list);
            return ventaVista;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @Transactional
    @PostMapping("/")
    public ResponseEntity<Venta> post(@RequestBody Venta vent) {
        System.out.println("Venta recibida: " + vent);

        try {
            vent.rellenarVenta();
            Venta ventaSave = ventRepository.save(vent);
            for (DetalleVenta detVent : vent.getDetalleList()) {
                detVent.setSubtotal(detVent.getCantidad() * detVent.getPrecio());
                detVent.setVenta(vent);
                detalleVentaRepository.save(detVent);
            }

            return new ResponseEntity<>(ventaSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Hola
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
