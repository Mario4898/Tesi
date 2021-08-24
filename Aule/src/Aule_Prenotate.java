import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Aule_Prenotate {

	public static void main(String[] args) {
		
		String baseUrl="https://classbook.di.uniba.it/index.php?view=day&page_date=2021-07-12&area=1&room=1";
		
		try {
			 Document doc = Jsoup.connect(baseUrl).get();
			 Set<String> s = new LinkedHashSet<>();
			 //System.out.println(doc.outerHtml());
			 Elements righe = doc.select("tr");
			 for (Element riga : righe) {
			     Elements orario = riga.getElementsByClass("row_labels");
				 Elements esami = riga.getElementsByClass("booked");
				 for (Element ora : orario) {
				 for (Element esame : esami) {
				  String h = ora.text();
				  String e = esame.text();
				  String prova = "ora inizio:"+ h + ", "+ "attività: "+ e;
				  //InfoAule hnItem = new InfoAule(h, e);
		       	  //ObjectMapper mapper = new ObjectMapper();
				  //String jsonString = mapper.writeValueAsString(hnItem);
				  //System.out.println(jsonString);
				  s.add(prova);
				 }
				 break;
				 }
				
				 }
				 
			 for (String prova : s) {
				 System.out.println(prova);
			 }
			 
		}
		catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	}




















