package exercises;

import javax.ws.rs.GET;

public class Persona {
    private int Id;
    private String Nombre;
    private boolean Casado;
    private String Sexo;

    @GET
    public int getId() {
        return Id;
    }

    @GET
    public void setId(int id) {
        Id = id;
    }

    @GET
    public String getNombre() {
        return Nombre;
    }

    @GET
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @GET
    public boolean isCasado() {
        return Casado;
    }

    @SET
    public void setCasado(boolean casado) {
        Casado = casado;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }
}
