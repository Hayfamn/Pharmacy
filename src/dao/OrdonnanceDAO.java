package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Ordonnance;
public class OrdonnanceDAO implements InterfaceDAO<Ordonnance>{

	@Override
	public void insert(Ordonnance e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String s="INSERT INTO ordonnance (dateOrd, descriptionOrd, idPatient) VALUES (?, ?, ?);";
		PreparedStatement ps=cx.prepareStatement(s);
		ps.setDate(1,(Date) e.getDateOrd());
		ps.setString(2,e.getDescriptionOrd());	
		ps.setInt(3,e.getIdPat());
		ps.executeUpdate();
		ps.close();
		
	}

	@Override
	public void delete(Ordonnance e) throws SQLException {
		Connection cx = SingletonConnection.getInstance();    
	     String s = "delete from ordonnance where idOrd = ?;";
	     PreparedStatement p=cx.prepareStatement(s);
	     p.setInt(1, e.getIdOrd());
	     p.executeUpdate();
	     p.close();
		
	}

	@Override
	public void update(Ordonnance e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("UPDATE ordonnance SET dateOrd=?,idPatient=?,descriptionOrd=? WHERE idOrd=?");
		ps.setDate(1,(Date) e.getDateOrd());
		ps.setInt(2,e.getIdPat());
		ps.setString(3,e.getDescriptionOrd());
		ps.setInt(4,e.getIdOrd());
		ps.executeUpdate();
	    ps.close();	
	}

	@Override
	public Ordonnance getById(int id) throws SQLException {
		Ordonnance ord=null;
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from ordonnance where idOrd=?;");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ord=new Ordonnance();
			ord.setIdOrd(rs.getInt("idOrd"));
			ord.setDateOrd(rs.getDate("dateOrd"));
			ord.setIdPat(rs.getInt("idPatient"));
			ord.setDescriptionOrd(rs.getString("descriptionOrd"));
		}
		ps.close();
		return ord;
	}

	@Override
	public List<Ordonnance> getByName(String nom) throws SQLException {
		List<Ordonnance> ordonnances=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from ordonnance where descriptionOrd=?;");
		ps.setString(1, nom);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Ordonnance ord=new Ordonnance();
			ord.setIdOrd(rs.getInt("idOrd"));
			ord.setDateOrd(rs.getDate("dateOrd"));
			ord.setIdPat(rs.getInt("idPatient"));
			ord.setDescriptionOrd(rs.getString("descriptionOrd"));
			ordonnances.add(ord);
		}
		ps.close();
		return ordonnances;
		
	}

	@Override
	public List<Ordonnance> getAll() throws SQLException {
		List<Ordonnance> ordonnances=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from ordonnance ");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Ordonnance ord=new Ordonnance();
			ord.setIdOrd(rs.getInt("idOrd"));
			ord.setDateOrd(rs.getDate("dateOrd"));
			ord.setIdPat(rs.getInt("idPatient"));
			ord.setDescriptionOrd(rs.getString("descriptionOrd"));
			ordonnances.add(ord);
		}
		ps.close();
		return ordonnances;
	}
	


}
