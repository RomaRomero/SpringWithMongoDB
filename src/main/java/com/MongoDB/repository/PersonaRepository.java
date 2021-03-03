package com.MongoDB.repository;


import java.util.List;
import com.MongoDB.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona , String>{

    List<Persona> findByNombre(String nombre);
    List<Persona> findByPublicación(boolean publicación);
  }
    


