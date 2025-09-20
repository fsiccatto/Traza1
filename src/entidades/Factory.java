package entidades;

import java.time.LocalTime;
import java.util.HashSet;

public class Factory {

    public static Pais crearPais(String nombre) {
        return Pais.builder()
                .nombre(nombre)
                .build();
    }

    public static Provincia crearProvincia(String nombre, Pais pais) {
        return Provincia.builder()
                .nombre(nombre)
                .pais(pais)
                .build();
    }

    public static Localidad crearLocalidad(String nombre, Provincia provincia) {
        return Localidad.builder()
                .nombre(nombre)
                .provincia(provincia)
                .build();
    }

    public static Domicilio crearDomicilio(String calle, int numero, int cp, Localidad localidad) {
        return Domicilio.builder()
                .calle(calle)
                .numero(numero)
                .cp(cp)
                .localidad(localidad)
                .build();
    }

    public static Sucursal crearSucursal(String nombre, boolean esCasaMatriz, Domicilio domicilio) {
        return Sucursal.builder()
                .nombre(nombre)
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(esCasaMatriz)
                .domicilio(domicilio)
                .build();
    }

    public static Empresa crearEmpresa(String nombre, String razonSocial, int cuit, String logo) {
        return Empresa.builder()
                .nombre(nombre)
                .razonSocial(razonSocial)
                .cuit(cuit)
                .logo(logo)
                .sucursales(new HashSet<>())
                .build();
    }
}
