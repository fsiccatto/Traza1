import entidades.*;

public class Main {
    public static void main(String[] args) {
        Pais argentina = Factory.crearPais("Argentina");

        Provincia bsas = Factory.crearProvincia("Buenos Aires", argentina);
        Localidad caba = Factory.crearLocalidad("CABA", bsas);
        Domicilio domCaba = Factory.crearDomicilio("Av. Siempre Viva", 742, 1001, caba);

        Localidad laPlata = Factory.crearLocalidad("La Plata", bsas);
        Domicilio domLaPlata = Factory.crearDomicilio("Calle 7", 777, 1900, laPlata);

        Provincia cordoba = Factory.crearProvincia("Córdoba", argentina);
        Localidad cbaCapital = Factory.crearLocalidad("Córdoba Capital", cordoba);
        Domicilio domCbaCapital = Factory.crearDomicilio("Av. Colón", 1234, 5000, cbaCapital);

        Localidad vcp = Factory.crearLocalidad("Villa Carlos Paz", cordoba);
        Domicilio domVcp = Factory.crearDomicilio("Bv. Sarmiento", 321, 5152, vcp);

        Sucursal suc1 = Factory.crearSucursal("Sucursal CABA", true, domCaba);
        Sucursal suc2 = Factory.crearSucursal("Sucursal La Plata", false, domLaPlata);
        Sucursal suc3 = Factory.crearSucursal("Sucursal Córdoba Capital", false, domCbaCapital);
        Sucursal suc4 = Factory.crearSucursal("Sucursal Villa Carlos Paz", false, domVcp);

        Empresa emp1 = Factory.crearEmpresa("Empresa 1", "Empresa 1 S.A.", 201234567, "logo_emp1.png");
        emp1.getSucursales().add(suc1);
        emp1.getSucursales().add(suc2);

        Empresa emp2 = Factory.crearEmpresa("Empresa 2", "Empresa 2 S.R.L.", 209876543, "logo_emp2.png");
        emp2.getSucursales().add(suc3);
        emp2.getSucursales().add(suc4);

        System.out.println(emp1.getNombre() + " sucursales: " + emp1.getSucursales().size());
        System.out.println(emp2.getNombre() + " sucursales: " + emp2.getSucursales().size());
    }
}
