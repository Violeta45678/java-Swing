package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersonaDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        List<Persona> data = new ArrayList<>();
        String sql = "Select * from persona";
        try { 
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono (rs.getString(4));
                data.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        return data;
    }
    
    public int Agregar (Persona p){
        String sql = "insert into persona(nombre,correo,telefono) values(?,?,?)";
        if (p.getNombre() == null || p.getNombre().isEmpty() ||
        p.getCorreo() == null || p.getCorreo().isEmpty() ||
        p.getTelefono() == null || p.getTelefono().isEmpty()) {
        System.out.println("Error: No se pueden insertar registros con campos vacíos.");
        return 0; 
    }

        try{
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTelefono());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Debes llenar todos los campos" + e);
        }
        return 1;
    }
public int Actualizar(Persona p) {
    String sql = "UPDATE persona SET nombre = ?, correo = ?, telefono = ? WHERE idPersona = ?";
    int filasAfectadas = 0;

    try {
        con = conectar.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getCorreo());
        ps.setString(3, p.getTelefono());
        ps.setInt(4, p.getId());

        filasAfectadas = ps.executeUpdate();

        System.out.println(filasAfectadas > 0 ? "Actualización exitosa." : "Error: no se pudo actualizar.");
        ps.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        conectar.desconectar();
    }

    return filasAfectadas;
}

public int eliminar(int id) {
    String sql = "DELETE FROM persona WHERE idPersona = ?";
    int filasAfectadas = 0;

    try {
        con = conectar.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        filasAfectadas = ps.executeUpdate();

        System.out.println(filasAfectadas > 0 ? "Eliminación exitosa." : "Error: no se encontró el registro.");
        ps.close();
    } catch (SQLException e) {
        System.out.println("Error al eliminar: " + e.getMessage());
    } finally {
        conectar.desconectar();
    }

    return 1;
}

}
