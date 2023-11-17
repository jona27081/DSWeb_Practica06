/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/Repository.java to edit this template
 */
package org.uv.DSW_Practica6.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ojeda
 */
public interface RepositoryDetalleVenta extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByVentaId(Long id);
}
