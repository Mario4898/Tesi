import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Corsi_Laurea {

	public static void main(String[] args) {
		String baseUrl= "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/corsi-di-laurea/corsi-di-laurea";
		String baseUrl2 = "https://www.uniba.it/studenti/segreterie-studenti/procedure";
		String baseUrl3 = "https://www.uniba.it/ricerca/dipartimenti/informatica/test-di-valutazione/informazioni-generali";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false); 
		
		
		try {
			 Document doc = Jsoup.connect(baseUrl).get();
			 HtmlPage page = client.getPage(baseUrl2);
			 HtmlPage page2 = client.getPage(baseUrl3); 
			for ( Element riga : doc.select("#content-core")) {
				Elements corsi = riga.getElementsByTag("p");
				Elements url = riga.getElementsByTag("a");
				System.out.println("CORSI ATTIVI:");
				for (Element link1 : url) {
					for (Element corso : corsi) {
					
					String corsi_attivi = corso.text();
					
					if (corsi_attivi.equals("Magistrale in Informatica")) {
						break;
					}else {
						String link = link1.attr("href");
						System.out.println(corsi_attivi +": " +link+ "\n");
					}
					}
				break;
				}
				
			}
			 HtmlElement immatricolazione1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6c7ce2485d8643ec887bcc2d4ae88343\"]/p[2]");
			 HtmlElement immatricolazione2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6c7ce2485d8643ec887bcc2d4ae88343\"]/p[3]");
			 HtmlElement immatricolazione3 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6c7ce2485d8643ec887bcc2d4ae88343\"]/p[4]");
			 String immatricolazione = immatricolazione1.asText() + "\n"+ immatricolazione2.asText() + immatricolazione3.asText();
			 System.out.println(immatricolazione);
			 
			 HtmlElement test_valutazione1= page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-97c981db6adf47b785a7a42ca4ae8f20\"]/div[1]");
			 HtmlElement test_valutazione2= page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-97c981db6adf47b785a7a42ca4ae8f20\"]/div[2]");
			 HtmlElement test_valutazione3= page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-97c981db6adf47b785a7a42ca4ae8f20\"]/div[4]/p");
			 
			 String test_valutazione = test_valutazione1.asText().substring(0, 467) +test_valutazione1.asText().substring(467, test_valutazione1.asText().length()) +
			 (test_valutazione2.asText().substring(261, 333) + test_valutazione2.asText().substring(333, 614)).trim() + test_valutazione3.asText();
			 
			System.out.println(test_valutazione);
		}
		catch(Exception e){
			 e.printStackTrace(); 
			 }
		finally{
			 client.close();
		}
		

	}

	
	}
