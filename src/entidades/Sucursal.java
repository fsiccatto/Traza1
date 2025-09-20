package entidades;

import lombok.*;

import java.util.UUID;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sucursal {
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();

    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;

    @ToString.Exclude
    private Empresa empresa;

    private Domicilio domicilio;
}
