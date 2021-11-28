package fr.su.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.su.jee.beans.Compte;
import fr.su.jee.db.connection.DatabaseConnectionFactory;

public class CompteDAO {
	
	public static void creationCompte (Compte compte) throws SQLException {
		//get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			final String sql = "insert into Compte (email, numero, balance) values (?,?,?)";
			//create prepared statement with option to get auto generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//set parameters
			stmt.setString(1, compte.getEmail());
			stmt.setInt(2, compte.getNumeroCompte());
			stmt.setInt(3, compte.getBalance());
			
			stmt.execute();
			
			//Get auto generated keys
	        //ResultSet rs = stmt.getGeneratedKeys(); 
	        
	        //if (rs.next())
	        //	compte.setId(rs.getInt(1));
	        
	        //rs.close();
	        stmt.close();
		} finally {
			con.close();
		}
	}
	
	public static Compte getCompte (String email) throws SQLException {
		//get connection from connection pool
		final Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
	
		Compte compte = new Compte();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			
			StringBuilder sb = new StringBuilder("SELECT numero, balance FROM Compte WHERE email = \"" + email + "\"");
			
			rs = stmt.executeQuery(sb.toString());
			rs.next();
			
			compte.setEmail(email);
			compte.setNumeroCompte(rs.getInt("numero"));
			compte.setBalance(rs.getInt("balance"));
			
			return compte;
		} finally {
			try {if (rs != null) rs.close();} catch (SQLException e) {}
			try {if (stmt != null) stmt.close();} catch (SQLException e) {}
			try {con.close();} catch (SQLException e) {}
		}
	}
	
	public static Compte updateCompte(int numeroCompte, String operationType, int montant) throws SQLException {
		//get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			Compte compte = new Compte();
			Statement st = null;
			ResultSet rs = null;
			st = con.createStatement();
			
			StringBuilder sb = new StringBuilder("SELECT balance FROM Compte WHERE numero = \"" + numeroCompte + "\"");
			
			rs = st.executeQuery(sb.toString());
			rs.next();
			
			int balance = rs.getInt("balance");
			
			final String sql = "UPDATE Compte SET balance = (?) WHERE numero = (?)";
			//create prepared statement with option to get auto generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//set parameters
			if(operationType.equals("deposit")) {
				stmt.setInt(1, balance + montant);
				compte.setBalance(balance + montant);
			} else {
				stmt.setInt(1, balance - montant);
				compte.setBalance(balance - montant);
			}
			stmt.setInt(2, numeroCompte);
			stmt.execute();
			
			//Get auto generated keys
	        //rs = stmt.getGeneratedKeys(); 
	        
	        rs.close();
	        stmt.close();
	        
	        return compte;
		} finally {
			con.close();
		}	
	}
	
	public static Compte transfert(int emetteur, int dest, int montant) throws SQLException {
		//Compte du client connecte, retirer la somme a transferer
		Compte client = CompteDAO.updateCompte(emetteur, "withdraw", montant);
		//Compte du destinataire, ajouter la somme transferee
		CompteDAO.updateCompte(dest, "deposit", montant);
		
		return client;
	}
		
/*	
	public static void deleteCompte (int numeroCompte) throws SQLException {
		//get connection from connection pool
		final Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
	
		//Compte compte = new Compte();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			
			StringBuilder sb = new StringBuilder("DELETE FROM Compte WHERE numeroCompte = \"" + numeroCompte + "\"");
			
			stmt.executeUpdate(sb.toString());
			//rs.next();
			
			//compte.setId(0);
			//compte.setUser(user);
			//compte.setBalance(rs.getInt("balance"));
			
			//return compte;
		} finally {
			try {if (rs != null) rs.close();} catch (SQLException e) {}
			try {if (stmt != null) stmt.close();} catch (SQLException e) {}
			try {con.close();} catch (SQLException e) {}
		}
	}
*/
}
