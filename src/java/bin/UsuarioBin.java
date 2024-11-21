
package bin;


public class UsuarioBin {
    private int id;
    private String nombre;
    private String curp;
    private String descripcion;
    private String apodo;
    private int edad;

    public UsuarioBin(int id, String nombre, String curp, String descripcion, String apodo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.curp = curp;
        this.descripcion = descripcion;
        this.apodo = apodo;
        this.edad = edad;
    }

    public UsuarioBin() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "UsuarioBin{" + "id=" + id + ", nombre=" + nombre + ", curp=" + curp + ", descripcion=" + descripcion + ", apodo=" + apodo + ", edad=" + edad + '}';
    }
    
    
}
