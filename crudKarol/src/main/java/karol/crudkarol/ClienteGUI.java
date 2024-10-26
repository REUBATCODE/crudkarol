package karol.crudkarol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteGUI extends JFrame {
    private JTextField txtNombre, txtDireccion, txtTelefono, txtEmail;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnListar;
    private JTextArea txtAreaClientes;

    public ClienteGUI() {
        setTitle("Gestión de Clientes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Campos de texto
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        // Botones
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnListar);

        // Área de texto para mostrar clientes
        txtAreaClientes = new JTextArea();
        txtAreaClientes.setEditable(false);

        // Añadir el panel y el área de texto a la ventana
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtAreaClientes), BorderLayout.CENTER);

        // Configurar eventos de los botones
        configurarEventos();

        setVisible(true);
    }

    private void configurarEventos() {
        // Evento para agregar cliente
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();
                Cliente cliente = new Cliente(0, nombre, direccion, telefono, email);
                if (CrudKarol.crearCliente(cliente)) {
                    mostrarMensaje("Cliente agregado exitosamente.");
                    limpiarCampos();
                } else {
                    mostrarMensaje("Error al agregar cliente.");
                }
            }
        });

        // Evento para actualizar cliente
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID del cliente a actualizar:"));
                    Cliente cliente = new Cliente(id, nombre, direccion, telefono, email);
                    if (CrudKarol.actualizarCliente(cliente)) {
                        mostrarMensaje("Cliente actualizado exitosamente.");
                        limpiarCampos();
                    } else {
                        mostrarMensaje("Error al actualizar cliente.");
                    }
                } catch (NumberFormatException ex) {
                    mostrarMensaje("ID inválido.");
                }
            }
        });

        // Evento para eliminar cliente
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el ID del cliente a eliminar:"));
                    if (CrudKarol.eliminarCliente(id)) {
                        mostrarMensaje("Cliente eliminado exitosamente.");
                    } else {
                        mostrarMensaje("Error al eliminar cliente.");
                    }
                } catch (NumberFormatException ex) {
                    mostrarMensaje("ID inválido.");
                }
            }
        });

        // Evento para listar clientes
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Cliente> clientes = CrudKarol.obtenerClientes();
                txtAreaClientes.setText(""); // Limpiar el área de texto
                for (Cliente cliente : clientes) {
                    txtAreaClientes.append("ID: " + cliente.getIdCliente() + ", Nombre: " + cliente.getNombre() +
                                           ", Dirección: " + cliente.getDireccion() + ", Teléfono: " + cliente.getTelefono() +
                                           ", Email: " + cliente.getEmail() + "\n");
                }
            }
        });
    }

    // Método para mostrar un mensaje
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }

    public static void main(String[] args) {
        new ClienteGUI();
    }
}
