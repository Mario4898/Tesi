import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Biblioteca_Aule {

	public static void main(String[] args) { 
		String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/laboratori-didattici-1";
		String baseUrl2 = "https://www.uniba.it/ricerca/dipartimenti/informatica/manuzio/laboratorio-manuzio";
		String baseUrl3 = "https://www.uniba.it/bibliotechecentri/informatica/biblioteca-di-informatica";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {
			HtmlPage page = client.getPage(baseUrl);
			HtmlPage page2 = client.getPage(baseUrl2);
			HtmlPage page3 = client.getPage(baseUrl3);
			HtmlElement didattici1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-7c69d602b1b94559ae0d823a3e120130\"]/p[1]");
			HtmlElement didattici2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-7c69d602b1b94559ae0d823a3e120130\"]/p[2]");
			HtmlElement didattici3 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-7c69d602b1b94559ae0d823a3e120130\"]/p[3]");
			String didattici = didattici1.asText().replaceAll("I software.*", "")+ "\n" + didattici2.asText() + "\n"+ didattici3.asText();
			System.out.println(didattici);
			//per maggiori informazioni sulle modalità di accesso si consiglia di visitare la sezione /"Laboratori Didattici/"
			//presente sul sito del Dipartimento
			// inserire link
			
			
			HtmlElement tesisti1 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-d455f35fd63f4f92a619713c926d8282\"]/p[1]");
			HtmlElement tesisti2 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-d455f35fd63f4f92a619713c926d8282\"]/p[2]");
			String tesisti = tesisti1.asText() + "\n" + tesisti2.asText();
			System.out.println(tesisti);
			//per maggiori informazioni sulle modalità di accesso o per reperire il modulo di attivazione dell'account si consiglia di visitare la sezione /"Laboratorio Tesisti/"
			//presente sul sito del Dipartimento
			// inserire link
			
			HtmlElement biblioteca1 = page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/p[1]");
			HtmlElement biblioteca2 = page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[5]");
			HtmlElement biblioteca3 = page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[6]");
			HtmlElement biblioteca4 = page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[7]");
			HtmlElement biblioteca5 = page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[8]");
			String dove_biblioteca = biblioteca1.asText();
			String biblioteca = biblioteca2.asText()+ "\n"+ biblioteca3.asText()+ "\n"+ biblioteca4.asText() + "\n"+ biblioteca5.asText();
			System.out.println("La biblioteca si trova: \n"+dove_biblioteca);
			System.out.println(biblioteca);
			//per maggiori informazioni si consiglia di visitare la sezione /"Biblioteca/" presente sul sito del dipartimento
			//inserire link
		}
		catch(Exception e){
			 e.printStackTrace();
			 }finally{
				 client.close();
				}

	}

}
