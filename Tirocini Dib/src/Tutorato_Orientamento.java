import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Tutorato_Orientamento {

	public static void main(String[] args) {
		String baseUrl= "https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/tutorato/informazioni-generali";
        String baseUrl2 = "https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/orientamento-e-tutorato-1/servizio-di-consulenze-di-orientamento";
		try {
			 Document doc = Jsoup.connect(baseUrl).get();
			 String tutorato = doc.select("#parent-fieldname-text-5fb787ba13364978ae80be972530afbe > p").text();
			 tutorato = tutorato.replaceAll("Questa sezione.*", "");
			 //System.out.println(tutorato);
			 //"\n Per maggiori informazioni per gli studenti interessati a seguire le attività di tutorato
			 //o per candidarsi come tutor si consiglia di visitare la sezione \"Tutorato\" sul sito del Dipartimento."
			 //inserire link
			 
			 Document doc2 = Jsoup.connect(baseUrl2).get();
			 String orientamento = doc2.select("#parent-fieldname-text-65612ebcd7fc484ea9a5d4ef43b61c6f").text();
			 orientamento= orientamento.replaceAll(" qui.*", ".");
			 //"Per maggiori informazioni sull'orientamento si consiglia di visitare la sezione \"Orientamento" sul sito del Dipartimento."
			 // inserire link
			 System.out.println(orientamento);
			 
			 String Ada = "Ada è la piattaforma di e-learning del Dipartimento di Informatica di Bari. Gli studenti possono accedere alle varie sezioni dedicate ai vari corsi, previa iscrizione, per poter reperire il materiale didattico usato a lezione dai docenti, i programmi dei vari corsi, i contatti ed i giorni di ricevimento dei docenti. Inoltre, potranno rimanere aggiornati sulle novità e su eventuali comunicazioni da parte dei Docenti dei corsi ai quali sono iscritti, e sulle ultime news del dipartimento.";
			 System.out.println(Ada);
		}
		catch(Exception e){
			 e.printStackTrace(); 
			 }
		

	}
	

}
