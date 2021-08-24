import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Segreteria {

	public static void main(String[] args) {
		String baseUrl ="https://www.uniba.it/ricerca/dipartimenti/informatica";
		String baseUrl2 = "https://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento/cose-il-dib";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {
			
			HtmlPage page = client.getPage(baseUrl);
			HtmlPage page2 = client.getPage(baseUrl2);
			
			HtmlElement segreteria_stud= page.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/div");
			String segr_stud = segreteria_stud.asText().trim();
			HtmlElement segreteria_did = page.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[9]");
			String segr_did = segreteria_did.asText().trim();
			HtmlElement stud_s = page.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[10]");
			String stranieri = stud_s.asText().trim();
			HtmlElement orient = page.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[11]");
			String segr_tirocini = orient.asText().trim();
			HtmlElement segreteria_taranto = page.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[12]");
			String segr_ta= segreteria_taranto.asText().trim();
			//System.out.println(segr_stud +"\n");		
		    //System.out.println(segr_did+"\n");	
			//System.out.println(stranieri+"\n");
			//System.out.println(segr_tirocini+"\n");
			//System.out.println(segr_ta+"\n");
			
			DomText c_dib1 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-601c791553994133b2348d975215d89d\"]/p[1]/text()[1]");
			HtmlElement c_dib2 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-601c791553994133b2348d975215d89d\"]/p[1]/b");
			DomText c_dib3 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-601c791553994133b2348d975215d89d\"]/p[1]/text()[3]");
			String cosa_dib= c_dib1.asText()+" "+ c_dib2.asText()+".\n";
			String cosa_dib2 = c_dib3.asText();
			System.out.println(cosa_dib + cosa_dib2 );
			
		}
		catch(Exception e){
			e.printStackTrace(); 
		} finally{
			 client.close();
		}

	}

}
