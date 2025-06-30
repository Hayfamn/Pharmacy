package modele;

import java.util.Objects;

public class Medicament {
	private int idMed,stock;
	private String nomMed,descriptionMed;
	private double prix;
	public Medicament(int idMed, String nomMed, double prix, int stock,String descriptionMed) {
		super();
		this.idMed = idMed;
		this.nomMed = nomMed;
		this.prix = prix;
		this.stock = stock;
		this.descriptionMed =descriptionMed;
	}
	public Medicament(int stock, String nomMed, String descriptionMed, double prix) {
		super();
		this.stock = stock;
		this.nomMed = nomMed;
		this.descriptionMed = descriptionMed;
		this.prix = prix;
	}
	public Medicament() {
		super();
		this.idMed = 0;
		this.nomMed = "";
		this.prix = 0;
		this.stock = 0;
		this.descriptionMed = "";
	}
	public int getIdMed() {
		return idMed;
	}
	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getNomMed() {
		return nomMed;
	}
	public void setNomMed(String nomMed) {
		this.nomMed = nomMed;
	}
	public String getDescriptionMed() {
		return descriptionMed;
	}
	public void setDescriptionMed(String descriptionMed) {
		this.descriptionMed = descriptionMed;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descriptionMed, idMed, nomMed, prix, stock);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicament other = (Medicament) obj;
		return Objects.equals(descriptionMed, other.descriptionMed) && idMed == other.idMed
				&& Objects.equals(nomMed, other.nomMed)
				&& Double.doubleToLongBits(prix) == Double.doubleToLongBits(other.prix) && stock == other.stock;
	}
	@Override
public String toString() {
		return "Medicament [idMed=" + idMed + ", stock=" + stock + ", nomMed=" + nomMed + ", descriptionMed="			+ descriptionMed + ", prix=" + prix + "]";
}
	


}
