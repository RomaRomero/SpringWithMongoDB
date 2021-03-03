package com.MongoDB.model;


// declaracion de paquetes con la implementación de las dependencias de mongodb
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// declaración de tipo document y el tipo de coleccion o nombre que se le asignara al documentio
@Document (collection = "Persona")
// inicio de la clase persona
public class Persona {
    // atributos de tipo privado
    @Id
  private String id;
   private String nombre;
  private String apellido;
  private String descripción;
  private boolean publicación;
// constructor vacio
  public Persona(){

    
  } 
// constructor con datos  
  public Persona(String nombre , String apellido,String descripción, boolean publicación){
    this.nombre=nombre;
    this.apellido=apellido;
    this.descripción=descripción;
    this.publicación=publicación;
}    
// declaración de metodos getter y setter
public String getId(){
    return id;
}
public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  public String getDescripcion() {
    return descripción;
  }

  public void setDescripcion(String descripción) {
    this.descripción = descripción;
  }

  public boolean isPublicacion() {
    return publicación;
  }

  public void setPublicacion(boolean isPublicacion) {
    this.publicación = isPublicacion;
  }
//   metodo  de tipo string que recoge los valores 
  @Override
  public String toString() {
    return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido +", descripción=" + descripción + ", publicación=" + publicación + "]";
  }
}


 

  

