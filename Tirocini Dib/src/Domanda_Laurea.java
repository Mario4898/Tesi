import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Domanda_Laurea {

	public static void main(String[] args) {
		String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/modulistica-1/informazioni-esame-di-laurea";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false); 
		
		try {
			HtmlPage page = client.getPage(baseUrl);
			HtmlElement dove1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[4]");
			HtmlElement dove2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[5]");
			String dove = dove1.asText() +" " + dove2.asText();
			//System.out.println(dove);
			
			HtmlElement quando1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[2]");
			HtmlElement quando2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/ul[1]");
			String quando = quando1.asText() + "\n"+ quando2.asText();
			//System.out.println(quando);
			
			
			HtmlElement quali1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[6]");
			HtmlElement quali2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[7]");
			HtmlElement quali3 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[8]");
			HtmlElement quali4 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[9]");
			HtmlElement quali5 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/ul[2]");
			HtmlElement quali6 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[10]");
			String quali = quali1.asText() +"\n" + quali2.asText() +"\n" + quali3.asText()  + "\n"+ quali4.asText() +"\n" + quali5.asText() +"\n" + quali6.asText();
			quali = quali.replaceAll(" A tal", "A tal");
			System.out.println(quali);
			String almalaurea = "Per maggiori informazioni a riguardo può visitare la pagina dedicata. Si ricorda che l'iscrizione ad AlmaLaurea, con conseguente "
					+ "compilazione del questionario, è richiesta per il completamento della domanda di laurea. ";
			//inserire link
			
			String bibliotela = "BiblioTela è un servizio messo a disposizione da SCeRPA che permette: al laureando di sottomettere la tesi, al relatore di approvarla, ed infine, una volta archiviata, potrà essere visualizzata "
					+ "dalla commissione di laurea. Può visualizzare una guida all'uso tramite il seguente link.";
			//inserire link
			}
		catch(Exception e){
			 e.printStackTrace();
			 }
		finally{
			 client.close();
		}
	
	}

}
