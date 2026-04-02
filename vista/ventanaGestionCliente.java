package vista;

import controlador.Coordinador;
import modelo.dto.clienteDTO;

import java.awt.Color;

import javax.swing.*;

public class ventanaGestionCliente extends JFrame {

    private JTextField txtNombre, txtEdad, txtTelefono, txtTipo;
    private Coordinador coord;
    private registroCompras historialAnterior;

    public ventanaGestionCliente(Coordinador coord, registroCompras historial) {
        this.coord = coord;
        this.historialAnterior = historial;

        setTitle("Gestión de Cliente");
        setSize(380, 250);
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 20); add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 200, 20); add(txtNombre);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(20, 50, 80, 20); add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(100, 50, 200, 20); add(txtEdad);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 80, 80, 20); add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 80, 200, 20); add(txtTelefono);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 110, 80, 20); add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(100, 110, 200, 20); add(txtTipo);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(50, 160, 120, 30); add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(200, 160, 120, 30); add(btnEliminar);

        clienteDTO actual = coord.obtenerCliente();
        if (actual != null) {
            txtNombre.setText(actual.getNombre());
            txtEdad.setText(String.valueOf(actual.getEdad()));
            txtTelefono.setText(actual.getTelefono());
            txtTipo.setText(actual.getTipo());
        }

        btnActualizar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Actualizar deshabilitado.");
        });

        btnEliminar.addActionListener(e -> {
            clienteDTO clienteAEliminar = coord.obtenerCliente();
            if (clienteAEliminar == null) {
                JOptionPane.showMessageDialog(null, "No hay cliente registrado.");
                return;
            }

            coord.eliminarCliente();

            txtNombre.setText("");
            txtEdad.setText("");
            txtTelefono.setText("");
            txtTipo.setText("");

            JOptionPane.showMessageDialog(null, "Cliente eliminado.");

            registroCompras nuevoHistorial = new registroCompras(coord);
            nuevoHistorial.setVisible(true);
        });
    }
}