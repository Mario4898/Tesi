
public class Docenti_Contatti {
    private String nominativo;
    private String email;
    private String numero;
    
    public Docenti_Contatti(String nominativo, String email, String numero) {
 	   super();
 	   this.nominativo=nominativo;
 	   this.email= email;
 	   this.numero= numero;
    }
    
    public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
