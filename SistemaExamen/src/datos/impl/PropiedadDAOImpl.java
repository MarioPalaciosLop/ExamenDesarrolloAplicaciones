/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.impl;

import datos.PropiedadDAO;
import dominio.Propiedad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.Conexion;
import java.util.ArrayList;


/**
 *
 * @author plope
 */
public class PropiedadDAOImpl implements PropiedadDAO {
    
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public PropiedadDAOImpl(){
        CON = Conexion.getInstancia();
    }

    @Override //ps = CON.conectar().prepareStatement("SELECT id, nombre, direccion, caracteristicas, estado, precioalquiler FROM propiedades WHERE estado = ?");
    public List listar(String texto) {
       List<Propiedad> registros=new ArrayList();
        try {            
            ps = CON.conectar().prepareStatement("Select * from propiedades where nombre LIKE ");
            ps.setString(1, "%" + texto + "%");             //int, string , string ,string 
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add(new Propiedad(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            ps=null;
            rs=null;
        }
        return registros;
    }

   @Override
public boolean insertar(Object obj) {
    if (obj instanceof Propiedad) {
        Propiedad propiedad = (Propiedad) obj;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO propiedades (nombre, direccion, caracteristicas, estado, precioalquiler) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, propiedad.getNombre());
            ps.setString(2, propiedad.getDireccion());
            ps.setString(3, propiedad.getCaracteristicas());
            ps.setString(4, propiedad.getEstado());
            ps.setDouble(5, propiedad.getPrecioalquiler());
            
            int filasAfectadas = ps.executeUpdate();
            ps.close();
            
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ps = null;
        }
    }
    return false;
}

    @Override
public boolean actualizar(Object obj) {
    if (obj instanceof Propiedad) {
        Propiedad propiedad = (Propiedad) obj;
        try {
            ps = CON.conectar().prepareStatement("UPDATE propiedades SET nombre=?, direccion=?, caracteristicas=?, estado=?, precioalquiler=? WHERE id=?");
            ps.setString(1, propiedad.getNombre());
            ps.setString(2, propiedad.getDireccion());
            ps.setString(3, propiedad.getCaracteristicas());
            ps.setString(4, propiedad.getEstado());
            ps.setDouble(5, propiedad.getPrecioalquiler());
            ps.setInt(6, propiedad.getId());
            
            int filasAfectadas = ps.executeUpdate();
            ps.close();
            
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ps = null;
        }
    }
    return false;
}

@Override
public boolean eliminar(int id) {
    try {
        ps = CON.conectar().prepareStatement("DELETE FROM propiedades WHERE id=?");
        ps.setInt(1, id);
        
        int filasAfectadas = ps.executeUpdate();
        ps.close();
        
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        ps = null;
    }
    return false;
}



    
}
