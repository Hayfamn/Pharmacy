package modele;

import java.util.Objects;

public class Utilisateur {
	private int id;
    private String nomUtilisateur;
    private String motDePasse;
    private String type;
	public Utilisateur(String nomUtilisateur, String motDePasse, String type) {
		super();
		this.nomUtilisateur=nomUtilisateur;
		this.motDePasse=motDePasse;
		this.type=type;
	}
	public Utilisateur() {
		super();
		this.id=0;
		this.nomUtilisateur="";
		this.motDePasse="";
		this.type="";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur=nomUtilisateur;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse=motDePasse;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type=type;
	}
	@Override
	public String toString() {
		return "Utilisateur [id="+id+", nomUtilisateur="+nomUtilisateur+", motDePasse="+motDePasse+", type="
				+ type+"]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, motDePasse, nomUtilisateur, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this==obj)
			return true;
		if (obj==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other=(Utilisateur) obj;
		return id==other.id && Objects.equals(motDePasse, other.motDePasse)
				&& Objects.equals(nomUtilisateur, other.nomUtilisateur) && Objects.equals(type, other.type);
	}
    
}
