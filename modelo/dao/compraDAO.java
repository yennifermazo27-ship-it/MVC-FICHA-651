package modelo.dao;

import modelo.dto.clienteDTO;
import modelo.dto.compraDTO;
import java.util.ArrayList;

public class compraDAO {

    private ArrayList<compraDTO> listaCompras;

    public compraDAO() {
        listaCompras = new ArrayList<>();
    }

    public void guardarCompra(compraDTO compra) {
        listaCompras.add(compra);
    }

    public ArrayList<compraDTO> listarCompras() {
        return listaCompras;
    }
    
    public compraDTO obtenerUltimaCompra() {
        if (listaCompras.isEmpty()) {
            return null;
        }
        return listaCompras.get(listaCompras.size() - 1);
    }
    
    public void eliminarComprasPorCliente(String nombre) {
        listaCompras.removeIf(c -> c.getCliente().getNombre().equals(nombre));
    }
    
    public void actualizarClienteEnCompras(String nombre, clienteDTO nuevoCliente) {
        for (compraDTO c : listaCompras) {
            if (c.getCliente().getNombre().equals(nombre)) {
                c.setCliente(nuevoCliente);
            }
        }
    }
}