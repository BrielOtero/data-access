import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Scanner;

public class Manager {
    File f;

    public Manager(String path) {
        this.f = new File(path);
    }

    public void añadirObjeto(Object object) {
        try (FileOutputStream dos = new FileOutputStream(f, true);
                ObjectOutputStream out = f.length() == 0 ? new ObjectOutputStream(dos)
                        : new AppendingObjectOutputStream(dos);) {

            out.writeObject(object);

        } catch (Exception e) {
            System.out.println("Error guardando objeto: " + e.getMessage());
        }
    }

    public void leerObjetos(boolean mostrarDepartamento, boolean mostrarPersona) {
        Object obj;

        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fis);) {

            while (true) {
                obj = in.readObject();

                if (obj instanceof Departamento) {
                    if (mostrarDepartamento) {
                        System.out.println("Departamento con Codigo: " + ((Departamento) obj).codigo + " y Nombre: "
                                + ((Departamento) obj).nombre);
                    }
                } else if (obj instanceof Persona) {
                    if (mostrarPersona) {
                        System.out.println("Persona con Codigo: " + ((Persona) obj).codigo + " y Nombre: "
                                + ((Persona) obj).nombre);
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    public void borrar(boolean esDepartamento) {
        Scanner sc = new Scanner(System.in);
        if (esDepartamento) {
            leerObjetos(true, false);
        } else {
            leerObjetos(false, true);
        }

        boolean correct = true;
        int indiceParaBorrar = -1;

        do {
            if (!correct) {
                System.out.println("Elige un indice valido");
            }
            correct = true;
            try {

                System.out.println("Elige el codigo para borrar");
                indiceParaBorrar = sc.nextInt();

                if (!objetosContieneElIndice(indiceParaBorrar, esDepartamento)) {
                    correct = false;
                }

            } catch (Exception e) {
                correct = false;
            }
        } while (!correct);

        Object readObj;

        File temp = new File(f.getPath() + File.pathSeparator + "temp.txt");
        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(temp);
                ObjectOutputStream out = temp.length() == 0 ? new ObjectOutputStream(fos)
                        : new AppendingObjectOutputStream(fos)) {
            while (true) {
                readObj = in.readObject();
                if (readObj instanceof Departamento) {

                    if (esDepartamento) {
                        if (((Departamento) readObj).codigo != indiceParaBorrar) {
                            out.writeObject(readObj);
                        }
                    } else {
                        out.writeObject(readObj);
                    }
                } else if (readObj instanceof Persona) {
                    if (!esDepartamento) {
                        if (((Persona) readObj).codigo != indiceParaBorrar) {
                            out.writeObject(readObj);
                        }
                    } else {
                        out.writeObject(readObj);
                    }
                }
            }

        } catch (Exception e) {
        }
        String path = f.getAbsolutePath().toString();
        f.delete();
        temp.renameTo(new File(path));
    }

    public boolean objetosContieneElIndice(int indice, boolean esDepartamento) {
        Object obj;
        boolean contieneElIndice = false;

        try (FileInputStream fis = new FileInputStream(f);
                ObjectInputStream in = new ObjectInputStream(fis);) {

            while (true) {
                obj = in.readObject();

                if (obj instanceof Departamento) {
                    if (esDepartamento && indice == ((Departamento) obj).codigo) {
                        contieneElIndice = true;
                        break;
                    }
                } else if (obj instanceof Persona) {
                    if (!esDepartamento && indice == ((Persona) obj).codigo) {
                        contieneElIndice = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
        }

        return contieneElIndice;
    }

    class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();// do not write a header
        }
    }
}
