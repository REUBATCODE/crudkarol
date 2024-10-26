package karol.crudkarol;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import karol.crudkarol.Cliente;
import karol.crudkarol.CrudKarol;
import karol.crudkarol.Proveedor;
import karol.crudkarol.Producto;
import karol.crudkarol.Venta;
import java.util.Date;




public class GUI extends JFrame {

    public GUI() {
        setTitle("Sistema de Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña de Cliente
        JPanel panelCliente = crearPanelCliente();
        tabbedPane.addTab("Cliente", panelCliente);

        // Pestaña de Proveedor
        JPanel panelProveedor = crearPanelProveedor();
        tabbedPane.addTab("Proveedor", panelProveedor);
        
        // Pestaña de Producto
        JPanel panelProducto = crearPanelProducto();
        tabbedPane.addTab("Producto", panelProducto);
        
        // Pestaña de Inventario
        JPanel panelInventario = crearPanelInventario();
        tabbedPane.addTab("Inventario", panelInventario);
        
        // Pestaña de Venta
        JPanel panelVenta = crearPanelVenta();
        tabbedPane.addTab("Venta", panelVenta);



        // Agregar el JTabbedPane al JFrame
        add(tabbedPane);
        setVisible(true);
    }

    private JPanel crearPanelCliente() {
        JPanel panel = new JPanel(new BorderLayout());

        // Formulario para Cliente
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        formPanel.add(txtNombre);

        formPanel.add(new JLabel("Dirección:"));
        JTextField txtDireccion = new JTextField();
        formPanel.add(txtDireccion);

        formPanel.add(new JLabel("Teléfono:"));
        JTextField txtTelefono = new JTextField();
        formPanel.add(txtTelefono);

        formPanel.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        formPanel.add(txtEmail);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnListar);

        // Modelo para el JList
        DefaultListModel<Cliente> modeloClientes = new DefaultListModel<>();
        JList<Cliente> listaClientes = new JList<>(modeloClientes);
        listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Añadir componentes al panel de Cliente
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(listaClientes), BorderLayout.SOUTH);

        // Agrega la lógica para cada botón (Ejemplo de Agregar Cliente)
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            Cliente cliente = new Cliente(0, nombre, direccion, telefono, email);
            if (CrudKarol.crearCliente(cliente)) {
                JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
                txtNombre.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtEmail.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar cliente.");
            }
        });

        // Ejemplo de listar clientes (completa con el método correspondiente en CrudKarol)
        btnListar.addActionListener(e -> {
        modeloClientes.clear(); // Limpiar la lista de clientes antes de actualizarla
        List<Cliente> clientes = CrudKarol.obtenerClientes(); // Obtener la lista de clientes
        for (Cliente cliente : clientes) {
            modeloClientes.addElement(cliente); // Agregar cada cliente al modelo
        }
    });
        
        btnActualizar.addActionListener(e -> {
    Cliente clienteSeleccionado = listaClientes.getSelectedValue();
    if (clienteSeleccionado != null) {
        clienteSeleccionado.setNombre(txtNombre.getText());
        clienteSeleccionado.setDireccion(txtDireccion.getText());
        clienteSeleccionado.setTelefono(txtTelefono.getText());
        clienteSeleccionado.setEmail(txtEmail.getText());

        if (CrudKarol.actualizarCliente(clienteSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
            btnListar.doClick(); // Refrescar la lista de clientes
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente de la lista.");
    }
});
        
    btnEliminar.addActionListener(e -> {
    Cliente clienteSeleccionado = listaClientes.getSelectedValue();
    if (clienteSeleccionado != null) {
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar a " + clienteSeleccionado.getNombre() + "?", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (CrudKarol.eliminarCliente(clienteSeleccionado.getIdCliente())) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");
                btnListar.doClick(); // Refrescar la lista de clientes
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente de la lista para eliminar.");
    }
});

        
        listaClientes.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) { // Evitar múltiples disparos del evento
            Cliente clienteSeleccionado = listaClientes.getSelectedValue();
            if (clienteSeleccionado != null) {
                txtNombre.setText(clienteSeleccionado.getNombre());
                txtDireccion.setText(clienteSeleccionado.getDireccion());
                txtTelefono.setText(clienteSeleccionado.getTelefono());
                txtEmail.setText(clienteSeleccionado.getEmail());
            }
        }
    });

        return panel;
    }

    
