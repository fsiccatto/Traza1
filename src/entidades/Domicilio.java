package entidades;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Domicilio {
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();

    private String calle;
    private Integer numero;
    private Integer cp;

    @ToString.Exclude
    private Localidad localidad;
}
