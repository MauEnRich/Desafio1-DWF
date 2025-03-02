package udb.edu.sv;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    private String nombrePersona;

    private int edadPersona;

    private String telefonoPersona;

    private String sexoPersona;

    private Integer idOcupacion;

    private Date fechaNac;
}
