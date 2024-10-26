package karol.crudkarol;

import java.util.Date;

public class Venta {
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private float total;
    private Date fecha;
    private String productoNombre;

    public Venta(int idVenta, int idProducto, int cantidad, float total, Date fecha) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    // Getters
    public int getIdVenta() {
        return idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getTotal() {
        return total;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    @Override
    public String toString() {
        return "ID Venta: " + idVenta + ", Producto: " + productoNombre + ", Cantidad: " + cantidad + ", Total: " + total + ", Fecha: " + fecha;
    }
}
