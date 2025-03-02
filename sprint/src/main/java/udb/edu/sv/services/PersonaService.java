package udb.edu.sv.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import udb.edu.sv.Persona;
import udb.edu.sv.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;


    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }


    public Optional<Persona> obtenerPorId(Long id) {
        return personaRepository.findById(id);
    }


    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }


    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }
}
