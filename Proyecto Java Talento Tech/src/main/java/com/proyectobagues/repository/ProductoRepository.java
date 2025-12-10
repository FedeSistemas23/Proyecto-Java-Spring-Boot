package com.proyectobagues.repository;

import com.proyectobagues.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

  List<Producto> findByNombreContaining(String nombre);

  List<Producto> findByPrecioregularLessThanEqual(Double precio);

  List<Producto> findByNombreContainingAndPrecioregularLessThanEqual(String nombre, Double precio);

}


