package udb.edu.sv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udb.edu.sv.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
