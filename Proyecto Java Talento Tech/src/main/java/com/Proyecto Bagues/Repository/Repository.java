package com.techlab.demo.repository;

import com.techlab.demo.model.Producto;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private static int nextId = 1;
  ArrayList<Producto> productos;

  public ProductRepository() {
    this.productos = obtenerProductosTecnologicos();
  }

  public Producto guardarProducto(Producto producto) {
    this.updateId(producto);
    productos.add(producto);
    return producto;
  }

  public ArrayList<Producto> obtenerProductos() {
    return this.productos;
  }

  public ArrayList<Producto> obtenerProductosPorNombreYPrecio(String nombre, int precioTope) {
    ArrayList<Producto> productoEncontrados = new ArrayList<>();
    for (Producto producto : this.productos) {
      if (estaIncluido(producto.getNombre(), nombre) && producto.getPrecio() <= precioTope) {
        productoEncontrados.add(producto);
      }
    }

    return productoEncontrados;
  }

  public ArrayList<Producto> obtenerProductosPorNombre(String nombre) {
    ArrayList<Producto> productoEncontrados = new ArrayList<>();

    for (Producto producto : this.productos) {
      if (estaIncluido(producto.getNombre(), nombre)) {
        productoEncontrados.add(producto);
      }
    }

    return productoEncontrados;
  }

  public ArrayList<Producto> obtenerProductosPorPrecio(int precioTope) {
    ArrayList<Producto> productosFiltrados = new ArrayList<>();

    for (Producto producto : productos) {
      if (producto.getPrecio() <= precioTope) {
        productosFiltrados.add(producto);
      }
    }

    return productosFiltrados;
  }

  public Producto buscarProductoPorId(int id) {
    for (Producto producto : productos) {
      if (producto.getId() == id) {
        return producto;
      }
    }

    return null;
  }

  public Producto borrarProducto(Producto producto) {
    this.productos.remove(producto);
    return producto;
  }

  public void actualizarProducto(Producto producto) {
    System.out.println("se actualizo el producto con id: " + producto.getId());
  }

  // Logica interna del repository
  private boolean estaIncluido(String nombreCompleto, String nombreParcial) {
    String nombreCompletoFormateado = formatoBusqueda(nombreCompleto);

    return nombreCompletoFormateado.contains(formatoBusqueda(nombreParcial));
  }

  private String formatoBusqueda(String texto) {
    return texto.trim().toLowerCase();
  }

  private ArrayList<Producto> obtenerProductosTecnologicos() {
    ArrayList<Producto> productos = new ArrayList<>();

    try {
      URL url = new URL("https://tu-endpoint.mockapi.io/productos");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      BufferedReader br = new BufferedReader(
          new InputStreamReader(conn.getInputStream())
      );

      StringBuilder response = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        response.append(line);
      }

      br.close();

      // Convertir JSON a lista de productos
      Gson gson = new Gson();
      Producto[] productosArray = gson.fromJson(response.toString(), Producto[].class);

      productos.addAll(Arrays.asList(productosArray));

    } catch (Exception e) {
      e.printStackTrace();

    for (Producto producto : productos) {
      this.updateId(producto);
    }

    return productos;
  }

  private void updateId(Producto producto) {
    producto.setId(nextId);
    nextId++;
  }
}
