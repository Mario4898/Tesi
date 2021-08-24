import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HNScraper {

	public static void main(String[] args) {
		String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/corsi-di-laurea/corsi-di-laurea" ;
		String baseUrl2 ="https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/tutorato/informazioni-generali";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	
		try{
			HtmlPage page = client.getPage(baseUrl);
			HtmlPage page2 = client.getPage(baseUrl2);
			List<HtmlElement> itemList = (page.getByXPath("//*[@id=\"parent-fieldname-text-90af691b4a854fb882a3ccfd477df5e8\"]/p"));
			HtmlElement prova1 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-5fb787ba13364978ae80be972530afbe\"]/p[1]");
			String tutorato = prova1.asText();
			ObjectMapper mapper2 = new ObjectMapper();
			HackerNewsItem hnItem2 = new HackerNewsItem(tutorato);
			String jsonString2 = mapper2.writeValueAsString(hnItem2);
			String tutorato1 = jsonString2.valueOf(tutorato);
			System.out.println (tutorato1);
			if(itemList.isEmpty()){
				System.out.println("No item found");
			}else{
				for(HtmlElement htmlItem : itemList ){
					//int position = Integer.parseInt(((HtmlElement) htmlItem.getFirstByXPath("./td/span")).asText().replace(".", ""));
					//int id = Integer.parseInt(htmlItem.getAttribute("id"));
					String title =  ((HtmlElement) htmlItem.getFirstByXPath("./a")).asText();
					String url = ((HtmlAnchor) htmlItem.getFirstByXPath("./a")).getHrefAttribute();
					//String author =  ((HtmlElement) htmlItem.getFirstByXPath("./following-sibling::tr/td[@class='subtext']/a[@class='hnuser']")).asText();
					//int score = Integer.parseInt(((HtmlElement) htmlItem.getFirstByXPath("./following-sibling::tr/td[@class='subtext']/span[@class='score']")).asText().replace(" points", ""));
					//HackerNewsItem hnItem = new HackerNewsItem(title, url);
					//ObjectMapper mapper = new ObjectMapper();
					//String jsonString = mapper.writeValueAsString(hnItem) ;
					//String prova= jsonString.valueOf(title);
					System.out.println("_________________");
					//System.out.println(prova + "ciao");
					System.out.println("_________________");
					//System.out.println(jsonString);
					System.out.println("_________________");
					System.out.println("title:"+ title + "," + " " + "url:" + url);
					
			}
			}
		 }catch(Exception e){
			 //e.printStackTrace();
		 }finally{
			 client.close();
		}
	 }
}