package com.proyectobagues.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Producto {


  private String nombre;
  private double precioRegular;
  private double precioDescuento;
  private String tipo;
  private String descripcion;
  private String imagen;
  private String familiaOlfativa;
  private int stock;
  private int Id;

  // Constructor vacío necesario para Jackson
  public Producto() {
  }

  // Constructor completo (opcional)
  public Producto( String nombre, double precioRegular,
      double precioDescuento, String tipo, String descripcion,
      String imagen, String familiaOlfativa, int stock, int id) {


    this.nombre = nombre;
    this.precioRegular = precioRegular;
    this.precioDescuento = precioDescuento;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.imagen = imagen;
    this.familiaOlfativa = familiaOlfativa;
    this.stock = stock;
    this.Id = id;
  }

  @Override
  public String toString() {
    return nombre + " (" + Id + ")";
  }
}
