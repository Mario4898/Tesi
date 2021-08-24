import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Tirocini {

	public static void main(String[] args) {
		String baseUrl="https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/tirocini/tirocini-informatica";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		 
		try {
			 HtmlPage page = client.getPage(baseUrl);
			 DomText interno1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[1]");
			 DomText interno2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[2]");
			 DomText interno3 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[3]");
			 DomText interno4 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[4]");
			 DomText interno5 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[5]");
			 String interno =interno1.asText()+ "\n"+ interno2.asText() +"\n"+ interno3.asText() +"\n"+ interno4.asText() +"\n"+ interno5.asText();
			 System.out.println(interno);
			 
			 DomText esterno1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[1]");
			 HtmlElement esterno2 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/strong[2]");
			 DomText esterno3 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[3]");
			 DomText esterno4 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[4]");
			 HtmlElement esterno5 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/strong[3]");
			 DomText esterno6 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[5]");
			 DomText esterno7 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[6]");
			 DomText esterno8 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[7]");
			 HtmlElement esterno9 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/strong[4]");
			 DomText esterno10 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[8]");
			 HtmlElement esterno11 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/a");
			 String esterno = esterno1.asText() + "\n"+ esterno2.asText()+ esterno3.asText()+ "\n"+ esterno4.asText() + " "+ esterno5.asText()
			 + "\n"+ esterno6.asText()+ " "+ esterno11.asText()+ "\n"+ esterno7.asText()+ "\n"+ esterno8.asText()+ esterno9.asText()+ "\n"+ esterno10.asText();
			 System.out.println(esterno);
			 
			 HtmlElement finestra1 = page.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/div[1]");
			 String finestra = finestra1.asText();
			 System.out.println(finestra);
			 String quando_tirocinio = "Si può cominciare un tirocinio interno o esterno quando sul libretto si hanno non più di tre esami ancora da superare. Per maggiori informazioni si consiglia di visitare la sezione \"Tirocini/Stage\" sul sito del Dipartimento.";
			 String moduli = "I moduli per il tirocinio e le relative informazioni sono disponibile nella sezione \"Tirocini/Stage\" presente sul sito del Dipartimento.";
			 //mettere link nel response
		}
		catch(Exception e){
			 e.printStackTrace();
			 }
		finally{
			 client.close();
		}
		

	}

}
