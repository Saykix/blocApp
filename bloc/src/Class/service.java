package Class;

public class service {

	int IdService;
	String nomService;
	
	public service(int idService, String nomService) {
		super();
		IdService = idService;
		this.nomService = nomService;
	}
	
	public int getIdService() {
		return IdService;
	}
	public void setIdService(int idService) {
		IdService = idService;
	}
	public String getNomService() {
		return nomService;
	}
	public void setNomService(String nomService) {
		this.nomService = nomService;
	}
	
}
