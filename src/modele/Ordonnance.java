package modele;

import java.util.Date;
import java.util.Objects;


public class Ordonnance {
	private int idOrd;
	private Date dateOrd ;
	private String descriptionOrd;
	private int idPat;
	public Ordonnance(int idOrd, Date dateOrd, String descriptionOrd, int idPat) {
		this.idOrd = idOrd;
		this.dateOrd = dateOrd;
		this.descriptionOrd = descriptionOrd;
		this.idPat = idPat;
	}
	public Ordonnance(Date dateOrd, String descriptionOrd, int idPat) {
		super();
		this.dateOrd = dateOrd;
		this.descriptionOrd = descriptionOrd;
		this.idPat = idPat;
	}
	public Ordonnance() {
		this.idOrd=0;
		this.dateOrd = new Date();
		this.descriptionOrd ="";
		this.idPat = 0;
	}
	public int getIdOrd() {
		return idOrd;
	}
	public void setIdOrd(int idOrd) {
		this.idOrd = idOrd;
	}
	public Date getDateOrd() {
		return dateOrd;
	}
	public void setDateOrd(Date dateOrd) {
		this.dateOrd = dateOrd;
	}
	public String getDescriptionOrd() {
		return descriptionOrd;
	}
	public void setDescriptionOrd(String descriptionOrd) {
		this.descriptionOrd = descriptionOrd;
	}
	public int getIdPat() {
		return idPat;
	}
	public void setIdPat(int idPat) {
		this.idPat = idPat;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dateOrd, descriptionOrd, idOrd, idPat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordonnance other = (Ordonnance) obj;
		return Objects.equals(dateOrd, other.dateOrd) && Objects.equals(descriptionOrd, other.descriptionOrd)
				&& idOrd == other.idOrd && idPat == other.idPat;
	}
	@Override
	public String toString() {
		return "Ordonnance [idOrd=" + idOrd + ", dateOrd=" + dateOrd + ", descriptionOrd=" + descriptionOrd + ", idPat="
				+ idPat + "]";
	}
	
}
