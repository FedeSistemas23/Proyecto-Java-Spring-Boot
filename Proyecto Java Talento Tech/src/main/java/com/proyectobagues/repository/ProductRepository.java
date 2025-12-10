package com.proyectobagues.repository;


import com.google.gson.Gson;
import com.proyectobagues.model.Producto;
import java.net.URI;
import java.util.function.Predicate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Repository
public class ProductRepository {

  private static long nextId = 1L;
  private List<Producto> productos;

  public ProductRepository() {
    this.productos = cargarProductosDesdeApi();
  }

  /* ------------------ MÉTODOS PÚBLICOS ------------------ */

  public Producto guardarProducto(Producto producto) {
    this.updateId(producto);
    productos.add(producto);
    return producto;
  }

  public List<Producto> obtenerProductos() {
    return productos;
  }

  public List<Producto> obtenerProductosPorNombreYPrecio(String nombre, int precioTope) {
    return filtrarProductos(p ->
        coincideNombre(p.getNombre(), nombre) &&
            p.getPrecioregular() <= precioTope
    );
  }

  public List<Producto> obtenerProductosPorNombre(String nombre) {
    return filtrarProductos(p -> coincideNombre(p.getNombre(), nombre));
  }

  public List<Producto> obtenerProductosPorPrecio(int precioTope) {
    return filtrarProductos(p -> p.getPrecioregular() <= precioTope);
  }

  public Producto buscarProductoPorId(int id) {
    return productos.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public void borrarProducto(Producto producto) {
    productos.remove(producto);
  }

  public void actualizarProducto(Producto nuevoProducto) {
    for (int i = 0; i < productos.size(); i++) {
      Producto actual = productos.get(i);
      if (actual.getId() == nuevoProducto.getId()) {
        productos.set(i, nuevoProducto);
        return;
      }
    }
  }

  /* ------------------ MÉTODOS INTERNOS ------------------ */

  private boolean coincideNombre(String completo, String parcial) {
    return normalizar(completo).contains(normalizar(parcial));
  }

  private String normalizar(String texto) {
    return texto == null ? "" : texto.trim().toLowerCase();
  }

  private void asignarId(Producto producto) {
    producto.setId(nextId++);
  }

  private List<Producto> filtrarProductos(Predicate<Producto> filtro) {
    return productos.stream()
        .filter(filtro)
        .toList();
  }

  /* ------------------ CARGA INICIAL DESDE API ------------------ */

  private List<Producto> cargarProductosDesdeApi() {
    List<Producto> lista = new ArrayList<>();

    try {
     // URL url = new URL("");
      URL url = URI.create("https://6829df1bab2b5004cb350975.mockapi.io/imagenesBagues").toURL();

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder json = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        json.append(line);
      }

      br.close();

      Gson gson = new Gson();
      Producto[] productosArray = gson.fromJson(json.toString(), Producto[].class);

      lista.addAll(Arrays.asList(productosArray));

      // Asignar IDs internos
      for (Producto p : lista) {
        asignarId(p);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return lista;
  }
  private void updateId(Producto producto) {
    producto.setId(nextId);
    nextId++;
  }
}

