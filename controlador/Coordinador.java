package controlador;

import modelo.dao.productoDAO;
import modelo.dao.clienteDAO;
import modelo.dao.compraDAO;
import modelo.dto.clienteDTO;
import modelo.dto.compraDTO;
import modelo.logica.procesos;
import vista.ventanaPrincipal;

import java.util.ArrayList;

public class Coordinador {

    private procesos logica;
    private productoDAO dao;
    private compraDAO compraDao;
    private clienteDAO clienteDao;
    private ventanaPrincipal vista;

    public void setLogica(procesos logica) {
        this.logica = logica;
    }

    public void setProductoDAO(productoDAO dao) {
        this.dao = dao;
    }

    public void setCompraDAO(compraDAO compraDao) {
        this.compraDao = compraDao;
    }

    public void setClienteDAO(clienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void setVista(ventanaPrincipal vista) {
        this.vista = vista;
    }

    public double calcularTotal(double precio, int cantidad) {
        return logica.calcularTotal(precio, cantidad);
    }

    public double calcularDescuento(double total, String tipo) {
        return logica.calcularDescuento(total, tipo);
    }

    public productoDAO getDao() {
        return dao;
    }

    public clienteDTO obtenerCliente() {
        return clienteDao.obtenerCliente();
    }

    public void actualizarCliente(clienteDTO cliente) {
        clienteDao.guardarCliente(cliente);  
    }

    public void eliminarCliente() {
        clienteDTO actual = clienteDao.obtenerCliente();
        if (actual != null) {
            compraDao.eliminarComprasPorCliente(actual.getNombre());
        }
        clienteDao.eliminarCliente();
    }

    public ArrayList<compraDTO> obtenerCompras() {
        return compraDao.listarCompras();
    }

    public compraDTO obtenerUltimaCompra() {
        return compraDao.obtenerUltimaCompra();
    }

    public void guardarCompra(compraDTO compra) {
        compraDao.guardarCompra(compra);
    }
}