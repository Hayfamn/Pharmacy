package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.LigneMedicament;

public class LigneMedicamentDAO {

	
	public void insert(LigneMedicament e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String s="insert into ligneMedicament  ( idMed,idOrd, quantite) values (?, ?, ?)";
		PreparedStatement ps = cx.prepareStatement(s);
		ps.setInt(2, e.getIdOrd());
		ps.setInt(1, e.getIdMed());
		ps.setInt(3, e.getQte());
		

		ps.executeUpdate();
		ps.close();
	}

	
	public void delete(LigneMedicament e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String s="delete from lignemedicament where idMed=? and idOrd=?";
		PreparedStatement ps=cx.prepareStatement(s);
		ps.setInt(1,e.getIdMed());
		ps.setInt(2,e.getIdOrd());
		ps.executeUpdate();
		ps.close();
		
	}

	
	public void update(LigneMedicament e) throws SQLException {
		    Connection cx = SingletonConnection.getInstance();
		    String s = "UPDATE lignemedicament SET quantite = ? WHERE idMed = ? AND idOrd = ?";
		    PreparedStatement ps = cx.prepareStatement(s);
		    ps.setInt(1, e.getQte());
		    ps.setInt(2, e.getIdMed());
		    ps.setInt(3, e.getIdOrd());
		    ps.executeUpdate();
		    ps.close();
		}

		
	

	
	public LigneMedicament getById(int id1,int id2) throws SQLException {
		LigneMedicament lmed=null;
		Connection cx=SingletonConnection.getInstance();
		String s="select * from lignemedicament where idMed=? and idOrd=?";
		PreparedStatement ps=cx.prepareStatement(s);
		ps.setInt(1,id1);
		ps.setInt(2,id2);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			lmed=new LigneMedicament();
			lmed.setIdMed(rs.getInt("idMed"));
			lmed.setIdOrd(rs.getInt("idOrd"));
			lmed.setQte(rs.getInt("quantite"));
		}
		ps.close();
		return lmed;
	}


	
	public List<LigneMedicament> getAll() throws SQLException {
		List<LigneMedicament> lignemeds=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from lignemedicament;");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			LigneMedicament lmed=new LigneMedicament();
			lmed.setIdMed(rs.getInt("idMed"));
			lmed.setIdOrd(rs.getInt("idOrd"));
			lmed.setQte(rs.getInt("quantite"));
			lignemeds.add(lmed);
		}
		ps.close();
		return lignemeds;
	}
	


}
