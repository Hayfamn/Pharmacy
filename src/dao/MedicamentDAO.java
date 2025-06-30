package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Medicament;


public class MedicamentDAO implements InterfaceDAO<Medicament>{

	@Override
	public void insert(Medicament e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String s="insert into medicament(nomMed,prix,stock,descriptionMed) values(?,?,?,?);";
		PreparedStatement ps=cx.prepareStatement(s);
		ps.setString(1,e.getNomMed());
		ps.setDouble(2,e.getPrix());
		ps.setInt(3,e.getStock());
		ps.setString(4,e.getDescriptionMed());		
		ps.executeUpdate();
		ps.close();
		
	}

	@Override
	public void delete(Medicament e) throws SQLException {
		Connection cx = SingletonConnection.getInstance();    
	     String s = "delete from medicament where idMed = ?;";
	     PreparedStatement p=cx.prepareStatement(s);
	     p.setInt(1, e.getIdMed());
	     p.executeUpdate();
	     p.close();
		
	}

	

	public void update(Medicament e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("UPDATE medicament SET nomMed=? ,descriptionMed=?,stock=?,prix=? WHERE idMed=?");
		ps.setString(1,e.getNomMed());
		ps.setString(2,e.getDescriptionMed());
		ps.setInt(3,e.getStock());
		ps.setDouble(4,e.getPrix());
		ps.setInt(5,e.getIdMed());
		ps.executeUpdate();
	    ps.close();
	}
	public void updateStock(int idMed, int newStock) throws SQLException {
	    String sql = "UPDATE medicament SET stock = ? WHERE idMed = ?";
	    Connection cx=SingletonConnection.getInstance();
	    PreparedStatement ps = cx.prepareStatement(sql);
	    ps.setInt(1, newStock);
	    ps.setInt(2, idMed);
	    ps.executeUpdate();
	    }
	

	@Override
	public Medicament getById(int id) throws SQLException {
		Medicament med=null;
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from medicament where idMed=?;");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			med=new Medicament();
			med.setIdMed(rs.getInt("idMed"));
			med.setNomMed(rs.getString("nomMed"));
			med.setPrix(rs.getInt("prix"));
			med.setStock(rs.getInt("stock"));
			med.setDescriptionMed(rs.getString("descriptionMed"));
			
		}
		ps.close();
		return med;
	}

	@Override
	public List<Medicament> getByName(String nom) throws SQLException {
		List<Medicament> medicaments=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from medicament where nomMed=?;");
		ps.setString(1, nom);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Medicament med=new Medicament();
			med.setIdMed(rs.getInt("idMed"));
			med.setNomMed(rs.getString("nomMed"));
			med.setPrix(rs.getInt("prix"));
			med.setStock(rs.getInt("stock"));
			med.setDescriptionMed(rs.getString("descriptionMed"));
			medicaments.add(med);
		}
		ps.close();
		return medicaments;
	}

	@Override
	public List<Medicament> getAll() throws SQLException {
		List<Medicament> medicaments=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from medicament;");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Medicament med=new Medicament();
			med.setIdMed(rs.getInt("idMed"));
			med.setNomMed(rs.getString("nomMed"));
			med.setPrix(rs.getDouble("prix"));
			med.setStock(rs.getInt("stock"));
			med.setDescriptionMed(rs.getString("descriptionMed"));
			medicaments.add(med);
		}
		ps.close();
		return medicaments;
	}
	public int getStockById(int idMed) throws SQLException {
	    int stock = 0;
	    Connection cx = SingletonConnection.getInstance();
	    PreparedStatement ps = cx.prepareStatement("SELECT stock FROM medicament WHERE idMed = ?");
	    ps.setInt(1, idMed);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        stock = rs.getInt("stock");
	    }
	    ps.close();
	    return stock;
	}


}
