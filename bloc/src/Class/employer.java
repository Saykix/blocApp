package Class;

public class employer {

	int idEmployer;
	
	String nom, prenom, fixe, portable, email, employerService, employerSite;

		
	public employer(int idEmployer, String nom, String prenom, String fixe, String portable, String email,
			String employerService, String employerSite) {
		super();
		this.idEmployer = idEmployer;
		this.nom = nom;
		this.prenom = prenom;
		this.fixe = fixe;
		this.portable = portable;
		this.email = email;
		this.employerService = employerService;
		this.employerSite = employerSite;
	}

	public employer(String text, String text2, String text3, String text4, String text5, int int1, int int2) {
		
		this.nom = text;
		this.prenom = text2;
		this.fixe = text3;
		this.portable = text4;
		this.email = text5;
		this.employerService = Integer.toString(int1);
		this.employerSite = Integer.toString(int2);
	}


	public employer(String nom2, String prenom2, String fixe2, String portable2, String email2, String idService,
			String idSite) {
		// TODO Auto-generated constructor stub
		this.nom = nom2;
		this.prenom = prenom2;
		this.fixe = fixe2;
		this.portable = portable2;
		this.email = email2;
		this.employerService = idService;
		this.employerSite = idSite;
	}

	public int getIdEmployer() {
		return idEmployer;
	}

	public void setIdEmployer(int idEmployer) {
		this.idEmployer = idEmployer;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getFixe() {
		return fixe;
	}

	public void setFixe(String fixe) {
		this.fixe = fixe;
	}

	public String getPortable() {
		return portable;
	}

	public void setPortable(String portable) {
		this.portable = portable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployerService() {
		return employerService;
	}

	public void setEmployerService(String employerService) {
		this.employerService = employerService;
	}

	public String getEmployerSite() {
		return employerSite;
	}

	public void setEmployerSite(String employerSite) {
		this.employerSite = employerSite;
	}
	
}
