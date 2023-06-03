

package prueba;

import datos.impl.PropiedadDAOImpl;
import dominio.Propiedad;

/**
 *
 * @author plope
 */
public class PropiedadTest {
    public static void main(String[] args) {
       insertarPropiedad("mario", "los naranjos", "prueba1", "prueba2", 0);
       // actualizarPropiedad(4, "mario", " sin casa", "chato", "pobre", 300.00);
       // eliminarPropiedad(4);
    }
    private static void insertarPropiedad(String b,String c,String d,String e,double f){
        Propiedad obj=new Propiedad();
        PropiedadDAOImpl datos=new PropiedadDAOImpl();
        obj.setNombre(b);
        obj.setCaracteristicas(c);
        obj.setEstado(d);
        obj.setDireccion(e);
        obj.setPrecioalquiler(f);
        boolean resp;
        resp=datos.insertar(obj);
        System.out.println("Insertado: "+resp);
    }
    
    
   private static void eliminarPropiedad(int id) {
    PropiedadDAOImpl datos = new PropiedadDAOImpl();
    boolean resp = datos.eliminar(id);
    System.out.println("Eliminado: " + resp);
    }

    private static void actualizarPropiedad(int id, String nombre, String direccion, String caracteristicas, String estado, double precioAlquiler) {
    PropiedadDAOImpl datos = new PropiedadDAOImpl();
    Propiedad pro = new Propiedad();
    pro.setId(id);
    pro.setNombre(nombre);
    pro.setDireccion(direccion);
    pro.setCaracteristicas(caracteristicas);
    pro.setEstado(estado);
    pro.setPrecioalquiler(precioAlquiler);
    boolean resp = datos.actualizar(pro);
    System.out.println("Actualizado: " + resp);
    }
}
