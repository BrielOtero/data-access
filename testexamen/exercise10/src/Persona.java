import java.io.Serializable;

public class Persona extends Componente implements Serializable {
    int codigo;
    String nombre;

    public Persona(String nombre) {
        incrementId();
        this.codigo = getId();
        this.nombre = nombre;
    }

}
