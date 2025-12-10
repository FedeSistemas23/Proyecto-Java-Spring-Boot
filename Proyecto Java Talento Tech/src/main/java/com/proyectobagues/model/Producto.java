package com.proyectobagues.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String tipo;
  private String descripcion;
  private String imagen;
  private double precioregular;
  private double preciodescuento;
  private String type;
  private String familiaolfativa;
  private int stock;
  // Constructor vacío necesario para Jackson
  public Producto() {
  }

  // Constructor completo (opcional)
  public Producto( Long id, String nombre, String tipo, String descripcion,String imagen,
      double precioRegular, double precioDescuento, String type,
      String familiaOlfativa, int stock) {

    this.id = id;
    this.nombre = nombre;
    this.precioregular = precioRegular;
    this.preciodescuento = precioDescuento;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.imagen = imagen;
    this.familiaolfativa = familiaOlfativa;
    this.stock = stock;
  }

  @Override
  public String toString() {
    return nombre + " (" + id + ")";
  }
}
