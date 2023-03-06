package Class;

public class site {

	int idSite;
	String ville, adresse, codePostal;
	
	public site(int idSite, String ville, String adresse, String codePostal) {
		super();
		this.idSite = idSite;
		this.ville = ville;
		this.adresse = adresse;
		this.codePostal = codePostal;
	}
	
	public site(String ville, String adresse, String codePostal) {
		super();
		this.ville = ville;
		this.adresse = adresse;
		this.codePostal = codePostal;
	}
	
	public int getIdSite() {
		return idSite;
	}
	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
}