private JPanel crearPanelProveedor() {
    JPanel panel = new JPanel(new BorderLayout());

    // Formulario para Proveedor
    JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    formPanel.add(new JLabel("Nombre:"));
    JTextField txtNombreProveedor = new JTextField();
    formPanel.add(txtNombreProveedor);

    formPanel.add(new JLabel("Contacto:"));
    JTextField txtContactoProveedor = new JTextField();
    formPanel.add(txtContactoProveedor);

    formPanel.add(new JLabel("Dirección:"));
    JTextField txtDireccionProveedor = new JTextField();
    formPanel.add(txtDireccionProveedor);

    // Botones
    JButton btnAgregarProveedor = new JButton("Agregar");
    JButton btnActualizarProveedor = new JButton("Actualizar");
    JButton btnEliminarProveedor = new JButton("Eliminar");
    JButton btnListarProveedor = new JButton("Listar");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(btnAgregarProveedor);
    buttonPanel.add(btnActualizarProveedor);
    buttonPanel.add(btnEliminarProveedor);
    buttonPanel.add(btnListarProveedor);

    // Modelo para el JList
    DefaultListModel<Proveedor> modeloProveedores = new DefaultListModel<>();
    JList<Proveedor> listaProveedores = new JList<>(modeloProveedores);
    listaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // Añadir componentes al panel de Proveedor
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(buttonPanel, BorderLayout.CENTER);
    panel.add(new JScrollPane(listaProveedores), BorderLayout.SOUTH);

    // Lógica para el botón de listar proveedores
    btnListarProveedor.addActionListener(e -> {
        modeloProveedores.clear(); // Limpiar la lista de proveedores antes de actualizarla
        List<Proveedor> proveedores = CrudKarol.obtenerProveedores(); // Obtener la lista de proveedores
        for (Proveedor proveedor : proveedores) {
            modeloProveedores.addElement(proveedor); // Agregar cada proveedor al modelo
        }
    });

    // Lógica para cargar los datos del proveedor seleccionado en los campos de texto
    listaProveedores.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) { // Evitar múltiples disparos del evento
            Proveedor proveedorSeleccionado = listaProveedores.getSelectedValue();
            if (proveedorSeleccionado != null) {
                txtNombreProveedor.setText(proveedorSeleccionado.getNombre());
                txtContactoProveedor.setText(proveedorSeleccionado.getContacto());
                txtDireccionProveedor.setText(proveedorSeleccionado.getDireccion());
            }
        }
    });

    // Lógica para el botón de agregar proveedor
    btnAgregarProveedor.addActionListener(e -> {
        String nombre = txtNombreProveedor.getText();
        String contacto = txtContactoProveedor.getText();
        String direccion = txtDireccionProveedor.getText();
        Proveedor proveedor = new Proveedor(0, nombre, contacto, direccion);
        if (CrudKarol.crearProveedor(proveedor)) {
            JOptionPane.showMessageDialog(this, "Proveedor agregado exitosamente.");
            txtNombreProveedor.setText("");
            txtContactoProveedor.setText("");
            txtDireccionProveedor.setText("");
            btnListarProveedor.doClick(); // Refrescar la lista de proveedores
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar proveedor.");
        }
    });

    // Lógica para el botón de actualizar proveedor
    btnActualizarProveedor.addActionListener(e -> {
        Proveedor proveedorSeleccionado = listaProveedores.getSelectedValue();
        if (proveedorSeleccionado != null) {
            proveedorSeleccionado.setNombre(txtNombreProveedor.getText());
            proveedorSeleccionado.setContacto(txtContactoProveedor.getText());
            proveedorSeleccionado.setDireccion(txtDireccionProveedor.getText());

            if (CrudKarol.actualizarProveedor(proveedorSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Proveedor actualizado exitosamente.");
                btnListarProveedor.doClick(); // Refrescar la lista de proveedores
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el proveedor.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un proveedor de la lista.");
        }
    });

    // Lógica para el botón de eliminar proveedor
    btnEliminarProveedor.addActionListener(e -> {
        Proveedor proveedorSeleccionado = listaProveedores.getSelectedValue();
        if (proveedorSeleccionado != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas eliminar a " + proveedorSeleccionado.getNombre() + "?", 
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                if (CrudKarol.eliminarProveedor(proveedorSeleccionado.getIdProveedor())) {
                    JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente.");
                    btnListarProveedor.doClick(); // Refrescar la lista de proveedores
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un proveedor de la lista para eliminar.");
        }
    });

    return panel;
}

    
private JPanel crearPanelProducto() {
    JPanel panel = new JPanel(new BorderLayout());

    // Formulario para Producto
    JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    formPanel.add(new JLabel("Nombre:"));
    JTextField txtNombreProducto = new JTextField();
    formPanel.add(txtNombreProducto);

    formPanel.add(new JLabel("Descripción:"));
    JTextField txtDescripcionProducto = new JTextField();
    formPanel.add(txtDescripcionProducto);

    formPanel.add(new JLabel("Precio:"));
    JTextField txtPrecioProducto = new JTextField();
    formPanel.add(txtPrecioProducto);

    formPanel.add(new JLabel("Cantidad en Inventario:"));
    JTextField txtCantidadInventario = new JTextField();
    formPanel.add(txtCantidadInventario);

    // Botones
    JButton btnAgregarProducto = new JButton("Agregar");
    JButton btnActualizarProducto = new JButton("Actualizar");
    JButton btnEliminarProducto = new JButton("Eliminar");
    JButton btnListarProducto = new JButton("Listar");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(btnAgregarProducto);
    buttonPanel.add(btnActualizarProducto);
    buttonPanel.add(btnEliminarProducto);
    buttonPanel.add(btnListarProducto);

    // Modelo para el JList
    DefaultListModel<Producto> modeloProductos = new DefaultListModel<>();
    JList<Producto> listaProductos = new JList<>(modeloProductos);
    listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    // Añadir componentes al panel de Producto
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(buttonPanel, BorderLayout.CENTER);
    panel.add(new JScrollPane(listaProductos), BorderLayout.SOUTH);

    // Lógica para el botón de listar productos
    btnListarProducto.addActionListener(e -> {
        modeloProductos.clear(); // Limpiar la lista de productos antes de actualizarla
        List<Producto> productos = CrudKarol.obtenerProductos(); // Obtener la lista de productos
        for (Producto producto : productos) {
            modeloProductos.addElement(producto); // Agregar cada producto al modelo
        }
    });

    // Lógica para cargar los datos del producto seleccionado en los campos de texto
    listaProductos.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) { // Evitar múltiples disparos del evento
            Producto productoSeleccionado = listaProductos.getSelectedValue();
            if (productoSeleccionado != null) {
                txtNombreProducto.setText(productoSeleccionado.getNombre());
                txtDescripcionProducto.setText(productoSeleccionado.getDescripcion());
                txtPrecioProducto.setText(String.valueOf(productoSeleccionado.getPrecio()));
                txtCantidadInventario.setText(String.valueOf(productoSeleccionado.getCantidadInventario()));
            }
        }
    });

    // Lógica para el botón de agregar producto
    btnAgregarProducto.addActionListener(e -> {
        String nombre = txtNombreProducto.getText();
        String descripcion = txtDescripcionProducto.getText();
        float precio = Float.parseFloat(txtPrecioProducto.getText());
        int cantidadInventario = Integer.parseInt(txtCantidadInventario.getText());
        Producto producto = new Producto(0, nombre, descripcion, precio, cantidadInventario);
        if (CrudKarol.crearProducto(producto)) {
            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
            txtNombreProducto.setText("");
            txtDescripcionProducto.setText("");
            txtPrecioProducto.setText("");
            txtCantidadInventario.setText("");
            btnListarProducto.doClick(); // Refrescar la lista de productos
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar producto.");
        }
    });

    // Lógica para el botón de actualizar producto
    btnActualizarProducto.addActionListener(e -> {
        Producto productoSeleccionado = listaProductos.getSelectedValue();
        if (productoSeleccionado != null) {
            productoSeleccionado.setNombre(txtNombreProducto.getText());
            productoSeleccionado.setDescripcion(txtDescripcionProducto.getText());
            productoSeleccionado.setPrecio(Float.parseFloat(txtPrecioProducto.getText()));
            productoSeleccionado.setCantidadInventario(Integer.parseInt(txtCantidadInventario.getText()));

            if (CrudKarol.actualizarProducto(productoSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
                btnListarProducto.doClick(); // Refrescar la lista de productos
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la lista.");
        }
    });

    // Lógica para el botón de eliminar producto
    btnEliminarProducto.addActionListener(e -> {
        Producto productoSeleccionado = listaProductos.getSelectedValue();
        if (productoSeleccionado != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas eliminar el producto " + productoSeleccionado.getNombre() + "?", 
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                if (CrudKarol.eliminarProducto(productoSeleccionado.getIdProducto())) {
                    JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.");
                    btnListarProducto.doClick(); // Refrescar la lista de productos
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el producto.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto de la lista para eliminar.");
        }
    });

    return panel;
}


private JPanel crearPanelInventario() {
    JPanel panel = new JPanel(new BorderLayout());

    // Formulario para gestionar el inventario
    JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
    formPanel.add(new JLabel("ID Producto:"));
    JTextField txtIdProductoInventario = new JTextField();
    formPanel.add(txtIdProductoInventario);

    formPanel.add(new JLabel("Cantidad Disponible:"));
    JTextField txtCantidadDisponibleInventario = new JTextField();
    formPanel.add(txtCantidadDisponibleInventario);

    // Botones
    JButton btnActualizarInventario = new JButton("Actualizar Cantidad");
    JButton btnListarInventario = new JButton("Listar Inventario");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(btnActualizarInventario);
    buttonPanel.add(btnListarInventario);

    // Área de texto para mostrar la lista de inventario
    JTextArea txtAreaInventario = new JTextArea(10, 40);
    txtAreaInventario.setEditable(false);

    // Añadir componentes al panel de Inventario
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(buttonPanel, BorderLayout.CENTER);
    panel.add(new JScrollPane(txtAreaInventario), BorderLayout.SOUTH);

    // Lógica para el botón de listar inventario
    btnListarInventario.addActionListener(e -> {
        txtAreaInventario.setText(""); // Limpiar el área de texto
        List<Producto> productos = CrudKarol.obtenerProductosConInventario();
        for (Producto producto : productos) {
            txtAreaInventario.append("ID: " + producto.getIdProducto() + ", Nombre: " + producto.getNombre() +
                                     ", Cantidad Disponible: " + producto.getCantidadInventario() + "\n");
        }
    });

    // Lógica para el botón de actualizar inventario
    btnActualizarInventario.addActionListener(e -> {
        try {
            int idProducto = Integer.parseInt(txtIdProductoInventario.getText());
            int cantidadDisponible = Integer.parseInt(txtCantidadDisponibleInventario.getText());

            if (CrudKarol.actualizarInventario(idProducto, cantidadDisponible)) {
                JOptionPane.showMessageDialog(this, "Cantidad actualizada exitosamente.");
                txtIdProductoInventario.setText("");
                txtCantidadDisponibleInventario.setText("");
                btnListarInventario.doClick(); // Actualizar la lista de inventario
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la cantidad.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un ID de producto y cantidad válidos.");
        }
    });

    return panel;
}




private JPanel crearPanelVenta() {
    JPanel panel = new JPanel(new BorderLayout());

    // Formulario para registrar una venta
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    formPanel.add(new JLabel("ID Producto:"));
    JTextField txtIdProductoVenta = new JTextField();
    formPanel.add(txtIdProductoVenta);

    formPanel.add(new JLabel("Cantidad:"));
    JTextField txtCantidadVenta = new JTextField();
    formPanel.add(txtCantidadVenta);

    formPanel.add(new JLabel("ID Cliente:"));
    JTextField txtIdClienteVenta = new JTextField();
    formPanel.add(txtIdClienteVenta);

    // Botones
    JButton btnRegistrarVenta = new JButton("Registrar Venta");
    JButton btnListarVentas = new JButton("Listar Ventas");

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(btnRegistrarVenta);
    buttonPanel.add(btnListarVentas);

    // Área de texto para mostrar la lista de ventas
    JTextArea txtAreaVentas = new JTextArea(10, 40);
    txtAreaVentas.setEditable(false);

    // Añadir componentes al panel de Venta
    panel.add(formPanel, BorderLayout.NORTH);
    panel.add(buttonPanel, BorderLayout.CENTER);
    panel.add(new JScrollPane(txtAreaVentas), BorderLayout.SOUTH);

    // Lógica para el botón de registrar venta
    btnRegistrarVenta.addActionListener(e -> {
        try {
            int idProducto = Integer.parseInt(txtIdProductoVenta.getText());
            int cantidad = Integer.parseInt(txtCantidadVenta.getText());
            int idCliente = Integer.parseInt(txtIdClienteVenta.getText());

            if (CrudKarol.registrarVenta(idProducto, cantidad, idCliente)) {
                JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");
                txtIdProductoVenta.setText("");
                txtCantidadVenta.setText("");
                txtIdClienteVenta.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce valores válidos para el ID de producto, cantidad, y ID de cliente.");
        }
    });

    // Lógica para el botón de listar ventas
    btnListarVentas.addActionListener(e -> {
        txtAreaVentas.setText(""); // Limpiar el área de texto
        List<Venta> ventas = CrudKarol.obtenerVentas();
        for (Venta venta : ventas) {
            txtAreaVentas.append(venta.toString() + "\n");
        }
    });

    return panel;
}






    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(GUI::new);
    }
}
