package karol.crudkarol;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private float precio;
    private int cantidadInventario;

    // Constructor
    public Producto(int idProducto, String nombre, String descripcion, float precio, int cantidadInventario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadInventario = cantidadInventario;
    }
    @Override
public String toString() {
    return "ID: " + idProducto + ", Nombre: " + nombre + ", Descripción: " + descripcion +
           ", Precio: " + precio + ", Cantidad: " + cantidadInventario;
}


    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }
}
