/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Carro;

/**
 *
 * @author estudo
 */
public class CarroDAO {
    
    public void create(Carro car){
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{ // INSERINDO OS DADOS NA TABELA CARROS
            stmt = conexao.prepareStatement("INSERT INTO CARROS (marca,placa,cor,horaEntrada,horaSaida) VALUES(?,?,?,?,?)");
            stmt.setString(1,car.getMarca());
            stmt.setString(2, car.getPlaca());
            stmt.setString(3, car.getCor());
            stmt.setInt(4, car.getHoraEntrada());
            stmt.setInt(5, car.getHoraSaida());
            
            // EXECUTANDO O SQL
            stmt.executeUpdate();
            
            JOptionPane.showConfirmDialog(null, "Dados inseridos com sucesso!");
            
        }catch(SQLException ex){
            JOptionPane.showConfirmDialog(null, "Erro na inserção dos dados: "+ex);
        }
        finally{
            ConnectionFactory.closeConexao(conexao, stmt);
        }
    }
        
    // RETORNANDO OS DADOS DO BANCO
    
    public List<Carro> read(){
            
            Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet resultado = null;
        
    List<Carro> carros = new ArrayList<>();
            
        try {
           stmt = conexao.prepareStatement("SELECT * FROM CARROS");
          resultado = stmt.executeQuery(); // RESPONSAVEL POR FAZER AS CONSULTAS NO BANCO.
           
           while (resultado.next()) {

                Carro carro = new Carro();

                carro.setId(resultado.getInt("ID_CARRO"));
                carro.setMarca(resultado.getString("MARCA"));
                carro.setPlaca(resultado.getString("PLACA"));
                carro.setCor(resultado.getString("COR"));
                carro.setHoraEntrada(resultado.getInt("HORAENTRADA"));
                carro.setHoraSaida(resultado.getInt("HORASAIDA"));
                
                carros.add(carro); // ADICIONANDO OS VALORES NA LISTA.
            }
           }
            catch(SQLException ex){
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE,null, ex);
        }
        finally{
            ConnectionFactory.closeconexao(conexao, stmt, resultado);
        }
        return carros;
    }
    
    public void update(Carro carro){

        Connection conexao = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE CARROS SET MARCA = ? ,PLACA = ?,HORAENTRADA = ?, HORASAIDA = ? WHERE id = ?");
            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getPlaca());
            stmt.setString(3, carro.getCor());
            stmt.setInt(4, carro.getHoraEntrada());
            stmt.setInt(5, carro.getHoraSaida());
            stmt.setInt(6, carro.getId());

            stmt.executeUpdate(); // EXECUTAR SQL.

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } 
        finally{
            ConnectionFactory.closeConexao(conexao, stmt);
        }

    }
    public void delete(Carro carro) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM CARROS WHERE id = ?");
            stmt.setInt(1, carro.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } 
        
        finally {
            ConnectionFactory.closeConexao(con, stmt);
        }

    }

}