// Importaciones necesarias
package negocio;

import dominio.Propiedad;
import datos.PropiedadDAO;
import datos.impl.PropiedadDAOImpl;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class PropiedadControl {

    private final PropiedadDAO DATOS;
    private Propiedad pro;
    private DefaultTableModel modeloTabla;

    public PropiedadControl() {
        this.DATOS = new PropiedadDAOImpl();
        this.pro = new Propiedad();
    }

    public DefaultTableModel listar(String texto) {
        // Lista para almacenar las categorías obtenidas de los datos
        List<Propiedad> lista = new ArrayList();

        // Obtener las categorías que coincidan con el texto proporcionado
        lista.addAll(DATOS.listar(texto));

        // Establecer los títulos de las columnas de la tabla
        String[] titulos = {"ID", "NOMBRE", "DIRECCION", "CARACTERISTICAS", "ESTADO", "PRECIO ALQUILER"};

        // Arreglo para almacenar los valores 6e cada fila de la tabla
        String[] registro = new String[6];

        // Crear una instancia de DefaultTableModel con los títulos de las columnas
        // y null como datos iniciales
        this.modeloTabla = new DefaultTableModel(null, titulos);

        // Recorrer la lista de categorías y agregar cada categoría al modelo de la tabla
        for (Propiedad item : lista) {
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDireccion();
            registro[3] = item.getCaracteristicas();
            registro[4] = item.getEstado();
            registro[5] = Double.toString(item.getPrecioalquiler());

            this.modeloTabla.addRow(registro);
        }

        return this.modeloTabla;
    }

    public String insertar(Propiedad obj) {
        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el ingreso de datos.";
        }
    }

    public String actualizar(Propiedad obj) {
        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización de datos.";
        }
    }

    public String eliminar(int id) {
        if (DATOS.eliminar(id)) {
            return "OK";
        } else {
            return "Error en la eliminación de datos.";
        }
    }

}
