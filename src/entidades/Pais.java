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
public class Pais {
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();
    private String nombre;

    @Builder.Default
    @ToString.Exclude
    private Set<Provincia> provincias = new HashSet<>();
}
