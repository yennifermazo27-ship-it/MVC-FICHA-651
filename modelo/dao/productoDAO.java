package modelo.dao;
import modelo.dto.productoDTO;
import java.util.ArrayList;

public class productoDAO {
    private ArrayList<productoDTO> lista = new ArrayList<>();

    public productoDAO() {
        lista.add(new productoDTO("P001", "Pestañina",   12000.0, 0));
        lista.add(new productoDTO("P002", "Iluminador",    20000.0, 0));
        lista.add(new productoDTO("P003", "Rubor",     25000.0, 0));
        lista.add(new productoDTO("P004", "Base", 100000.0, 0));
        lista.add(new productoDTO("P005", "Corrector",    15000.0, 0));
        
    }

    public ArrayList<productoDTO> listar() {
        return lista;
    }
}