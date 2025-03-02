package udb.edu.sv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import udb.edu.sv.repository.PersonaRepository;
import udb.edu.sv.services.PersonaService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
public class PersonaServiceTest {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personaRepository;

    private Persona persona;

    @BeforeEach
    public void setUp() {
        Optional<Persona> optionalPersona = personaRepository.findById(35L);
        if (optionalPersona.isPresent()) {
            persona = optionalPersona.get();
        } else {

            persona = new Persona();
            persona.setNombrePersona("Mauricio");
            persona.setEdadPersona(25);
            persona.setTelefonoPersona("123456789");
            persona.setSexoPersona("Masculino");
            persona.setIdOcupacion(1);
            persona.setFechaNac(java.sql.Date.valueOf("1998-04-20"));
            persona = personaService.guardar(persona);
        }
    }

    @Test
    public void testGuardarPersona() {
        // Guardamos la persona que ya hemos configurado.
        Persona savedPersona = personaService.guardar(persona);
        assertNotNull(savedPersona);
        assertEquals("Mauricio", savedPersona.getNombrePersona());
    }

    @Test
    public void testObtenerPersonaPorId() {
        // Paso 1: Usar un ID que ya existe en la base de datos.
        Long idExistente = 35L;  // Cambia esto por un ID que esté en tu base de datos.

        // Paso 2: Obtener la persona con el ID existente.
        Optional<Persona> foundPersona = personaService.obtenerPorId(idExistente);

        // Paso 3: Verificar que la persona fue encontrada.
        assertTrue(foundPersona.isPresent(), "La persona no fue encontrada con el ID: " + idExistente);

        // Paso 4: Verificar que el nombre de la persona sea correcto (ajusta según lo que esperas de la persona con el ID).
        assertEquals("Mauricio", foundPersona.get().getNombrePersona(), "El nombre de la persona no es el esperado.");

        // Mensaje de confirmación si la prueba es exitosa
        System.out.println("La persona con ID " + idExistente + " y nombre " + foundPersona.get().getNombrePersona() + " fue encontrada correctamente.");
    }


    @Test
    public void testActualizarPersona() {

        Optional<Persona> existingPersonaOpt = personaService.obtenerPorId(35L);


        assertTrue(existingPersonaOpt.isPresent(), "La persona no fue encontrada en la base de datos.");

        Persona existingPersona = existingPersonaOpt.get();

        // Actualizar todos los campos
        existingPersona.setNombrePersona("Juan");
        existingPersona.setEdadPersona(35);
        existingPersona.setTelefonoPersona("987654321");
        existingPersona.setSexoPersona("Masculino");
        existingPersona.setIdOcupacion(2);
        existingPersona.setFechaNac(java.sql.Date.valueOf("1985-07-15"));


        Persona updatedPersona = personaService.guardar(existingPersona);

        // Verificar que todos los campos se hayan actualizado correctamente
        assertEquals("Juan", updatedPersona.getNombrePersona(), "El nombre de la persona no fue actualizado.");
        assertEquals(35, updatedPersona.getEdadPersona(), "La edad de la persona no fue actualizada.");
        assertEquals("987654321", updatedPersona.getTelefonoPersona(), "El teléfono de la persona no fue actualizado.");
        assertEquals("Masculino", updatedPersona.getSexoPersona(), "El sexo de la persona no fue actualizado.");
        assertEquals(2, updatedPersona.getIdOcupacion(), "La ocupación de la persona no fue actualizada.");
        assertEquals(java.sql.Date.valueOf("1985-07-15"), updatedPersona.getFechaNac(), "La fecha de nacimiento de la persona no fue actualizada.");


        System.out.println("La prueba fue exitosa. Persona actualizada correctamente.");
    }


    @Test
    public void testEliminarPersona() {
        // Paso 1: Usar un ID que ya existe en la base de datos.
        Long idExistente = 35L;

        // Paso 2: Verificar que la persona con ese ID existe.
        Optional<Persona> personaExistente = personaService.obtenerPorId(idExistente);
        assertTrue(personaExistente.isPresent(), "La persona no existe en la base de datos.");

        // Paso 3: Eliminar la persona utilizando su ID.
        personaService.eliminar(idExistente);

        // Paso 4: Verificar que la persona fue eliminada correctamente.
        Optional<Persona> deletedPersona = personaService.obtenerPorId(idExistente);
        assertFalse(deletedPersona.isPresent(), "La persona no fue eliminada correctamente.");


        System.out.println("Prueba exitosa: La persona con ID " + idExistente + " fue eliminada correctamente.");
    }

}
