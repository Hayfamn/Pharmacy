package dao;

import java.sql.*;


import modele.Utilisateur;
import modele.LigneMedicament;
public class Test {
	public static void main(String[] args) throws SQLException {
		Connection cx=SingletonConnection.getInstance();
		System.out.println("Connexion etablie");
//		UtilisateurDAO userdao=new UtilisateurDAO();
//		Utilisateur user=new Utilisateur("Eya Khelifi","1234567","admin");
//		userdao.insert(user);
		
		//Utilisateur user=new Utilisateur(2,"Eya Khelifi","7654321","Admin");
		//userdao.insert(user);
		//System.out.println("User ajouté");
//		System.out.println(userdao.getAll());
//		System.out.println(userdao.getByName("Hayfa Menzli"));
//		System.out.println(userdao.getById(1));
//		MedicamentDAO meddao=new MedicamentDAO();
		//Medicament med=new Medicament(1,"Doliprane 1000",4.500,100,"pour douleur intense");
		//Medicament med=new Medicament(2,"Antafene",4.500,100,"pour douleur intense");
		//meddao.insert(med);
		//meddao.delete(med);
//		OrdonnanceDAO orddao=new OrdonnanceDAO();
		//Ordonnance ord=new Ordonnance(1,java.sql.Date.valueOf("2025-02-01"),"3 jours",2);
		//orddao.insert(ord);
//		System.out.println(orddao.getByName("3 jours"));
//		PatientDAO patdao=new PatientDAO();
//		Patient p=new Patient(1,"Hayfa Menzli",70);
//		patdao.insert(p);
//		System.out.println(patdao.getById(1));
//		patdao.delete(p);
		LigneMedicamentDAO lmeds=new LigneMedicamentDAO();
		LigneMedicament lm=new LigneMedicament(3,6,100);
		lmeds.insert(lm);
		
	}
	

}
