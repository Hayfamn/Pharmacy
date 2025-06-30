package modele;

import java.util.Objects;

public class Patient {
	private int idPat;
	private String nomPat;
	private double credit;
	public Patient(int idPat, String nomPat, double credit) {
		this.idPat = idPat;
		this.nomPat = nomPat;
		this.credit = credit;
	}
	public Patient() {
		this.idPat=0;
		this.credit=0;
		this.nomPat="";
	}

	
	public Patient(String nomPat, double credit) {
		super();
		this.nomPat = nomPat;
		this.credit = credit;
	}
	public int getIdPat() {
		return idPat;
	}
	public void setIdPat(int idPat) {
		this.idPat = idPat;
	}
	public String getNomPat() {
		return nomPat;
	}
	public void setNomPat(String nomPat) {
		this.nomPat = nomPat;
	}

	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Patient [idPat=" + idPat + ", nomPat=" + nomPat + ", credit=" + credit
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(credit, idPat, nomPat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Double.doubleToLongBits(credit) == Double.doubleToLongBits(other.credit) && idPat == other.idPat
				&& Objects.equals(nomPat, other.nomPat) ;
	}
	
	
}
