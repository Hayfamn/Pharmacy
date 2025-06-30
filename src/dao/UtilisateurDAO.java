package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.Utilisateur;

public class UtilisateurDAO implements InterfaceDAO<Utilisateur>{
	public static boolean isEmpty() {
	    try {
	        Connection cx = SingletonConnection.getInstance();
	        Statement st = cx.createStatement();
	        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM utilisateur;");
	        if (rs.next()) {
	            return rs.getInt(1) == 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public void insert(Utilisateur e) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		String s="insert into utilisateur values(?,?,?,?);";
		PreparedStatement ps=cx.prepareStatement(s);
		ps.setInt(1,e.getId());
		ps.setString(2,e.getNomUtilisateur());
		ps.setString(3,e.getMotDePasse());
		ps.setString(4, e.getType());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void delete(Utilisateur e) throws SQLException {
		 Connection cx = SingletonConnection.getInstance();    
	     String s = "delete from utilisateur where idUser = ?;";
	     PreparedStatement p=cx.prepareStatement(s);
	     p.setInt(1, e.getId());
	     p.executeUpdate();
	     p.close();
	    
		
	}

	@Override
	public void update(Utilisateur e) throws SQLException {
	    Connection cx = SingletonConnection.getInstance();    
	    String s = "UPDATE utilisateur SET nomUser = ?, mdpUser = ?, typeUser = ? WHERE idUser = ?";
	    PreparedStatement ps = cx.prepareStatement(s);
	    ps.setString(1, e.getNomUtilisateur());
	    ps.setString(2, e.getMotDePasse());
	    ps.setString(3, e.getType());
	    ps.setInt(4, e.getId());
	    ps.executeUpdate();
	    ps.close();
	}


	@Override
	public Utilisateur getById(int id) throws SQLException {
		Utilisateur user=null;
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from utilisateur where idUser=?;");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			user=new Utilisateur();
			user.setId(rs.getInt("idUser"));
			user.setNomUtilisateur(rs.getString("nomUser"));
			user.setMotDePasse(rs.getString("mdpUser"));
			user.setType(rs.getString("typeUser"));
		}
		ps.close();
		return user;
	}

	@Override
	public List<Utilisateur> getByName(String nom) throws SQLException {
		List<Utilisateur> utilisateurs=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from utilisateur where nomUser=?;");
		ps.setString(1, nom);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Utilisateur user=new Utilisateur();
			user.setId(rs.getInt("idUser"));
			user.setNomUtilisateur(rs.getString("nomUser"));
			user.setMotDePasse(rs.getString("mdpUser"));
			user.setType(rs.getString("typeUser"));
			utilisateurs.add(user);
		}
		ps.close();
		return utilisateurs;
	}

	@Override
	public List<Utilisateur> getAll() throws SQLException {
		List<Utilisateur> utilisateurs=new ArrayList<>();
		Connection cx=SingletonConnection.getInstance();
		PreparedStatement ps=cx.prepareStatement("Select * from utilisateur;");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Utilisateur user=new Utilisateur();
			user.setId(rs.getInt("idUser"));
			user.setNomUtilisateur(rs.getString("nomUser"));
			user.setMotDePasse(rs.getString("mdpUser"));
			user.setType(rs.getString("typeUser"));
			utilisateurs.add(user);
		}
		ps.close();
		return utilisateurs;
	}public static Utilisateur authentifier(String nomUtilisateur, String motDePasse) {
	    Utilisateur utilisateur = null;
	    Connection cx = SingletonConnection.getInstance();
	    String sql = "SELECT * FROM utilisateur WHERE nomUser = ? AND mdpUser = ?";

	    try (PreparedStatement ps = cx.prepareStatement(sql)) {
	        ps.setString(1, nomUtilisateur);
	        ps.setString(2, motDePasse);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            utilisateur = new Utilisateur();
	            utilisateur.setId(rs.getInt("idUser"));
	            utilisateur.setNomUtilisateur(rs.getString("nomUser"));
	            utilisateur.setMotDePasse(rs.getString("mdpUser"));
	            utilisateur.setType(rs.getString("typeUser"));
	        }

	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return utilisateur;
	}

	
}
