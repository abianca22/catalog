package ro.pao.model;

public sealed interface Rol permits RolElev, RolProfesor{
    String afiseazaRol();
}
