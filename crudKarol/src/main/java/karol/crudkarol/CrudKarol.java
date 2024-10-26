package karol.crudkarol;

import conexionSQL.ConexionSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import karol.crudkarol.Cliente;
import karol.crudkarol.CrudKarol;
import karol.crudkarol.Proveedor;
import karol.crudkarol.Producto;
import karol.crudkarol.Venta;


public class CrudKarol {

public static void main(String[] args) {
    System.out.println("Iniciando aplicación...");
}


    // Método para Crear Cliente (INSERT)
    public static boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (nombre, direccion, telefono, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setIdCliente(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
        }
        return false;
    }

    // Método para Obtener Clientes (SELECT)
    public static List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
        try (Connection conn = ConexionSQL.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id_cliente"),
                                              rs.getString("nombre"),
                                              rs.getString("direccion"),
                                              rs.getString("telefono"),
                                              rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Método para Actualizar Cliente (UPDATE)
    public static boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET nombre = ?, direccion = ?, telefono = ?, email = ? WHERE id_cliente = ?";
        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setInt(5, cliente.getIdCliente());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
        return false;
    }

    // Método para Eliminar Cliente (DELETE)
    public static boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM CLIENTE WHERE id_cliente = ?";
        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
        return false;
    }
    
     // Método para Crear Proveedor (INSERT)
    public static boolean crearProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO PROVEEDOR (nombre, contacto, direccion) VALUES (?, ?, ?)";
        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getDireccion());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Método para Obtener todos los Proveedores (SELECT)
    public static List<Proveedor> obtenerProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM PROVEEDOR";
        try (Connection conn = ConexionSQL.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("direccion"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener proveedores: " + e.getMessage());
        }
        return proveedores;
    }

    // Método para Actualizar Proveedor (UPDATE)
    public static boolean actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE PROVEEDOR SET nombre = ?, contacto = ?, direccion = ? WHERE id_proveedor = ?";
        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getContacto());
            pstmt.setString(3, proveedor.getDireccion());
            pstmt.setInt(4, proveedor.getIdProveedor());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }

    // Método para Eliminar Proveedor (DELETE)
    public static boolean eliminarProveedor(int idProveedor) {
        String sql = "DELETE FROM PROVEEDOR WHERE id_proveedor = ?";
        try (Connection conn = ConexionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProveedor);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    // Método para Crear Producto (INSERT)
public static boolean crearProducto(Producto producto) {
    String sql = "INSERT INTO PRODUCTO (nombre, descripcion, precio, cantidad_inventario) VALUES (?, ?, ?, ?)";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, producto.getNombre());
        pstmt.setString(2, producto.getDescripcion());
        pstmt.setFloat(3, producto.getPrecio());
        pstmt.setInt(4, producto.getCantidadInventario());
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al agregar producto: " + e.getMessage());
        return false;
    }
}

// Método para Obtener todos los Productos (SELECT)
public static List<Producto> obtenerProductos() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM PRODUCTO";
    try (Connection conn = ConexionSQL.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getFloat("precio"),
                    rs.getInt("cantidad_inventario"));
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener productos: " + e.getMessage());
    }
    return productos;
}

// Método para Actualizar Producto (UPDATE)
public static boolean actualizarProducto(Producto producto) {
    String sql = "UPDATE PRODUCTO SET nombre = ?, descripcion = ?, precio = ?, cantidad_inventario = ? WHERE id_producto = ?";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, producto.getNombre());
        pstmt.setString(2, producto.getDescripcion());
        pstmt.setFloat(3, producto.getPrecio());
        pstmt.setInt(4, producto.getCantidadInventario());
        pstmt.setInt(5, producto.getIdProducto());
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al actualizar producto: " + e.getMessage());
        return false;
    }
}

// Método para Eliminar Producto (DELETE)
public static boolean eliminarProducto(int idProducto) {
    String sql = "DELETE FROM PRODUCTO WHERE id_producto = ?";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idProducto);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al eliminar producto: " + e.getMessage());
        return false;
    }
}

// Método para Actualizar Inventario (UPDATE)
public static boolean actualizarInventario(int idProducto, int cantidadDisponible) {
    String sql = "UPDATE INVENTARIO SET cantidad_disponible = ? WHERE id_producto = ?";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, cantidadDisponible);
        pstmt.setInt(2, idProducto);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al actualizar inventario: " + e.getMessage());
        return false;
    }
}

// Método para Obtener el Inventario (Lista de Productos con Cantidad Disponible)
public static List<Producto> obtenerProductosConInventario() {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT p.id_producto, p.nombre, i.cantidad_disponible " +
                 "FROM PRODUCTO p " +
                 "JOIN INVENTARIO i ON p.id_producto = i.id_producto";
    try (Connection conn = ConexionSQL.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    null, // Descripción no incluida en la consulta
                    0, // Precio no incluido en la consulta
                    rs.getInt("cantidad_disponible"));
            productos.add(producto);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener productos con inventario: " + e.getMessage());
    }
    return productos;
}

