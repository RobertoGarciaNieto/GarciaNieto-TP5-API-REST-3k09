package com.example.inicial1.services;

import com.example.inicial1.entities.Persona;
import com.example.inicial1.repositories.PersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements BaseService<Persona> {

    @Autowired // Esto es para la implementacion (se podría hacer también con el constructor)
    private PersonaRepository personaRepository;
    /*
    public PersonaService (PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }
     */

    @Override
    @Transactional
    public List<Persona> findAll() throws Exception {
        try {
            List<Persona> entities=personaRepository.findAll(); //Obtiene de la BD todas las personas que tengamos registradas
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona findById(Long id) throws Exception {
        try {
            Optional<Persona> entityOptional=personaRepository.findById(id);  //Se usa porque no sabemos si en la BD va a haber una entidad con el id
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona entity) throws Exception {
        try {
            entity = personaRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try {
             Optional<Persona> entityOptional = personaRepository.findById(id);
             Persona persona =entityOptional.get();
             persona = personaRepository.save(entity);
             return persona;
        } catch (Exception e) {
             throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if (personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }
}
