import entidades.*;
import repositorios.InMemoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Repo genérico (constructor: (getterId, setterId))
        InMemoryRepository<Empresa> empresaRepo =
                new InMemoryRepository<>(Empresa::getId, Empresa::setId);

        // ====== Creación de datos base ======
        Pais argentina = Pais.builder().nombre("Argentina").build();

        Provincia bsas     = Provincia.builder().nombre("Buenos Aires").pais(argentina).build();
        Provincia cordoba  = Provincia.builder().nombre("Córdoba").pais(argentina).build();

        Localidad caba       = Localidad.builder().nombre("CABA").provincia(bsas).build();
        Localidad laPlata    = Localidad.builder().nombre("La Plata").provincia(bsas).build();
        Localidad cbaCapital = Localidad.builder().nombre("Córdoba Capital").provincia(cordoba).build();
        Localidad vcp        = Localidad.builder().nombre("Villa Carlos Paz").provincia(cordoba).build();

        Domicilio domCaba    = Domicilio.builder().calle("Av. Siempre Viva").numero(742).cp(1001).localidad(caba).build();
        Domicilio domLaPlata = Domicilio.builder().calle("Calle 7").numero(777).cp(1900).localidad(laPlata).build();
        Domicilio domCba     = Domicilio.builder().calle("Av. Colón").numero(1234).cp(5000).localidad(cbaCapital).build();
        Domicilio domVcp     = Domicilio.builder().calle("Bv. Sarmiento").numero(321).cp(5152).localidad(vcp).build();

        Sucursal suc1 = Sucursal.builder().nombre("Sucursal CABA").esCasaMatriz(true).domicilio(domCaba).build();
        Sucursal suc2 = Sucursal.builder().nombre("Sucursal La Plata").esCasaMatriz(false).domicilio(domLaPlata).build();
        Sucursal suc3 = Sucursal.builder().nombre("Sucursal Córdoba Capital").esCasaMatriz(false).domicilio(domCba).build();
        Sucursal suc4 = Sucursal.builder().nombre("Sucursal Villa Carlos Paz").esCasaMatriz(false).domicilio(domVcp).build();

        Empresa emp1 = Empresa.builder()
                .nombre("Empresa 1")
                .razonSocial("Empresa 1 S.A.")
                .cuit(201234567)
                .logo("logo_emp1.png")
                .build();
        emp1.getSucursales().addAll(List.of(suc1, suc2));

        Empresa emp2 = Empresa.builder()
                .nombre("Empresa 2")
                .razonSocial("Empresa 2 S.R.L.")
                .cuit(209876543)
                .logo("logo_emp2.png")
                .build();
        emp2.getSucursales().addAll(List.of(suc3, suc4));

        // ====== Persistencia en repo ======
        empresaRepo.save(emp1);
        empresaRepo.save(emp2);

        // 1) Mostrar todas las empresas
        System.out.println("\n== Todas las empresas ==");
        empresaRepo.findAll().forEach(System.out::println);

        // 2) Buscar una empresa por su ID
        UUID idEmp1 = emp1.getId();
        System.out.println("\n== Buscar por ID (" + idEmp1 + ") ==");
        empresaRepo.findById(idEmp1)
                .ifPresentOrElse(
                        e -> System.out.println("Encontrada: " + e),
                        () -> System.out.println("No encontrada")
                );

        // 3) Buscar una empresa por nombre (SIN método del repo)
        System.out.println("\n== Buscar por nombre (Empresa 2) ==");
        List<Empresa> porNombre = empresaRepo.findAll().stream()
                .filter(e -> e.getNombre() != null && e.getNombre().equalsIgnoreCase("Empresa 2"))
                .collect(Collectors.toList());
        porNombre.forEach(System.out::println);

        // 4) Actualizar el CUIT de Empresa 1 (por ID)
        System.out.println("\n== Actualizar CUIT de Empresa 1 ==");
        emp1.setCuit(209999999);
        empresaRepo.update(idEmp1, emp1);
        empresaRepo.findById(idEmp1).ifPresent(e -> System.out.println("Actualizada: " + e));

        // 5) Eliminar Empresa 2 (por ID)
        System.out.println("\n== Eliminar Empresa 2 ==");
        UUID idEmp2 = emp2.getId();
        empresaRepo.delete(idEmp2);
        System.out.println("Empresas restantes:");
        empresaRepo.findAll().forEach(System.out::println);
    }
}