public static Producto obtenerProductoPorId(int idProducto) {
    String sql = "SELECT * FROM PRODUCTO WHERE id_producto = ?";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idProducto);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getFloat("precio"),
                    rs.getInt("cantidad_inventario"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el producto por ID: " + e.getMessage());
    }
    return null;
}

public static boolean crearVenta(Venta venta) {
    String sql = "INSERT INTO VENTA (id_producto, cantidad, total, fecha) VALUES (?, ?, ?, ?)";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, venta.getIdProducto());
        pstmt.setInt(2, venta.getCantidad());
        pstmt.setFloat(3, venta.getTotal());
        pstmt.setDate(4, new java.sql.Date(venta.getFecha().getTime())); // Asegúrate de pasar una fecha SQL
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al crear venta: " + e.getMessage());
        return false;
    }
}



// Método para Registrar Venta
public static boolean registrarVenta(int idProducto, int cantidad, int idCliente) {
    String sqlFactura = "INSERT INTO FACTURA (fecha, monto_total, id_cliente) VALUES (CURDATE(), 0, ?)";
    String sqlDetalle = "INSERT INTO DETALLE_FACTURA (id_factura, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
    String sqlActualizarInventario = "UPDATE INVENTARIO SET cantidad_disponible = cantidad_disponible - ? WHERE id_producto = ?";

    try (Connection conn = ConexionSQL.getConnection()) {
        conn.setAutoCommit(false); // Iniciar transacción

        // Insertar la factura
        try (PreparedStatement pstmtFactura = conn.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS)) {
            pstmtFactura.setInt(1, idCliente);
            pstmtFactura.executeUpdate();

            // Obtener el ID de la factura generada
            ResultSet rs = pstmtFactura.getGeneratedKeys();
            if (!rs.next()) {
                conn.rollback();
                return false;
            }
            int idFactura = rs.getInt(1);

            // Insertar el detalle de la factura
            try (PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle)) {
                float precioUnitario = obtenerPrecioProducto(idProducto);
                pstmtDetalle.setInt(1, idFactura);
                pstmtDetalle.setInt(2, idProducto);
                pstmtDetalle.setInt(3, cantidad);
                pstmtDetalle.setFloat(4, precioUnitario);
                pstmtDetalle.executeUpdate();
            }

            // Actualizar el inventario
            try (PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizarInventario)) {
                pstmtActualizar.setInt(1, cantidad);
                pstmtActualizar.setInt(2, idProducto);
                pstmtActualizar.executeUpdate();
            }

            // Calcular el monto total de la factura y actualizarlo
            float montoTotal = cantidad * obtenerPrecioProducto(idProducto);
            String sqlActualizarMonto = "UPDATE FACTURA SET monto_total = ? WHERE id_factura = ?";
            try (PreparedStatement pstmtActualizarMonto = conn.prepareStatement(sqlActualizarMonto)) {
                pstmtActualizarMonto.setFloat(1, montoTotal);
                pstmtActualizarMonto.setInt(2, idFactura);
                pstmtActualizarMonto.executeUpdate();
            }

            conn.commit(); // Confirmar la transacción
            return true;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("Error al registrar la venta: " + e.getMessage());
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Error en la conexión: " + e.getMessage());
        return false;
    }
}

// Método para Obtener Precio de un Producto
public static float obtenerPrecioProducto(int idProducto) {
    String sql = "SELECT precio FROM PRODUCTO WHERE id_producto = ?";
    try (Connection conn = ConexionSQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idProducto);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getFloat("precio");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener precio del producto: " + e.getMessage());
    }
    return 0;
}

// Método para Obtener Ventas

public static List<Venta> obtenerVentas() {
    List<Venta> ventas = new ArrayList<>();
    String sql = "SELECT v.id_venta, v.id_producto, v.cantidad, v.total, v.fecha, p.nombre AS producto_nombre " +
                 "FROM VENTA v " +
                 "JOIN PRODUCTO p ON v.id_producto = p.id_producto";

    try (Connection conn = ConexionSQL.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int idVenta = rs.getInt("id_venta");
            int idProducto = rs.getInt("id_producto");
            int cantidad = rs.getInt("cantidad");
            float total = rs.getFloat("total");
            Date fecha = rs.getDate("fecha");
            String productoNombre = rs.getString("producto_nombre");

            Venta venta = new Venta(idVenta, idProducto, cantidad, total, fecha);
            venta.setProductoNombre(productoNombre);
            ventas.add(venta);
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener ventas: " + e.getMessage());
    }
    return ventas;
}




}

