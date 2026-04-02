package vista;

import controlador.Coordinador;
import modelo.dto.productoDTO;
import modelo.dto.clienteDTO;
import modelo.dto.compraDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class ventanaPrincipal extends JFrame {

    private Coordinador coord;
    private registroCompras ventanaHistorial;

    private JTextField txtNombre, txtEdad, txtTelefono, txtTipo;
    private JTextField txtProducto, txtPrecio, txtCantidad;
    private JLabel lblResultado;
    private JTable tabla;

    public void setCoordinador(Coordinador coord) {
        this.coord = coord;
    }

    public ventanaPrincipal() {

        setTitle("DON APARATO");
        setSize(650,500);
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font fuente = new Font("Tahoma", Font.PLAIN, 12);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 20); add(lblNombre);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(20, 50, 80, 20); add(lblEdad);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 80, 80, 20); add(lblTelefono);

        JLabel lblTipo = new JLabel("Tipo (A, B, C):");
        lblTipo.setBounds(20, 110, 80, 20); add(lblTipo);

        txtNombre = new JTextField(); txtNombre.setBounds(120,20,150,20); add(txtNombre);
        txtEdad = new JTextField(); txtEdad.setBounds(120,50,150,20); add(txtEdad);
        txtTelefono = new JTextField(); txtTelefono.setBounds(120,80,150,20); add(txtTelefono);
        txtTipo = new JTextField(); txtTipo.setBounds(120,110,150,20); add(txtTipo);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(300, 20, 80, 20); add(lblCodigo);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(300, 50, 80, 20); add(lblPrecio);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(300, 80, 80, 20); add(lblCantidad);

        txtProducto = new JTextField(); txtProducto.setBounds(400,20,150,20); add(txtProducto);
        txtPrecio = new JTextField(); txtPrecio.setBounds(400,50,150,20); add(txtPrecio);
        txtCantidad = new JTextField(); txtCantidad.setBounds(400,80,150,20); add(txtCantidad);

        JButton btnCompra = new JButton("Comprar");
        btnCompra.setBounds(50,150,120,30); add(btnCompra);

        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(250,150,120,30); add(btnMostrar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(450,150,120,30); add(btnLimpiar);

        JButton btnHistorial = new JButton("Historial");
        btnHistorial.setBounds(30,190,220,30); add(btnHistorial);

        JButton btnActualizar = new JButton("Actualizar datos");
        btnActualizar.setBounds(370,190,220,30); add(btnActualizar);

        lblResultado = new JLabel();
        lblResultado.setBounds(20,220,600,30); add(lblResultado);

        tabla = new JTable();
        add(new JScrollPane(tabla)).setBounds(20,260,600,150);

        btnCompra.addActionListener(e -> {
            try {
                clienteDTO cliente = new clienteDTO(
                        txtNombre.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        txtTelefono.getText(),
                        txtTipo.getText()
                );

                productoDTO producto = new productoDTO(
                        txtProducto.getText(),
                        "Producto",
                        Double.parseDouble(txtPrecio.getText()),
                        Integer.parseInt(txtCantidad.getText())
                );

                double total = coord.calcularTotal(producto.getPrecio(), producto.getCantidad());
                double desc = coord.calcularDescuento(total, cliente.getTipo());
                double finalP = total - desc;

                compraDTO compra = new compraDTO(cliente, producto, total, desc, finalP);

                coord.guardarCompra(compra);
                coord.actualizarCliente(cliente);

                lblResultado.setText("Compra guardada. Total: " + finalP);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en datos");
            }
        });

        btnHistorial.addActionListener(e -> {
            if (ventanaHistorial != null) ventanaHistorial.dispose();
            ventanaHistorial = new registroCompras(coord);
            ventanaHistorial.setVisible(true);
        });

        btnActualizar.addActionListener(e -> {
            ventanaGestionCliente v = new ventanaGestionCliente(coord, null);
            v.setVisible(true);
        });

        btnLimpiar.addActionListener(e -> {
            txtNombre.setText("");
            txtEdad.setText("");
            txtTelefono.setText("");
            txtTipo.setText("");
            txtProducto.setText("");
            txtPrecio.setText("");
            txtCantidad.setText("");
            lblResultado.setText("");
        });

        btnMostrar.addActionListener(e -> {
            compraDTO ultima = coord.obtenerUltimaCompra();

            if (ultima == null) {
                lblResultado.setText("Los campos se encuentran vacíos.");
                return;
            }

            clienteDTO c = ultima.getCliente();
            String descuento = c.getTipo() == null || c.getTipo().isEmpty()
                    ? "No se realiza descuento"
                    : "Descuento: $" + ultima.getDescuento();

            lblResultado.setText("Nombre: " + c.getNombre()
                    + " | Tipo: " + c.getTipo()
                    + " | Total: $" + ultima.getTotal()
                    + " | " + descuento
                    + " | Total final: $" + ultima.getTotalFinal());
        });
    }

    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Código");

        ArrayList<productoDTO> lista = coord.getDao().listar();

        for(productoDTO p : lista){
            modelo.addRow(new Object[]{
                    p.getNombre(),
                    p.getPrecio(),
                    p.getCodigo()
            });
        }

        tabla.setModel(modelo);
    }

    public void refrescarHistorial() {
        if (ventanaHistorial != null && ventanaHistorial.isVisible()) {
            ventanaHistorial.dispose();
            ventanaHistorial = new registroCompras(coord);
            ventanaHistorial.setVisible(true);
        }
    }
}