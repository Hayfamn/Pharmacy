package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Patient;

public class PatientDAO implements InterfaceDAO<Patient>{
	@Override
	public void insert(Patient e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String s="insert into patient values(?,?,?)";
		PreparedStatement ps=cx.prepareStatement(s);
		ps.setInt(1,e.getIdPat());
		ps.setString(2,e.getNomPat());
		ps.setDouble(3,e.getCredit());
		ps.executeUpdate();
		ps.close();
		
	}

	@Override
	public void delete(Patient e) throws SQLException {
		Connection cx = SingletonConnection.getInstance();    
	     String s = "delete from patient where idPatient = ?;";
	     PreparedStatement p=cx.prepareStatement(s);
	     p.setInt(1, e.getIdPat());
	     p.executeUpdate();
	     p.close();
		
	}

	@Override
	public void update(Patient e) throws SQLException {
	    Connection cx = SingletonConnection.getInstance();
	    String s = "UPDATE patient SET nomPatient = ?, credit = ? WHERE idPatient = ?";
	    PreparedStatement ps = cx.prepareStatement(s);
	    ps.setString(1, e.getNomPat());
	    ps.setDouble(2, e.getCredit());
	    ps.setInt(3, e.getIdPat());
	    ps.executeUpdate();
	    ps.close();
	}


	@Override
	public Patient getById(int id) throws SQLException {
		Patient pat=null;
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from patient where idPatient=?;");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			pat=new Patient();
			pat.setIdPat(rs.getInt("idPatient"));
			pat.setNomPat(rs.getString("nomPatient"));
			pat.setCredit(rs.getDouble("credit"));
		}
		ps.close();
		return pat;
	}

	@Override
	public List<Patient> getByName(String nom) throws SQLException {
		List<Patient> patients=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from patient where nomPatient=?;");
		ps.setString(1, nom);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Patient pat=new Patient();
			pat.setIdPat(rs.getInt("idPatient"));
			pat.setNomPat(rs.getString("nomPatient"));
			pat.setCredit(rs.getDouble("credit"));
			patients.add(pat);
			}
		ps.close();
		return patients;
	}

	@Override
	public List<Patient> getAll() throws SQLException {
		List<Patient> patients=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from patient ;");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Patient pat=new Patient();
			pat.setIdPat(rs.getInt("idPatient"));
			pat.setNomPat(rs.getString("nomPatient"));
			pat.setCredit(rs.getDouble("credit"));
			patients.add(pat);
			}
		ps.close();
		return patients;
	}
}
