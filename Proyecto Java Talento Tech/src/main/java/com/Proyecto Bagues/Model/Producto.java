package com.techlab.demo.model;

public class Producto {

  private int id;
  private String nombre;
  private double precioRegular;
  private double precioDescuento;
  private String tipo;
  private String descripcion;
  private String imagen;
  private String type;
  private String familiaOlfativa;
  private int stock;

  // metodo constructor
  public Producto(String nombre, double precioRegular, double precioDescuento,
      String tipo, String descripcion, String imagen,
      String type, String familiaOlfativa, int stock) {
    this.id = Producto.nextId++;
    this.nombre = nombre;
    this.precioRegular = precioRegular;
    this.precioDescuento = precioDescuento;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.imagen = imagen;
    this.type = type;
    this.familiaOlfativa = familiaOlfativa;
    this.stock = stock;
  }
  // ================================
  // Getters y Setters
  // ================================

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    if (nombre == null || nombre.isBlank()) {
      System.out.println("⚠️ El nombre no puede estar vacío.");
      return;
    }
    this.nombre = nombre;
  }

  public double getPrecioRegular() {
    return precioRegular;
  }

  public void setPrecioRegular(double precioRegular) {
    if (precioRegular <= 0) {
      System.out.println("⚠️ El precio regular debe ser mayor a cero.");
      return;x
    }
    this.precioRegular = precioRegular;
  }

  public double getPrecioDescuento() {
    return precioDescuento;
  }

  public void setPrecioDescuento(double precioDescuento) {
    if (precioDescuento < 0 || precioDescuento > precioRegular) {
      System.out.println("⚠️ El precio con descuento no puede ser mayor que el precio regular ni menor a cero.");
      return;
    }
    this.precioDescuento = precioDescuento;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    if (tipo == null || tipo.isBlank()) {
      System.out.println("⚠️ El tipo no puede estar vacío.");
      return;
    }
    this.tipo = tipo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    if (descripcion.length() > 500) {
      System.out.println("⚠️ La descripción no puede tener más de 500 caracteres.");
      return;
    }
    this.descripcion = descripcion;
  }

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    if (imagen == null || imagen.isBlank()) {
      System.out.println("⚠️ La URL de imagen no puede estar vacía.");
      return;
    }
    this.imagen = imagen;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    if (type == null || type.isBlank()) {
      System.out.println("⚠️ El campo 'type' no puede estar vacío.");
      return;
    }
    this.type = type;
  }

  public String getFamiliaOlfativa() {
    return familiaOlfativa;
  }

  public void setFamiliaOlfativa(String familiaOlfativa) {
    if (familiaOlfativa == null || familiaOlfativa.isBlank()) {
      System.out.println("⚠️ La familia olfativa no puede estar vacía.");
      return;
    }
    this.familiaOlfativa = familiaOlfativa;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    if (stock < 0) {
      System.out.println("⚠️ El stock no puede ser negativo.");
      return;
    }
    this.stock = stock;
  }

  public boolean coincideId(int id) {
    return this.id == id;
  }

  // ================================
  // Método toString (opcional)
  // ================================

  @Override
  public String toString() {
    return String.format("""
                ID: %d
                Nombre: %s
                Precio Regular: $%.2f
                Precio Descuento: $%.2f
                Tipo: %s
                Familia Olfativa: %s
                Descripción: %s
                Type: %s
                Imagen: %s
                Stock: %d
                """,
        id, nombre, precioRegular, precioDescuento, tipo,
        familiaOlfativa, descripcion, type, imagen, stock);
  }
}
