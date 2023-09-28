package usm.hdd.Taller1.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("clientes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String nombre;
    private String apellidos;
    private String rut;
    private Integer numSerie;
    private String estado;
}
