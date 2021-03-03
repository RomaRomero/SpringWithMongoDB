package com.MongoDB.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.MongoDB.model.Persona;

import com.MongoDB.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class PersonaController {
    @Autowired
    PersonaRepository personaRepository;

        // metodo de tipo get para llamar a todos los campos almacenados en la base de datos 
        // implementando control de excepciones

    @GetMapping("/persona")
        public ResponseEntity<List<Persona>> getAllPersonas(@RequestParam(required = false) String nombre) {
        try {
    List<Persona> persona = new ArrayList <Persona>();
    if (nombre == null)
      personaRepository.findAll().forEach(persona::add);
    else
      personaRepository.findByNombre(nombre).forEach(persona::add);

    if (persona.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(persona, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

    // metodo de tipo get para obtener un solo dato almacenado en la base de datos por parametro id

  @GetMapping("/personas/{id}")
public ResponseEntity<Persona> getPersonaById(@PathVariable("id") String id) {
  Optional<Persona> personaData = personaRepository.findById(id);

  if (personaData.isPresent()) {
    return new ResponseEntity<>(personaData.get(), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

// metodo get para llamar por parametro booleano de publicación
@GetMapping("/persona/publicacion")
public ResponseEntity<List<Persona>> findByPublicació() {
  try {
    List<Persona> persona = personaRepository.findByPublicación(true);

    if (persona.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(persona, HttpStatus.OK);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}


//   Post sirve para insertar un campo en la base de datos

  @PostMapping("/personas")
  public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
    try {
      Persona _persona = personaRepository.save(new Persona(persona.getNombre() ,persona.getApellido() , persona.getDescripcion() , false));
      return new ResponseEntity<>(_persona, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


// llama un campo de la base de datos para actualizar los valores registrados 
  @PutMapping("/tutorials/{id}")
public ResponseEntity<Persona> updatePersona(@PathVariable("id") String id, @RequestBody Persona persona) {
  Optional<Persona> personaData = personaRepository.findById(id);

  if (personaData.isPresent()) {
    Persona _persona = personaData.get();
    _persona.setNombre(persona.getNombre());
    _persona.setApellido(persona.getApellido());
    _persona.setDescripcion(persona.getDescripcion());
    _persona.setPublicacion(persona.isPublicacion());
    return new ResponseEntity<>(personaRepository.save(_persona), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

// metodo para eliminar por parametro id

  @DeleteMapping("/personas/{id}")
public ResponseEntity<HttpStatus> deletePersona(@PathVariable("id") String id) {
  try {
    personaRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

// metodo para eliminar todos los datos
  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllPersonas() {
    try {
      personaRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
    
}

  

 