import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.nodes.Element;

public class News_Dib {

	public static void main(String[] args) {
		String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {
			Document doc = Jsoup.connect(baseUrl).get();
			HtmlPage page = client.getPage(baseUrl);
			List<HtmlElement> eventi = (page.getByXPath("//*[@id=\"portlet-newszona\"]/dd"));
			System.out.println("Ultime notizie: \n");
			for (HtmlElement evento : eventi) {
				 String not1 = ((HtmlElement) evento.getFirstByXPath("./a")).asText();
				 if (not1.equals("Archivio eventi…") || not1.equals("Altre notizie…")) {
					 not1="";
					 continue; 
				 }
				 System.out.println(not1+"\n");
				 
			 }
			
		}
		catch (Exception e) {
			 e.printStackTrace();
		} finally{
			 client.close();
		}

	}

}
