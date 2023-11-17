/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DSW_Practica6.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ojeda
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "ventas")
public class Venta{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_id_seq")
    @SequenceGenerator(name = "venta_id_seq", sequenceName = "venta_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "fecha")
    private Date fecha;
    @NotNull
    @Column(name = "total")
    private double total;
    @OneToMany(mappedBy= "venta" , fetch = FetchType.LAZY)
    @JsonBackReference
    private List<DetalleVenta> detalleList;

    public Venta(List<DetalleVenta> detalleList) {
        this.detalleList = detalleList;
    }

    public void rellenarVenta() {
        double suma = 0;
        this.fecha = Date.valueOf(LocalDate.now());
        for (DetalleVenta detVent : this.detalleList) {
            suma += (detVent.getCantidad() * detVent.getPrecio());
        }
        this.total = suma;
    }
}
