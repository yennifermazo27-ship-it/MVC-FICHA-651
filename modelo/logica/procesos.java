package modelo.logica;

import modelo.dto.clienteDTO;
import modelo.dto.productoDTO;
import modelo.dto.compraDTO;

public class procesos {

    public double calcularTotal(double precio, int cantidad) {
        return precio * cantidad;
    }

    public double calcularDescuento(double total, String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            return 0;
        }
        switch (tipo.toUpperCase()) {
            case "A": return total * 0.4;
            case "B": return total * 0.2;
            case "C": return total * 0.1;
            default:  return 0;
        }
    }

    public double calcularTotalFinal(double total, double descuento) {
        return total - descuento;
    }

    public compraDTO procesarCompra(clienteDTO cliente, productoDTO producto) {
        double total      = calcularTotal(producto.getPrecio(), producto.getCantidad());
        double descuento  = calcularDescuento(total, cliente.getTipo());
        double totalFinal = calcularTotalFinal(total, descuento);
        return new compraDTO(cliente, producto, total, descuento, totalFinal);
    }
}