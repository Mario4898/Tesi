import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Orari {

	public static void main(String[] args) {
		String baseUrl= "https://www.google.com/search?sxsrf=ALeKk02_ndmpxsbyFzJ9Or40zmGgt93GFA:1629647325235&q=dipartimento+di+informatica+universit%C3%A0+degli+studi+di+bari+aldo+moro+orari&ludocid=3433950482164875583&sa=X&ved=2ahUKEwiBw7ev_cTyAhWSiqQKHTgBBHYQ6BMwHXoECCwQAg&biw=1536&bih=754&dpr=1.25";

		try {
			 Document doc = Jsoup.connect(baseUrl).get();
			 String orari = doc.select(".AdUYUd.vk_bk > .WgFkxc > tbody").text();
			 orari= orari.replaceFirst("Chiuso ", "chiuso\n").replaceAll("ì ", "ì").replaceAll(" ", "\n").replaceAll("to\n", "to ").replaceAll("ca\n", "ca ").replaceAll("ì", "ì: dalle ").replaceAll("–", " alle ").replaceAll("Chiuso", "chiuso\n");
			 System.out.println(orari);
		}
		catch(Exception e){
			 e.printStackTrace(); 
			 }
		 

	}

	}


