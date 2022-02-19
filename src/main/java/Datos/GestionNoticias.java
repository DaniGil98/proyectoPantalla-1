/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Conexión.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author AlumnadoManana
 */
public class GestionNoticias {
    private Connection connection;
    
    public GestionNoticias(){
        MyConnection miCon;
        try{
             miCon=new MyConnection();
            connection=miCon.getCon();
            
        }catch(ClassNotFoundException ex){
        System.out.println("Error: con la base de datos"+ex);
        }
    }
    public boolean insertNoticia(Noticia noti){
        try{
            PreparedStatement stmt= connection.prepareStatement("INSERT into noticias(url,fecha,duracion)VALUES(?,?,?)");
            stmt.setString(1,noti.getUrl());
            java.util.Date utilStartDate = noti.getFecha();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            stmt.setDate(2,sqlStartDate);
            java.util.Date fechafin = noti.getDuracion();
            java.sql.Date sqlfechafinDate = new java.sql.Date(fechafin.getTime());
            stmt.setDate(3, sqlfechafinDate);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("Error al insertar noticia");
            return false;
        }
    }
    
    public boolean modificarNoticia(int id, Noticia noti){
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE noticias SET url=?,fecha=?,duracion=? WHERE id=?");
            
            stmt.setString(1,noti.getUrl());
            stmt.setDate(2, (java.sql.Date) noti.getFecha());
            stmt.setDate(3, (java.sql.Date) noti.getDuracion());
            stmt.setInt(4,id);            
            stmt.executeUpdate();
            return true;
            
        }catch(SQLException ex){
            System.out.println("Error al modificar noticia: " + ex);
            return false;
        }
    }
    
        public boolean eliminarNoticia(int id){
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM noticias WHERE id=?");
            stmt.setInt(1,id);
            
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            System.out.println("Error al eliminar noticia: " + ex);
            return false;
        }
    }
}
