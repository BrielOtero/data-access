import java.io.Serializable;

public class Departamento extends Componente implements Serializable {
    int codigo;
    String nombre;

    public Departamento(String nombre) {
        incrementId();
        this.codigo = getId();
        this.nombre = nombre;
    }

}
