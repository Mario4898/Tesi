
public class InfoAule {
       private String ora;
       private String esame;
       
       public InfoAule(String ora, String esame) {
    	   super();
    	   this.ora=ora;
    	   this.esame= esame;
       }
       
       public String getOra() {
   		return ora;
   	}
   	public void setOra(String ora) {
   		this.ora = ora;
   	}
   	
   	public String getEsame() {
		return esame;
	}
	public void setEsame(String esame) {
		this.esame = esame;
	}
}
