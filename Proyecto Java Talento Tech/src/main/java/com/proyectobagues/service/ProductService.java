package com.proyectobagues.service;

import com.proyectobagues.model.Producto;
import com.proyectobagues.repository.ProductMemRepository;
import com.proyectobagues.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductoRepository productoRepository;

  public ProductService(ProductoRepository repository) {this.productoRepository = repository;  }

  public Producto crearProducto(Producto producto) {

      return productoRepository.save(producto);
  }

  public List<Producto> listarProductos( String nombre, Double precio) {

    boolean hayNombre = (nombre != null && !nombre.isEmpty());
    boolean hayPrecio = (precio != null && precio > 0);

    if (hayNombre && hayPrecio) {
      return productoRepository.findByNombreContainingAndPrecioregularLessThanEqual(nombre, precio);
    }

    if (hayNombre) {
      return productoRepository.findByNombreContaining(nombre);
    }

    if (hayPrecio) {
      return productoRepository.findByPrecioregularLessThanEqual(precio);
    }

    return productoRepository.findAll();
  }

  public Producto editarNombreProducto(Long id, Producto data) {
    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    if (data.getNombre() != null) producto.setNombre(data.getNombre());
    if (data.getDescripcion() != null) producto.setDescripcion(data.getDescripcion());
    if (data.getPrecioregular() != 0) producto.setPrecioregular(data.getPrecioregular());
    if (data.getPreciodescuento() != 0) producto.setPreciodescuento(data.getPreciodescuento());
    if (data.getStock() != 0) producto.setStock(data.getStock());
    if (data.getImagen() != null) producto.setImagen(data.getImagen());

    return productoRepository.save(producto);
  }


  public Producto borrarProducto(Long id) {
    Optional<Producto> productOptional = this.productoRepository.findById(id);
    if (productOptional.isEmpty()) {
      System.out.println("No se puede borrar el producto. porque no se encontro");
      return null;
    }

    Producto producto = productOptional.get();

    this.productoRepository.delete(producto);
    // se puede usar el siguiente codigo, pero hay que manejar una excepcion(OptimisticLockingFailureException)
    //this.repository.deleteById(producto);

    System.out.println("Se borro correctamente el producto con id: " + producto.getId());
    return producto;
  }
}

