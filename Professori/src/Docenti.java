
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Docenti {  

	public static void main(String[] args) {
		
		String baseUrl="https://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento/personale/docenti-2";
		String baseUrl2="https://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento/personale/tecniciamministrativi";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {
			 Document doc = Jsoup.connect(baseUrl).get();
			 HtmlPage page = client.getPage(baseUrl2);
			 Set<String> s = new LinkedHashSet<>();
			 //System.out.println(doc.outerHtml());
			 for (Element riga : doc.select("#content-core p")) {
				 
				 Elements telefono = riga.getElementsByTag("p");
				 Elements nominativo = riga.getElementsByAttribute("title");
				 Elements contatti = riga.getElementsByTag("a");
				
				  for (Element identificativo : nominativo) {
				   for (Element contatto: contatti) {
				     for (Element numero : telefono) {
					   
					 if (identificativo.text().equals("")) { 
						 continue;
					 }
					 String nome = identificativo.text().replaceAll("@uniba.it", " ");
					 String nome2= nome.replaceAll("[-+.^:,]", " ");
					 String lettera = nome2.substring(0, 1);
					 String lettera2 = nome2.substring(1, 2);
					 char a = lettera.charAt(0);
					 char b = lettera2.charAt(0);
					 
					 while(Character.isLowerCase(a)){
						 if (!Character.isLetter(b)) {
							 break;
						 }
						 nome2 = "";
						 break;
					 }
					 if (nome2.equals("")) {
						 continue;
					 }
					 
					 
						 String email = contatto.attr("href");
						 email = email.replaceAll("mailto:", "");
						 while (email.substring(0,4).equals("http")) {
							 email = "";
							 break;
						 }
						 if (email.equals("")) {
							 continue;
						 }
						 String numeri = numero.text().replaceAll("interno.*", "").replaceAll("[^0-9]", "");
						 if (numeri.length()<=8) {
							 numeri= "";
							 continue;
						 }
						 if (numeri.equals("")) {
							 continue;
						 }
						 numeri = "+" + numeri;
		
	//CONTROLLO
						 String[] newStr = nome2.split("\\s+");
					     String [] newStr2 = email.split("\\.");
					     if (newStr.length>3) {
					    	 String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
								s.add(referenze);
					     }
					      if (newStr.length==3) {
					    	  String controllo1= newStr[0];
					    	  String controllo2= newStr[1];
					    	  String controllo3= newStr[2];
					    	  
					    	String c1 = controllo1.substring(0, 1);
					    	String c11= controllo1.substring(1, 2);
					    	char c = c1.charAt(0);
							char d = c11.charAt(0);
							
							
							String c2 = controllo2.substring(0, 1);
					    	String c22= controllo2.substring(1, 2);
					    	char e = c2.charAt(0);
							char f = c22.charAt(0);
							
							if (Character.isUpperCase(c) && Character.isUpperCase(d) || Character.isLowerCase(c) && !Character.isLetter(d) || Character.isUpperCase(c) && c11.equals("e")
									|| Character.isUpperCase(c) && c11.equals("a") || Character.isUpperCase(c) && c11.equals("i") || Character.isUpperCase(c) && c11.equals("o")
									|| Character.isUpperCase(c) && c11.equals("u")) {
								controllo1="";
								if (Character.isUpperCase(e) && Character.isUpperCase(f)) {
									controllo2="";
									String controllo4= controllo3.toLowerCase();
									if (!controllo4.equals(newStr2[0])) {
										nome2="";
										email="";
										numeri ="";
										break;
									}else {
										String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
										s.add(referenze);
									}
								}else {
									String controllo5 = (controllo2+controllo3).toLowerCase();
									if (!controllo5.equals(newStr2[0])) {
										nome2="";
										email="";
										numeri ="";
										break;
									}else {
										String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
										s.add(referenze);
									}
									
								}
							}
								}else {
									String controllo6 = newStr[1];
									controllo6= controllo6.toLowerCase();
									if (!controllo6.equals(newStr2[0])) {
										nome2="";
										email="";
										numeri ="";
										break;
									}else {
										String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
										s.add(referenze);
									}
					    	  
					      }
}
				      
					 } break; 
			 } 
			 }
			 
			 
			 List<HtmlElement> tecnici_amm = (page.getByXPath("//*[@id=\"parent-fieldname-text-52044857692f4923992714417390e085\"]/table[1]/tbody/tr[*]"));
			 for (HtmlElement tecnico : tecnici_amm) {
				 String nomi = ((HtmlElement) tecnico.getFirstByXPath("./td[1]")).asText();
				 String cell = ((HtmlElement) tecnico.getFirstByXPath("./td[2]")).asText().replaceAll("-", "");
				 String contatti = ((HtmlElement) tecnico.getFirstByXPath("./td[3]")).asText();
				 
				 String[] newStr = nomi.split("\\s+");
				 if (newStr.length>3){
					 String referenze= nomi + " E-mail: "+ contatti+ " Tel: " + cell;
						s.add(referenze);
				 }
				 if (newStr.length==3) {
					 String nome1 = newStr[0];
					 String nome2 = newStr[1];
					 String nome3 = newStr[2];
					 String [] newStr2 = contatti.split("\\.");
					 String nome4 = newStr[1].toLowerCase();
					 String nome5 = newStr[2].toLowerCase();
					 String controllo = nome4+nome5;
					 if (controllo.equals(newStr2[0])) {
						 nomi = nome1.toUpperCase() + " "+ nome2 + nome3;
						 String referenze = nomi + " "+ contatti+ " " + cell;
						 s.add(referenze);
					 }else {
					 nomi= nome1.toUpperCase()+ " "+ nome2.toUpperCase() + " "+ nome3;
					 String referenze = nomi + " "+ contatti+ " " + cell;
					 s.add(referenze);
					 }
					 
				 }else {
					 String nome1 = newStr[0].toUpperCase();
					 String nome2 = newStr[1]; 
					 nomi= nome1+ " "+ nome2;
					 String referenze = nomi + " "+ contatti+ " " + cell;
					 s.add(referenze);
				 
				 }
			
					 } 
		for (String referenze : s) {
						System.out.println(referenze);
			}
			 
		}
			 
		
		catch(Exception e){
			 e.printStackTrace();
		 
	}

		}
	}


