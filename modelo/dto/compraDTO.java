package modelo.dto;

public class compraDTO {

    private clienteDTO cliente;
    private productoDTO producto;
    private double total;
    private double descuento;
    private double totalFinal;

    public compraDTO(clienteDTO cliente, productoDTO producto,
                     double total, double descuento, double totalFinal) {
        this.cliente = cliente;
        this.producto = producto;
        this.total = total;
        this.descuento = descuento;
        this.totalFinal = totalFinal;
    }
    
    public void setCliente(clienteDTO cliente) {
        this.cliente = cliente;
    }

    public clienteDTO getCliente() {
        return cliente;
    }

    public productoDTO getProducto() {
        return producto;
    }

    public double getTotal() {
        return total;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotalFinal() {
        return totalFinal;
    }
}