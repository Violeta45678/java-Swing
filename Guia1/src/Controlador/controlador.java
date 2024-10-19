/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.vista;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gamer
 */
public class controlador implements ActionListener{

    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    vista v =  new vista();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public controlador(vista v){
        this.v = v;
        this.v.btngListar.addActionListener(this);
        this.v.btngGuardar.addActionListener(this);
        this.v.btngEditar.addActionListener(this);
        this.v.btngOk.addActionListener(this);
        this.v.btngEliminar.addActionListener(this);
        this.v.tfCorreo.addActionListener(this);
        this.v.tfId.addActionListener(this);
        this.v.tfNombre.addActionListener(this);
        this.v.tfTelefono.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(e.getSource() == v.btngListar){
            Listar(v.Tabla);
            this.limpiar();
        }
       if(e.getSource() == v.btngGuardar){
           agregar();
           this.Listar(v.Tabla);
           this.limpiar();
       }
       if(e.getSource() == v.btngEditar){
           this.actualizar();
           this.Listar(v.Tabla);
           this.limpiar();
       }
       if(e.getSource() == v.btngEliminar){
           this.eliminar();
           this.Listar(v.Tabla);
           this.limpiar();
       } 

    }
    
    public void Listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        modelo.setRowCount(0);
        List<Persona> lista = dao.listar();
        Object [] object = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getCorreo();
            object[3] = lista.get(i).getTelefono();
            modelo.addRow(object);
        }
    }

    public void agregar(){
       String nom = v.tfNombre.getText();
       String correo = v.tfCorreo.getText();
       String tel = v.tfTelefono.getText();
       p.setNombre(nom);
       p.setCorreo(correo);
       p.setTelefono(tel);

       System.out.println("nombre: " + p.getNombre());
       System.out.println("telefono: "  + p.getTelefono());    
       System.out.println("correp: " +  p.getCorreo());

       int respuesta = dao.Agregar(p);

       if(respuesta == 1){
            JOptionPane.showMessageDialog(v,"Usuario agregado");

       }else{
            JOptionPane.showMessageDialog(v,"ERROR");   
       }
    }
        
    public void actualizar(){
        int id = Integer.parseInt(this.v.tfId.getText());
        String nombre = this.v.tfNombre.getText();
        String correo = this.v.tfCorreo.getText();
        String telefono = this.v.tfTelefono.getText();
        this.p.setId(id);
        this.p.setNombre(nombre);
        this.p.setCorreo(correo);
        this.p.setTelefono(telefono);
        int respuesta = this.dao.Actualizar(p);
        if(respuesta == 1){
            JOptionPane.showMessageDialog(v,"Usuario modificado");
        }else{
            JOptionPane.showMessageDialog(v,"ERROR");   
        }       
    }

    void limpiar() {
        v.tfId.setText("");
        v.tfNombre.setText("");
        v.tfCorreo.setText("");
        v.tfTelefono.setText("");
    }
        
    void eliminar(){
        int id = Integer.parseInt(v.tfId.getText());
        int respuesta = this.dao.eliminar(id);
        if(respuesta == 1){
            JOptionPane.showMessageDialog(v,"Usuario Eliminado");
        }else{
            JOptionPane.showMessageDialog(v,"ERROR");   
        } 
    }
}
