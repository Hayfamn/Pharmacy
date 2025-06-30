package modele;

import java.util.Objects;

public class LigneMedicament {
	private int idMed,idOrd,qte;

	public LigneMedicament(int idMed, int idOrd, int qte) {
		this.idMed = idMed;
		this.idOrd = idOrd;
		this.qte = qte;
	}
	public LigneMedicament() {
		this.idMed = 0;
		this.idOrd = 0;
		this.qte = 0;
	}
	public int getIdMed() {
		return idMed;
	}
	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}
	public int getIdOrd() {
		return idOrd;
	}
	public void setIdOrd(int idOrd) {
		this.idOrd = idOrd;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idMed, idOrd, qte);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigneMedicament other = (LigneMedicament) obj;
		return idMed == other.idMed && idOrd == other.idOrd && qte == other.qte;
	}
	@Override
	public String toString() {
		return "LigneMedicament [idMed=" + idMed + ", idOrd=" + idOrd + ", qte=" + qte + "]";
	}
	
}
