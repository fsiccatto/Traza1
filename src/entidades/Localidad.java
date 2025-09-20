package entidades;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Localidad {
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();
    private String nombre;

    @ToString.Exclude
    private Provincia provincia;

    @Builder.Default
    @ToString.Exclude
    private Set<Domicilio> domicilios = new HashSet<>();
}
