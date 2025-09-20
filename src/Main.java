import entidades.*;

public class Main {
    public static void main(String[] args) {
        Pais argentina = Factory.crearPais("Argentina");

        Provincia mendoza = Factory.crearProvincia("Mendoza", argentina);
        Localidad ciudad = Factory.crearLocalidad("Ciudad", mendoza);
        Domicilio domCiudad = Factory.crearDomicilio("Carlos Pellegrini", 742, 5000, ciudad);

        Localidad lujanDeCuyo = Factory.crearLocalidad("Lujan de Cuyo", mendoza);
        Domicilio domLujanDeCuyo = Factory.crearDomicilio("Almirante Brown", 777, 5500, lujanDeCuyo);

        Provincia cordoba = Factory.crearProvincia("Córdoba", argentina);
        Localidad cbaCapital = Factory.crearLocalidad("Córdoba Capital", cordoba);
        Domicilio domCbaCapital = Factory.crearDomicilio("Av. Colón", 1234, 5000, cbaCapital);

        Localidad vcp = Factory.crearLocalidad("Villa Carlos Paz", cordoba);
        Domicilio domVcp = Factory.crearDomicilio("Bv. Sarmiento", 321, 5152, vcp);

        Sucursal suc1 = Factory.crearSucursal("Sucursal Mendoza", true, domCiudad);
        Sucursal suc2 = Factory.crearSucursal("Sucursal Lujan de Cuyo", false, domLujanDeCuyo);
        Sucursal suc3 = Factory.crearSucursal("Sucursal Córdoba Capital", false, domCbaCapital);
        Sucursal suc4 = Factory.crearSucursal("Sucursal Villa Carlos Paz", false, domVcp);

        Empresa emp1 = Factory.crearEmpresa("Empresa 1", "Empresa 1 S.A.", 201234567, "img1.png");
        emp1.getSucursales().add(suc1);
        emp1.getSucursales().add(suc2);

        Empresa emp2 = Factory.crearEmpresa("Empresa 2", "Empresa 2 S.R.L.", 209876543, "img2.png");
        emp2.getSucursales().add(suc3);
        emp2.getSucursales().add(suc4);

        System.out.println(emp1.getNombre() + " sucursales: " + emp1.getSucursales().size());
        System.out.println(emp2.getNombre() + " sucursales: " + emp2.getSucursales().size());
    }
}