package com.proyectobagues.service;

import com.proyectobagues.model.Producto;
import com.proyectobagues.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public Producto crearProducto(Producto producto) {
    return repository.guardarProducto(producto);
  }

  public List<Producto> listarProductos(String nombre, Integer precio) {

    if (nombre != null && !nombre.isBlank() && precio != null && precio > 0) {
      return repository.obtenerProductosPorNombreYPrecio(nombre, precio);
    }

    if (nombre != null && !nombre.isBlank()) {
      return repository.obtenerProductosPorNombre(nombre);
    }

    if (precio != null && precio > 0) {
      return repository.obtenerProductosPorPrecio(precio);
    }

    return repository.obtenerProductos();
  }

  public Producto editarNombreProducto(int id, Producto data) {

    Producto producto = repository.buscarProductoPorId(id);

    if (producto == null) {
      return null;
    }

    if (data.getNombre() == null || data.getNombre().isBlank()) {
      return null;
    }

    producto.setNombre(data.getNombre());
    repository.actualizarProducto(producto);

    return producto;
  }

  public void borrarProducto(int id) {

    Producto producto = repository.buscarProductoPorId(id);

    if (producto == null) {
      return;
    }

    repository.borrarProducto(producto);
  }
}

