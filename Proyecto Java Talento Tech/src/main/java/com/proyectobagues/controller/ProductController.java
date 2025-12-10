package com.proyectobagues.controller;

import com.proyectobagues.model.Producto;
import com.proyectobagues.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class  ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
    Producto nuevo = service.crearProducto(producto);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
  }

  @GetMapping
  public List<Producto> listarProductos(
      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) Double precio) {
    try {
      return service.listarProductos(nombre, precio);
    } catch (Exception e) {
      e.printStackTrace(); // ⬅️ Acá se imprime el stacktrace
      throw e; // vuelve a lanzar la excepción para que Spring responda 500
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Producto> editarProducto(
      @PathVariable long id,
      @RequestBody Producto producto) {

    return ResponseEntity.ok(service.editarNombreProducto(id, producto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> borrarProducto(@PathVariable long id) {
    service.borrarProducto(id);
    return ResponseEntity.noContent().build();
  }
}

