package entidades;
import lombok.*;

import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Provincia {
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();
    private String nombre;

    @ToString.Exclude
    private Pais pais;

    @Builder.Default
    @ToString.Exclude
    private Set<Localidad> localidades = new HashSet<>();
}
