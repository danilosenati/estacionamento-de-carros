/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudo
 */

// CLASSE PARA CONEXÃO COM O BANCO DE DADOS
public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "estudo";
    private static final String PASS = "";

// METODO PARA RETORNAR AS CONEXÕES
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
        } 
        catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Falha na conexão: ", ex);
        }
    
    }
    
    // METODOS PARA FECHAR A CONEXÃO
    public static void closeConexao(Connection conexao){
        try{
            if(conexao != null){
                conexao.close();
            }
        
        }catch (SQLException ex){
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConexao(Connection conexao, PreparedStatement stmt){
        
        closeConexao(conexao);
        
        try{
            if(stmt != null){
                stmt.close();
            }
        
        }catch (SQLException ex){
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeconexao(Connection conexao, PreparedStatement stmt, ResultSet result){
        
        closeConexao(conexao, stmt);
        
        try{
            if(result != null){
                result.close();
            }
        
        }catch (SQLException ex){
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}