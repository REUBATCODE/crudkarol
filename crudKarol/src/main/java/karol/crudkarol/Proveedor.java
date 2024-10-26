package karol.crudkarol;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String contacto;
    private String direccion;

    // Constructor
    public Proveedor(int idProveedor, String nombre, String contacto, String direccion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
    }
    @Override
public String toString() {
    return "ID: " + idProveedor + ", Nombre: " + nombre + ", Contacto: " + contacto + ", Dirección: " + direccion;
}


    // Getters y Setters
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
