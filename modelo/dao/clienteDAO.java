package modelo.dao;

import modelo.dto.clienteDTO;

public class clienteDAO {

    private clienteDTO clienteActual;

    public void guardarCliente(clienteDTO cliente) {
        this.clienteActual = cliente;
    }

    public clienteDTO obtenerCliente() {
        return clienteActual;
    }

    public void eliminarCliente() {
        this.clienteActual = null;
    }

    public void actualizarCliente(clienteDTO cliente) {
        this.clienteActual = cliente;
    }
}