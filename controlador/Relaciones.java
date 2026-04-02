package controlador;

import modelo.dao.productoDAO;
import modelo.dao.compraDAO;
import modelo.dao.clienteDAO;
import modelo.logica.procesos;
import vista.ventanaPrincipal;

public class Relaciones {

    public void iniciar() {

        ventanaPrincipal vista = new ventanaPrincipal();
        productoDAO dao = new productoDAO();
        compraDAO compraDao = new compraDAO();
        clienteDAO clienteDao = new clienteDAO();  
        procesos logica = new procesos();
        Coordinador coord = new Coordinador();

        vista.setCoordinador(coord);
        coord.setVista(vista);
        coord.setLogica(logica);
        coord.setProductoDAO(dao);
        coord.setCompraDAO(compraDao);
        coord.setClienteDAO(clienteDao); 

        vista.setVisible(true);
        vista.cargarTabla();
    }
}