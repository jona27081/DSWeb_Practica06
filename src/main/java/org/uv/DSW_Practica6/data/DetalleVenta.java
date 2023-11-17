/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DSW_Practica6.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ojeda
 */
@Data
@Entity
@Table(name = "detalleventas")
public class DetalleVenta{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalleventa_id_seq")
    @SequenceGenerator(name = "detalleventa_id_seq", sequenceName = "detalleventa_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "nombre")
    @Size(min = 3, max = 50, message = "El nombre debe ser mayor a 2 caracteres y menor a 50")
    private String nombre;
    @NotNull
    @Min(value = 1, message = "Precio no debe ser menor de 1MXM")
    @Max(value = 3000, message = "Precio no debe ser menor de 3,000MXM")
    @Column(name = "precio")
    private double precio;
    @NotNull
    @Min(value = 1, message = "Almenos debes llevar 1 unidad")
    @Max(value = 3000, message = "Maximo 40 unidades por persona")
    @Column(name = "cantidad")
    private double cantidad;
    @Column(name = "subtotal")
    private double subtotal;
    @ManyToOne
    @JoinColumn(name = "id_Venta")
    private Venta venta;

    public DetalleVenta(String nombre, double precio, double cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public DetalleVenta() {
        venta = new Venta();
    }

}
