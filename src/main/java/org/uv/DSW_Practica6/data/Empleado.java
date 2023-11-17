/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DSW_Practica6.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "empleado2")
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado2_id_seq")
    @SequenceGenerator(name = "empleado2_id_seq", sequenceName = "empleado2_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "nombre")
    private String name;
    @NotNull
    @Column(name = "direccion")
    private String direccion;
    @NotNull
    @Column(name = "telefono")
    private String telefono;

    public Empleado(Long id, String name, String direccion, String telefono) {
        this.id = id;
        this.name = name;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    
    
}
