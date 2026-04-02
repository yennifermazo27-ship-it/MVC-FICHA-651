package vista;

import controlador.Coordinador;
import modelo.dto.compraDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.util.ArrayList;

public class registroCompras extends JFrame {

    private JTable tabla;

    public registroCompras(Coordinador coord) {

        setTitle("Historial de Compras");
        setSize(510,400);
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        setLocationRelativeTo(null);

        tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20,20,450,300);
        add(scroll);

        cargarTabla(coord);
    }

    public void cargarTabla(Coordinador coord) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Tipo");
        modelo.addColumn("Producto");
        modelo.addColumn("Total");
        modelo.addColumn("Descuento");
        modelo.addColumn("Total Final");

        ArrayList<compraDTO> lista = coord.obtenerCompras();

        for (compraDTO c : lista) {
            String tipo = c.getCliente().getTipo();
            String descuento = (tipo == null || tipo.isEmpty())
                    ? "No se realiza descuento"
                    : "$" + c.getDescuento();

            modelo.addRow(new Object[]{
                    c.getCliente().getNombre(),
                    (tipo == null || tipo.isEmpty()) ? "Sin tipo" : tipo.toUpperCase(),
                    c.getProducto().getNombre(),
                    "$" + c.getTotal(),
                    descuento,
                    "$" + c.getTotalFinal()
            });
        }

        tabla.setModel(modelo);
    }
}