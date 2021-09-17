package io.swagger.api.dibi;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import io.swagger.annotations.ApiParam;
import io.swagger.api.dibi.model.Dibi;
import io.swagger.api.dibi.model.DibiResult;
import io.swagger.model.FormAdmissibileValue;
import io.swagger.model.FormAnswer;
import io.swagger.model.FormField;
import io.swagger.model.FormFieldValidationRequest;
import io.swagger.model.FormFieldValidationResponse;
import io.swagger.model.FormFieldValuesRequest;
import io.swagger.model.FormFieldValuesResponse;
import io.swagger.model.FormSubmitRequest;
import io.swagger.model.FormSubmitResponse;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

@Controller
public class DibiApiController implements DibiApi {

	private static final Logger log = LoggerFactory.getLogger(DibiApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public DibiApiController(final ObjectMapper objectMapper, final HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<FormSubmitResponse> submit(
			@RequestParam(value = "page", required = false, defaultValue = "") final String page,
			@ApiParam(value = "Json data", required = true) @Valid @RequestBody final FormSubmitRequest data,
			@ApiParam(value = "Algho-Token") @RequestHeader(value = "Algho-Token", required = false) final String alghoToken) {

		final String accept = "application/json"; // request.getHeader("Accept");

		if (accept != null && accept.contains("application/json")) {

			String scelta ="";
			String attività ="";
			String cognome_prof= "";
			for (final FormField field : data.getFields()) {
				if (field.getName().equals("Cognome Docente"))
				    cognome_prof = field.getValue();
				if (field.getName().equals("Tirocini"))
					scelta = field.getValue();
				if (field.getName().equals("Orari"))
					scelta = field.getValue();
				if (field.getName().equals("Nome Attività"))
					attività = field.getValue();
				if (field.getName().equals("Scelta"))
					scelta = field.getValue();
				if (field.getName().equals("Ultime Notizie"))
					scelta = field.getValue();
				if (field.getName().equals("Segreteria"))
					scelta = field.getValue();
				if (field.getName().equals("Info Corsi"))
					scelta = field.getValue();
				if (field.getName().equals("Strutture Dib"))
					scelta = field.getValue();
				if (field.getName().equals("Info Professori"))
					scelta = field.getValue();
				if (field.getName().equals("Domanda Laurea"))
					scelta = field.getValue();
				if (field.getName().equals("Informazione Scelta"))
					scelta = field.getValue();
				
				
			}
			
			final FormSubmitResponse response = new FormSubmitResponse();
	if (scelta.equals("Email") || scelta.equals("Numero di Telefono") || scelta.equals("Pagina Web del Docente") || scelta.equals("Info generali")) {			
			    String baseUrl="https://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento/personale/docenti-2";
				String baseUrl2="https://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento/personale/tecniciamministrativi";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false);
				cognome_prof= cognome_prof.replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase().replaceAll("\\s+","").replaceAll("[^a-zA-Z]", "").replaceAll("'", "");
				final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
				try {
					 Document doc = Jsoup.connect(baseUrl).get();
					 HtmlPage page1 = client.getPage(baseUrl2);
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
							 
							     String link = identificativo.attr("href");
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
							    	 
							    	 String unito1 = (newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase().replaceAll("'", "");
							    	 String unito2 = ((newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()+ (newStr[1]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()).replaceAll("'", "");
							    	 String unito3 = ((newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()+ (newStr[1]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()+ (newStr[2]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()).replaceAll("'", "");
							    	 
							    	 if(unito1.equals(cognome_prof)|| unito2.equals(cognome_prof) || unito3.equals(cognome_prof)) {
							    		 if(scelta.equals("Email")) {
												final FormAnswer answer = new FormAnswer();
												answer.setAnswerText("L'email del docente "+ "è "+ email);
												answerlist.add(answer);
											}else if (scelta.equals("Numero di Telefono")) {
												final FormAnswer answer = new FormAnswer();
												answer.setAnswerText("Il numero di telefono del docente "+ "è "+ numeri);
												answerlist.add(answer);
											}else if (scelta.equals("Pagina Web del Docente")) {
												final FormAnswer answer = new FormAnswer();
												answer.setAnswerText("La pagina web del docente "+ "è la seguente: ");
												answer.setMedia(link);
									            answer.setMediaType("link");
												answerlist.add(answer);
											}else if (scelta.equals("Info generali")) {
												String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
												final FormAnswer answer = new FormAnswer();
												answer.setAnswerText(referenze);
								                answer.setMedia(link);
								                answer.setMediaType("link");
								                answerlist.add(answer);
											}
							    	 }
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
												String unito1 = (newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase().replaceAll("'", "");
										    	String unito2 = ((newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()+ (newStr[1]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()).replaceAll("'", "");
										    	if(unito1.equals(cognome_prof)|| unito2.equals(cognome_prof)) {
												if(scelta.equals("Email")) {
													final FormAnswer answer = new FormAnswer();
													answer.setAnswerText("L'email del docente "+ "è "+ email);
													answerlist.add(answer);
												}else if (scelta.equals("Numero di Telefono")) {
													final FormAnswer answer = new FormAnswer();
													answer.setAnswerText("Il numero di telefono del docente "+ "è "+ numeri);
													answerlist.add(answer);
												}else if (scelta.equals("Pagina Web del Docente")) {
													final FormAnswer answer = new FormAnswer();
													answer.setAnswerText("La pagina web del docente "+ "è la seguente: ");
													answer.setMedia(link);
										            answer.setMediaType("link");
													answerlist.add(answer);
												}else if (scelta.equals("Info generali")) {
													String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
													final FormAnswer answer = new FormAnswer();
													answer.setAnswerText(referenze);
									                answer.setMedia(link);
									                answer.setMediaType("link");
									                answerlist.add(answer);
												}
										    		
										    	}
											}
										}else {
											String controllo5 = (controllo2+controllo3).toLowerCase();
											if (!controllo5.equals(newStr2[0])) {
												nome2="";
												email="";
												numeri ="";
												break;
											}else {
												 String unito1 = (newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase().replaceAll("'", "");
										    	 String unito2 = ((newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()+ (newStr[1]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase()).replaceAll("'", "");
										    	if(unito1.equals(cognome_prof)|| unito2.equals(cognome_prof)) {
										    		if(scelta.equals("Email")) {
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText("L'email del docente "+ "è "+ email);
														answerlist.add(answer);
													}else if (scelta.equals("Numero di Telefono")) {
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText("Il numero di telefono del docente "+ "è "+ numeri);
														answerlist.add(answer);
													}else if (scelta.equals("Pagina Web del Docente")) {
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText("La pagina web del docente "+ "è la seguente: ");
														answer.setMedia(link);
											            answer.setMediaType("link");
														answerlist.add(answer);
													}else if (scelta.equals("Info generali")) {
														String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText(referenze);
										                answer.setMedia(link);
										                answer.setMediaType("link");
										                answerlist.add(answer);
													}
										    	 }
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
												String unito1 = (newStr[0]).replaceAll("ò", "o").replaceAll("è", "e").replaceAll("é", "e").replaceAll("à", "a").replaceAll("ù", "u").replaceAll("ì", "i").toUpperCase().replaceAll("'", "");
										    	if(unito1.equals(cognome_prof)) {
										    		if(scelta.equals("Email")) {
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText("L'email del docente "+ "è "+ email);
														answerlist.add(answer);
													}else if (scelta.equals("Numero di Telefono")) {
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText("Il numero di telefono del docente "+ "è "+ numeri);
														answerlist.add(answer);
													}else if (scelta.equals("Pagina Web del Docente")) {
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText("La pagina web del docente "+ "è la seguente: ");
														answer.setMedia(link);
											            answer.setMediaType("link");
														answerlist.add(answer);
													}else if (scelta.equals("Info generali")) {
														String referenze= nome2 + " E-mail: "+ email+ " Tel: " + numeri;
														final FormAnswer answer = new FormAnswer();
														answer.setAnswerText(referenze);
										                answer.setMedia(link);
										                answer.setMediaType("link");
										                answerlist.add(answer);
													}
								                
										    	 }
											}
							    	  
							      }
		}
						      
							 } break; 
					 } 
					 }
					 

					 
				
				 if (answerlist.size()==0) {
					final FormAnswer answer1 = new FormAnswer();
					final FormAnswer answer2 = new FormAnswer();
					answer1.setAnswerText("Professore non trovato, riprovare.");
					answer2.setAnswerText("Se la ricerca non produce risultati ti consiglio di visitare la sezione \"Docenti\" presente sul sito del Dipartimento.");
					answer2.media("https://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento/personale/docenti-2");
					answer2.mediaType("link");
					answerlist.add(answer1);
					answerlist.add(answer2);
					response.setAnswers(answerlist);
				    return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
				 }else {
				 response.setAnswers(answerlist);
				 return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
				 }		
				}
					 
				
				catch(Exception e){
					 e.printStackTrace();
				 
			}
				finally{
					 client.close();
				}
			}
		
				
		switch (scelta) {
					case "Quando posso richiedere il tirocinio?":{
						String quando_tirocinio = "Si può cominciare un tirocinio interno o esterno quando sul libretto si hanno non più di tre esami ancora da superare. Per maggiori informazioni si consiglia di visitare la sezione \"Tirocini/Stage\" sul sito del Dipartimento.";
						try {
							
			                final FormAnswer answer = new FormAnswer();
			                answer.setMedia("https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/tirocini/tirocini-informatica");
			                answer.setMediaType("link");
			                answer.setAnswerText(quando_tirocinio);
			                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
			                answerlist.add(answer);
			                response.setAnswers(answerlist);

							return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

						} catch (final Exception e) {
							log.error("Couldn't serialize response for content type application/json", e);
							return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
						}
						
						
					
					case "Dove posso trovare i moduli per la richiesta del tirocinio?":{
						String moduli = "I moduli per il tirocinio e le relative informazioni sono disponibili nella sezione \"Tirocini/Stage\" presente sul sito del Dipartimento.";
						try {
							
			                
			                final FormAnswer answer = new FormAnswer();
			                answer.setAnswerText(moduli);
			                answer.setMedia("https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/tirocini/tirocini-informatica");
			                answer.setMediaType("link");
			                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
			                answerlist.add(answer);
			                response.setAnswers(answerlist);

							return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

						} catch (final Exception e) {
							log.error("Couldn't serialize response for content type application/json", e);
							return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
						
					}
						
					
					case "Informazioni Tirocinio Interno":{
						String baseUrl="https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/tirocini/tirocini-informatica";
						WebClient client = new WebClient();
						client.getOptions().setCssEnabled(false);
						client.getOptions().setJavaScriptEnabled(false);
						 
						try {
							 HtmlPage page1 = client.getPage(baseUrl);
							 String interno1 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[1]")).asText();
							 String interno2 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[2]")).asText();
							 String interno3 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[3]")).asText();
							 String interno4 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[4]")).asText();
							 String interno5 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[6]/text()[5]")).asText();							
							 try {
									
					                final FormAnswer answer1 = new FormAnswer();
					                final FormAnswer answer2 = new FormAnswer();
					                final FormAnswer answer3 = new FormAnswer();
					                final FormAnswer answer4 = new FormAnswer();
					                final FormAnswer answer5 = new FormAnswer();
					                answer1.setAnswerText(interno1);
					                answer2.setAnswerText(interno2);
					                answer3.setAnswerText(interno3);
					                answer4.setAnswerText(interno4);
					                answer5.setAnswerText(interno5);
					               
					                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
					                answerlist.add(answer1);
					                answerlist.add(answer2);
					                answerlist.add(answer3);
					                answerlist.add(answer4);
					                answerlist.add(answer5);
					                response.setAnswers(answerlist);

									return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

								} catch (final Exception e) {
									log.error("Couldn't serialize response for content type application/json", e);
									return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
								}	
						}catch(Exception e){
								 e.printStackTrace();
							 }
						finally{
							 client.close();
						}
					}
						
					
					case "Informazioni Tirocinio Esterno":{
						String baseUrl="https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/tirocini/tirocini-informatica";
						WebClient client = new WebClient();
						client.getOptions().setCssEnabled(false);
						client.getOptions().setJavaScriptEnabled(false);
						try {
							 HtmlPage page1 = client.getPage(baseUrl);
							 String esterno1 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[1]")).asText();
							 String esterno2 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/strong[2]")).asText();
							 String esterno3 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[3]")).asText();
							 String esterno4 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[4]")).asText();
							 String esterno5 = ((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/strong[3]")).asText();
							 String esterno6 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[5]")).asText();
							 String esterno7 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[6]")).asText();
							 String esterno8 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[7]")).asText();
							 String esterno9 = ((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/strong[4]")).asText();
							 String esterno10 = ((DomText) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/text()[8]")).asText();
							 String esterno11 = ((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/p[7]/a")).asText();
							 esterno2 = esterno2 + esterno3;
							 esterno4 = esterno4 + " "+ esterno5; 
							 esterno6 = esterno6 + " "+ esterno11;
							 esterno8 = esterno8 + esterno9;
							
							 try {
								
					                final FormAnswer answer1 = new FormAnswer();
					                final FormAnswer answer2 = new FormAnswer();
					                final FormAnswer answer4 = new FormAnswer();
					                final FormAnswer answer6 = new FormAnswer();
					                final FormAnswer answer7 = new FormAnswer();
					                final FormAnswer answer8 = new FormAnswer();
					                final FormAnswer answer10 = new FormAnswer();
					                
					                answer1.setAnswerText(esterno1);
					                answer2.setAnswerText(esterno2);
					                answer4.setAnswerText(esterno4);
					                answer6.setAnswerText(esterno6);
					                answer7.setAnswerText(esterno7);
					                answer8.setAnswerText(esterno8);
					                answer10.setAnswerText(esterno10);
					                
					                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
					                answerlist.add(answer1);
					                answerlist.add(answer2);
					                answerlist.add(answer4);
					                answerlist.add(answer6);
					                answerlist.add(answer7);
					                answerlist.add(answer8);
					                answerlist.add(answer10);
					                
					                response.setAnswers(answerlist);

									return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

								} catch (final Exception e) {
									log.error("Couldn't serialize response for content type application/json", e);
									return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
								}	
							 
				 
						}catch(Exception e){
							 e.printStackTrace();
						 }
					finally{
						 client.close();
					}
					}
						
					
					case "Finestra Temporale Accettazione richieste Tirocinio":{
						String baseUrl="https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/tirocini/tirocini-informatica";
						WebClient client = new WebClient();
						client.getOptions().setCssEnabled(false);
						client.getOptions().setJavaScriptEnabled(false);
						try {
							HtmlPage page1 = client.getPage(baseUrl);
						    HtmlElement finestra1 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6af98676d13d43c88ab06b5a8342a1ac\"]/div[1]");
						    String finestra = finestra1.asText();
						    try {
								
				                final FormAnswer answer = new FormAnswer();
				                answer.setAnswerText(finestra);
				                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
				                answerlist.add(answer);
				                response.setAnswers(answerlist);

								return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

							} catch (final Exception e) {
								log.error("Couldn't serialize response for content type application/json", e);
								return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
							}
						}
					catch(Exception e){
						 e.printStackTrace();
					 }
				finally{
					 client.close();
				}
					}
				
					
		
		case "Orario di apertura":{
			try {
				String apertura= "Il dipartimento di informatica apre alle ore 8";
                final FormAnswer answer = new FormAnswer();
                answer.setAnswerText(apertura);
                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
                answerlist.add(answer);
                response.setAnswers(answerlist);

				return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

			} catch (final Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		case "Orario di chiusura":{
			try {
				String chiusura= "Il dipartimento di informatica chiude alle ore 18";
                final FormAnswer answer = new FormAnswer();
                answer.setAnswerText(chiusura);
                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
                answerlist.add(answer);
                response.setAnswers(answerlist);

				return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

			} catch (final Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		case "Orario settimanale":{
			String baseUrl= "https://www.google.com/search?q=dipartimento+di+informatica+bari&biw=1536&bih=754&sxsrf=AOaemvKmvM3f_3Bu-vfqxvs5q_gAEgM1mw%3A1630344139473&ei=yxMtYZiUHNWDlQeojKXQBw&oq=dipartimento+di+informatica+bari&gs_lcp=Cgdnd3Mtd2l6EAMyBAgjECcyBAgjECcyBggAEBYQHjoHCCMQsAMQJzoHCAAQRxCwAzoLCC4QgAQQxwEQrwE6EAguEIAEEIcCEMcBEK8BEBRKBAhBGABQmyFY5ShgsitoAXACeACAAZkBiAHNBJIBAzAuNJgBAKABAcgBCcABAQ&sclient=gws-wiz&ved=0ahUKEwjYpa2aodnyAhXVQeUKHShGCXoQ4dUDCA8&uact=5";

			try {
				 Document doc = Jsoup.connect(baseUrl).get();
				 String orari = doc.select(".a-h > .vk_bk").text();
				 orari= orari.replaceAll("ì ", "ì").replaceAll(" ", "\n").replaceAll("to\n", "to ").replaceAll("ca\n", "ca ").replaceAll("ì", "ì: dalle ").replaceAll("–", " alle ").replaceAll("18", "18.").replaceAll("Chiuso", "Chiuso."); 
				try {
						
		                final FormAnswer answer = new FormAnswer();
		                answer.setAnswerText(orari);
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer);
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
			catch(Exception e){
				 e.printStackTrace(); 
				 }
			break;
		}
		case "Info Tutorato":{
			String baseUrl= "https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/tutorato/informazioni-generali";
			try {
				 Document doc = Jsoup.connect(baseUrl).get();
				 String tutorato = doc.select("#parent-fieldname-text-5fb787ba13364978ae80be972530afbe > p").text();
				 tutorato = tutorato.replaceAll("Questa sezione.*", "");
				 String tutorato2 = "Per maggiori informazioni sulle attività di tutorato e le candidature come tutor si consiglia di visitare la sezione \"Tutorato\" presente sul sito del Dipartimento.";
				 try {
						
		                final FormAnswer answer = new FormAnswer();
		                final FormAnswer answer1 = new FormAnswer();
		                answer.setAnswerText(tutorato);
		                answer1.setAnswerText(tutorato2);
		                answer1.setMedia("https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/tutorato");
		                answer1.setMediaType("link");
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer);
		                answerlist.add(answer1);
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
				 
			
			catch(Exception e){
				 e.printStackTrace(); 
				 }
			

		}
		
		case "Info Orientamento":{
			String baseUrl2 = "https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/orientamento-e-tutorato-1/servizio-di-consulenze-di-orientamento";
			try {
				 Document doc2 = Jsoup.connect(baseUrl2).get();
				 String orientamento = doc2.select("#parent-fieldname-text-65612ebcd7fc484ea9a5d4ef43b61c6f").text();
				 orientamento= orientamento.replaceAll(" qui.*", ".");
				 String orientamento2 = "Per maggiori informazioni inerenti l'orientamento si consiglia di visitare la sezione \"Orientamento\" sul sito del Dipartimento.";
				 try {
				
		                final FormAnswer answer = new FormAnswer();
		                final FormAnswer answer1 = new FormAnswer();
		                answer.setAnswerText(orientamento);
		                answer1.setAnswerText(orientamento2);
		                answer1.setMedia("https://www.uniba.it/ricerca/dipartimenti/informatica/tutorato/orientamento-e-tutorato-1");
		                answer1.setMediaType("link");
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer);
		                answerlist.add(answer1);
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				
			}
				
			
			catch(Exception e){
				 e.printStackTrace(); 
				 }
		
		
		}
		case "Ultime Notizie":{
			String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica";
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			
			try {
				Document doc = Jsoup.connect(baseUrl).get();
				HtmlPage page1 = client.getPage(baseUrl);
				List<HtmlElement> eventi = (page1.getByXPath("//*[@id=\"portlet-newszona\"]/dd"));
				final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
				String inizio1 = "Ultime notizie: ";
				final FormAnswer answer1 = new FormAnswer();
				answer1.setAnswerText(inizio1);
				answerlist.add(answer1);
				for (HtmlElement evento : eventi) {
					 String not1 = ((HtmlElement) evento.getFirstByXPath("./a")).asText();
					 String url = ((HtmlAnchor) evento.getFirstByXPath("./a")).getHrefAttribute();
					 if (not1.equals("Archivio eventi…") || not1.equals("Altre notizie…")) {
						 not1="";
						 continue; 
					 }
					 final FormAnswer answer = new FormAnswer();
						answer.setAnswerText(not1);
						answer.setMedia(url);
						answer.setMediaType("link");
						answerlist.add(answer);
					 
				 }
				try {
					
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			catch (Exception e) {
				 e.printStackTrace();
			} finally{
				 client.close();
			}
		}
		
		case "Segreteria Studenti":{
			String baseUrl ="https://www.uniba.it/ricerca/dipartimenti/informatica";
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			try {
				
				HtmlPage page1 = client.getPage(baseUrl);
				
				HtmlElement segreteria_stud= page1.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/div");
				String segr_stud = segreteria_stud.asText().trim();
				
				try {
					
	                final FormAnswer answer1 = new FormAnswer();
	              
	                answer1.setAnswerText(segr_stud);
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer1);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			catch(Exception e){
				e.printStackTrace(); 
			} finally{
				 client.close();
			}
		
		}
		
		case "Segreteria Didattica":{
			String baseUrl ="https://www.uniba.it/ricerca/dipartimenti/informatica";
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			try {
				
				HtmlPage page1 = client.getPage(baseUrl);
				
				
				HtmlElement segreteria_did = page1.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[9]");
				String segr_did = segreteria_did.asText().trim();
				
				try {
					
	                
	                final FormAnswer answer2 = new FormAnswer();
	               
	                
	                answer2.setAnswerText(segr_did);
	               
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                
	                answerlist.add(answer2);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			catch(Exception e){
				e.printStackTrace(); 
			} finally{
				 client.close();
			}
		
		}
		case "Pratiche Studenti Stranieri":{
			String baseUrl ="https://www.uniba.it/ricerca/dipartimenti/informatica";
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			try {
				
				HtmlPage page1 = client.getPage(baseUrl);
				
				HtmlElement stud_s = page1.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[10]");
				String stranieri = stud_s.asText().trim();

				try {
					
	              
	                final FormAnswer answer3 = new FormAnswer();
	                
	                answer3.setAnswerText(stranieri);
	               
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	               
	                answerlist.add(answer3);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			catch(Exception e){
				e.printStackTrace(); 
			} finally{
				 client.close();
			}
		
		}
		
		case "Segreteria Orientamento, Tirocinio e Job Placement":{
			String baseUrl ="https://www.uniba.it/ricerca/dipartimenti/informatica";
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			try {
				
				HtmlPage page1 = client.getPage(baseUrl);
				
				
				HtmlElement orient = page1.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[11]");
				String segr_tirocini = orient.asText().trim();
				try {
					
	                final FormAnswer answer4 = new FormAnswer();
	                
	               
	                answer4.setAnswerText(segr_tirocini);
	               
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	               
	                answerlist.add(answer4);
	               
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			catch(Exception e){
				e.printStackTrace(); 
			} finally{
				 client.close();
			}
		
		}
		case "Segreteria Didattica sede Taranto":{
			String baseUrl ="https://www.uniba.it/ricerca/dipartimenti/informatica";
			WebClient client = new WebClient();
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);
			try {
				
				HtmlPage page1 = client.getPage(baseUrl);
				
				HtmlElement segreteria_taranto = page1.getFirstByXPath("//*[@id=\"portletwrapper-706c6f6e652e7269676874636f6c756d6e0a636f6e746578740a2f756e696261342f726963657263612f646970617274696d656e74692f696e666f726d61746963612f646970617274696d656e746f2d64692d696e666f726d61746963610a7370617a696f2d73747564656e7469\"]/dl/dd/p[12]");
				String segr_ta= segreteria_taranto.asText().trim();
				try {
				
	                final FormAnswer answer5 = new FormAnswer();
	             
	                answer5.setAnswerText(segr_ta);
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	          
	                answerlist.add(answer5);
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
			catch(Exception e){
				e.printStackTrace(); 
			} finally{
				 client.close();
			}
		
		}
		
		
		case "Dove si trova la segreteria?":{
			String dove = "La segreteria è ubicata al piano seminterrato del Dipartimento.";
			
			try {
				final FormAnswer answer1 = new FormAnswer();
				answer1.setAnswerText(dove);
				answer1.media("https://i.pinimg.com/originals/88/8d/b3/888db367e4a096e10c6cfda6d7cdbe86.jpg");
				answer1.mediaType("img");
				final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
				answerlist.add(answer1);
                response.setAnswers(answerlist);

				return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

			} catch (final Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	
		}

			case "Corsi di Laurea attivi":{
				String baseUrl= "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/corsi-di-laurea/corsi-di-laurea";
				
				try {
					 Document doc = Jsoup.connect(baseUrl).get();
					 final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
					 final FormAnswer answer = new FormAnswer();
					 String aggiunta = "Corsi di Laurea Attivi: ";
						answer.setAnswerText(aggiunta);
						answerlist.add(answer);
						for ( Element riga : doc.select("#content-core")) {
							Elements corsi = riga.getElementsByTag("a");
							
							for (Element corso : corsi) {
								
								String corsi_attivi = corso.text();
								String link = corso.attr("href");
								if (corsi_attivi.equals("Magistrale in Informatica")) {
									break;
								}else {
									
									
									String corso1 = corsi_attivi;
									final FormAnswer answer1 = new FormAnswer();
									answer1.setAnswerText(corso1);
									answer1.setMedia(link);
									answer1.setMediaType("link");
									answerlist.add(answer1);
									
								}
								
							}
							break;
							}
					try {
					
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				catch(Exception e){
					 e.printStackTrace(); 
					 }
				
			}
			
			case "Cosa sono le Propedeuticità?":{
				String risposta = "Le propedeuticità sono degli esami dei vari corsi di laurea che consentono di annullare il debito formativo, in caso di bocciatura o mancata partecipazione al test di valutazione, e/o di accedere ad esami che li richiedono come competenze di base.";
			
				try {
					final FormAnswer answer1 = new FormAnswer();
					answer1.setAnswerText(risposta);
					final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
					answerlist.add(answer1);
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
		
			
			}
			
			case "Immatricolazione ai Corsi di Laurea":{
				String baseUrl2 = "https://www.uniba.it/studenti/segreterie-studenti/procedure";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false); 
				
				
				try {
					
					 HtmlPage page1 = client.getPage(baseUrl2);
					
					 HtmlElement immatricolazione1 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6c7ce2485d8643ec887bcc2d4ae88343\"]/p[2]");
					 HtmlElement immatricolazione2 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6c7ce2485d8643ec887bcc2d4ae88343\"]/p[3]");
					 HtmlElement immatricolazione3 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-6c7ce2485d8643ec887bcc2d4ae88343\"]/p[4]");
					 String imm1 = immatricolazione1.asText();
					 String imm2 = immatricolazione2.asText();
					 String imm3 = immatricolazione3.asText();
					 
					 try {
							
			                final FormAnswer answer1 = new FormAnswer();
			                final FormAnswer answer2 = new FormAnswer();
			                final FormAnswer answer3 = new FormAnswer();
			                
			                answer1.setAnswerText(imm1);
			                answer2.setAnswerText(imm2);
			                answer3.setAnswerText(imm3);
			                
			                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
			                answerlist.add(answer1);
			                answerlist.add(answer2);
			                answerlist.add(answer3);
			                
			                response.setAnswers(answerlist);

							return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

						} catch (final Exception e) {
							log.error("Couldn't serialize response for content type application/json", e);
							return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					 
					 
				}
				catch(Exception e){
					 e.printStackTrace(); 
					 }
				finally{
					 client.close();
				}
				
			}
				
			
			case "Cosa sono i Test di Valutazione?":{
				String baseUrl3 = "https://www.uniba.it/ricerca/dipartimenti/informatica/test-di-valutazione/informazioni-generali";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false); 
				
				
				try {
					 HtmlPage page2 = client.getPage(baseUrl3); 
					
					 HtmlElement test_valutazione1= page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-97c981db6adf47b785a7a42ca4ae8f20\"]/div[1]");
					 HtmlElement test_valutazione2= page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-97c981db6adf47b785a7a42ca4ae8f20\"]/div[2]");
					 HtmlElement test_valutazione3= page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-97c981db6adf47b785a7a42ca4ae8f20\"]/div[4]/p");
					 String test1 = test_valutazione1.asText();
					 String test2 = test_valutazione2.asText();
					 String test3 = test_valutazione3.asText();
					 try {
							
			                final FormAnswer answer1 = new FormAnswer();
			                final FormAnswer answer2 = new FormAnswer();
			                final FormAnswer answer3 = new FormAnswer();
			                
			                answer1.setAnswerText(test1);
			                answer2.setAnswerText(test2);
			                answer3.setAnswerText(test3);
			                
			                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
			                answerlist.add(answer1);
			                answerlist.add(answer2);
			                answerlist.add(answer3);
			                
			                response.setAnswers(answerlist);

							return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

						} catch (final Exception e) {
							log.error("Couldn't serialize response for content type application/json", e);
							return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
				}
				catch(Exception e){
					 e.printStackTrace(); 
					 }
				finally{
					 client.close();
				}
			}
			case "Quando posso effettuare la domanda di laurea?":{
				String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/modulistica-1/informazioni-esame-di-laurea";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false);
				try {
					HtmlPage page1 = client.getPage(baseUrl);
					
					HtmlElement quando1 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[2]");
					HtmlElement quando2 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/ul[1]");
					String q1 = quando1.asText();
					String q2 = quando2.asText();
					 try {
							
			                final FormAnswer answer1 = new FormAnswer();
			                final FormAnswer answer2 = new FormAnswer();
			                
			                answer1.setAnswerText(q1);
			                answer2.setAnswerText(q2);
			                
			                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
			                answerlist.add(answer1);
			                answerlist.add(answer2);
			                
			                response.setAnswers(answerlist);

							return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

						} catch (final Exception e) {
							log.error("Couldn't serialize response for content type application/json", e);
							return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
				
					}
				catch(Exception e){
					 e.printStackTrace();
					 }
				finally{
					 client.close();
				}
			}
				
			case "Dove si effettua la domanda di laurea?":{
				String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/modulistica-1/informazioni-esame-di-laurea";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false); 
				try {
					HtmlPage page1 = client.getPage(baseUrl);
					HtmlElement dove1 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[4]");
					HtmlElement dove2 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[5]");
					String d1 = dove1.asText();
					String d2 = dove2.asText();
					 try {
							
			                final FormAnswer answer1 = new FormAnswer();
			                final FormAnswer answer2 = new FormAnswer();
			                
			                answer1.setAnswerText(d1);
			                answer2.setAnswerText(d2);
			                
			                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
			                answerlist.add(answer1);
			                answerlist.add(answer2);
			                
			                response.setAnswers(answerlist);

							return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

						} catch (final Exception e) {
							log.error("Couldn't serialize response for content type application/json", e);
							return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}
				catch(Exception e){
					 e.printStackTrace();
					 }
				finally{
					 client.close();
				}
			}
				
			case "Che cos'è AlmaLaurea?":
			{
				String almalaurea = "Per maggiori informazioni a riguardo può visitare la pagina dedicata. Si ricorda che l'iscrizione ad AlmaLaurea, con conseguente "
						+ "compilazione del questionario, è richiesta per il completamento della domanda di laurea. ";
				 try {
						
		                final FormAnswer answer = new FormAnswer();
		                
		                answer.setAnswerText(almalaurea);
		                answer.setMedia("https://www.almalaurea.it/lau/afam/brochure_laureandi");
		                answer.setMediaType("link");
		                
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer);
		                
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
			
			case "Che cos'è BiblioTela?":{
				String bibliotela = "BiblioTela è un servizio messo a disposizione da SCeRPA che permette al laureando di sottomettere la tesi, al relatore di approvarla, e alla commissione di laurea di visualizzarla"
						+ "una volta archiviata.È possibile visualizzare la guida all'uso tramite il seguente link.";
				 try {
						
		                final FormAnswer answer = new FormAnswer();
		                
		                answer.setAnswerText(bibliotela);
		                answer.setMedia("https://www.uniba.it/coronavirus/didattica-online-e-in-presenza/didattica-online/guida-uso-bibliotela-laureandi-relatori");
		                answer.setMediaType("link");
		                
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer);
		                
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
				
			
			case "Quali sono i documenti da consegnare prima della seduta di laurea?":{
				String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/didattica/modulistica-1/informazioni-esame-di-laurea";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false); 
				try {
					HtmlPage page1 = client.getPage(baseUrl);
					
					String quali1 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[6]")).asText();
					String quali2 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[7]")).asText();
					String quali3 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[8]")).asText();
					String quali4 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[9]")).asText();
					String quali5 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/ul[2]")).asText();
					String quali6 =((HtmlElement) page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-a7105f3dcbdc4cf79479260a481ba41c\"]/p[10]")).asText();
					
					try {
			
		                final FormAnswer answer1 = new FormAnswer();
		                final FormAnswer answer2 = new FormAnswer();
		                final FormAnswer answer3 = new FormAnswer();
		                final FormAnswer answer4 = new FormAnswer();
		                final FormAnswer answer5 = new FormAnswer();
		                final FormAnswer answer6 = new FormAnswer();
		                answer1.setAnswerText(quali1);
		                answer2.setAnswerText(quali2);
		                answer3.setAnswerText(quali3);
		                answer4.setAnswerText(quali4);
		                answer5.setAnswerText(quali5);
		                answer6.setAnswerText(quali6);
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer1);
		                answerlist.add(answer2);
		                answerlist.add(answer3);
		                answerlist.add(answer4);
		                answerlist.add(answer5);
		                answerlist.add(answer6);
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				
					}
				catch(Exception e){
					 e.printStackTrace();
					 }
				finally{
					 client.close();
				}
				
			}	
			
			case "Informazioni piattaforma Ada":{
				 String Ada = "Ada è la piattaforma di e-learning del Dipartimento di Informatica di Bari. Gli studenti potranno accedere alle varie sezioni dedicate ai vari corsi, previa iscrizione, per poter reperire: il materiale didattico usato a lezione dai docenti, i programmi dei vari corsi, i contatti ed i giorni di ricevimento dei docenti. Inoltre potranno rimanere aggiornati sulle novità e su eventuali comunicazioni da parte dei Docenti dei corsi ai quali sono iscritti e sulle ultime news del dipartimento.";
				 try {
						
		                final FormAnswer answer = new FormAnswer();
		                
		                answer.setAnswerText(Ada);
		                answer.setMedia("https://elearning.di.uniba.it/");
		                answer.setMediaType("link");
		                
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer);
		                
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
			
			
			case "Chi è il Direttore?":{
				String direttore= "Il direttore del dipartimento è il Professore Donato Malerba. Ecco i suoi contatti:"+ "\n" +  "E-mail: donato.malerba@uniba.it oppure direttore.dib@uniba.it;" +"\n" + "Telefono: +390805443269.";
				try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText(direttore);
	                answer.setMedia("https://www.uniba.it/docenti/malerba-donato");
	                answer.setMediaType("link");
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "La Storia del Dib":{
				String storia = "Il Dib fu istituito il 1 gennaio 1992, come trasformazione del precedente Istituto di Scienze dell'Informazione. Qui, l'informatica è entrata nel panorama dell'insegnamento universitario italiano già nel 1969, con il corso di laurea in Scienze dell'Informazione, secondo in Italia per istituzione. A supporto c'era il Centro Studi e Applicazioni in Tecnologie Avanzate (C.S.A.T.A), che con le sue infrastrutture tecnologiche avanzate, contribuì a formare la prima generazione di informatici italiani. In mezzo secolo, il Dib è cresciuto molto e ora, con i suoi 48 docenti di discipline informatiche, è il più grande Dipartimento di Informatica dell'Italia meridionale. ll Dib è impegnato da lungo tempo in due corsi di laurea triennale su Bari e uno su Taranto, con una laurea magistrale in Computer Science (in lingua inglese) e in Data Science a Bari e una laurea magistrale in Sicurezza Informatica a Taranto. La formazione universitaria offerta dal Dib si avvantaggia di una ricerca avanzata per lo studio e l'applicazione di nuovi metodi e tecniche informatiche. Molte ricerche sono condotte nell’ambito dell’intelligenza artificiale. L'apprendimento automatico (machine learning) e il data mining permettono l'estrazione della conoscenza a partire da grandi quantità di dati (Big Data) per applicarla in vari contesti. L'elaborazione del linguaggio naturale e i sistemi semantici abilitano il ritrovamento ed il filtraggio personalizzato dell’informazione. Altra area di ricerca è quella dell'ingegneria del software, con attenzione ai processi per produrre software di qualità. Altre ricerche riguardano l'interazione con oggetti intelligenti (IoT), in mondi virtuali e con realtà aumentata, ma anche la visualizzazione di enormi quantità di dati per favorirne l'analisi e la comprensione.";
try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText(storia);
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Informazioni Erasmus":{
				String erasmus = "Per maggiori informazioni inerenti l'Erasmus si consiglia di visitare la pagina dedicata";
				try {
					
	                final FormAnswer answer = new FormAnswer();
	                answer.setAnswerText(erasmus);
	                answer.setMedia("https://uniba.erasmusmanager.it/studenti/");
	                answer.setMediaType("link");
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			
			}
			
			case "Informazioni Covid":{
				String risposta="A tutti gli studenti viene richiesto di segnalare la propria presenza in Dipartimento registrandosi mediante scansione di un QR code. Dal 6 agosto è entrato in vigore il decreto-legge n.111 che richiede la verifica della certificazione verde (Green Pass) agli studenti iscritti ai corsi di studio, anche post-laurea, che accedono alle aule per la frequenza delle lezioni e per gli esami di profitto o di laurea, ovvero che accedono ai laboratori didattici e di ricerca e alle sale lettura di pertinenza del Dipartimento. Essa dovrà avvenire a campione, nella misura ritenuta congrua dal verificatore.";
			
try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText(risposta);
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			
			
			}

			case "Biblioteca":{
				String baseUrl3 = "https://www.uniba.it/bibliotechecentri/informatica/biblioteca-di-informatica";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false);
				
				try {
				
					HtmlPage page3 = client.getPage(baseUrl3);
					
					
					HtmlElement biblioteca1 = page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/p[1]");
					String biblioteca2 = ((HtmlElement)page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[5]")).asText();
					String biblioteca3 = ((HtmlElement)page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[6]")).asText();
					String biblioteca4 = ((HtmlElement)page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[7]")).asText();
					String biblioteca5 = ((HtmlElement)page3.getFirstByXPath("//*[@id=\"parent-fieldname-text-fe1d2d7c904d4a488bbd4ea7f746aae5\"]/div[5]/div/p[8]")).asText();
					String dove_biblioteca = biblioteca1.asText();
				
					
					String biblioteca6 = "Per maggiori informazioni si consiglia di visitare la sezione \"Biblioteca\" presente sul sito del dipartimento";
					
					try {
					
		                final FormAnswer answer1 = new FormAnswer();
		                final FormAnswer answer2 = new FormAnswer();
		                final FormAnswer answer3 = new FormAnswer();
		                final FormAnswer answer4 = new FormAnswer();
		                final FormAnswer answer5 = new FormAnswer();
		                final FormAnswer answer6 = new FormAnswer();
		                answer1.setAnswerText("La biblioteca si trova: "+dove_biblioteca);
		                answer1.media("https://i.pinimg.com/originals/62/c8/10/62c810cdb1f1184894d6538aed9fc656.jpg");
		                answer1.mediaType("img");
		                answer2.setAnswerText(biblioteca2);
		                answer3.setAnswerText(biblioteca3);
		                answer4.setAnswerText(biblioteca4);
		                answer5.setAnswerText(biblioteca5);
		                answer6.setAnswerText(biblioteca6);
		                answer6.setMedia("https://www.uniba.it/bibliotechecentri/informatica/biblioteca-di-informatica");
		                answer6.setMediaType("link");
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer1);
		                answerlist.add(answer2);
		                answerlist.add(answer3);
		                answerlist.add(answer4);
		                answerlist.add(answer5);
		                answerlist.add(answer6);
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					
					
					
				}
				catch(Exception e){
					 e.printStackTrace();
					 }finally{
						 client.close();
						}	
			}
			
			
			case "Laboratori Didattici":{
				String baseUrl = "https://www.uniba.it/ricerca/dipartimenti/informatica/laboratori-didattici-1";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false);	
				try {
					HtmlPage page1 = client.getPage(baseUrl);
					
					HtmlElement didattici1 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-7c69d602b1b94559ae0d823a3e120130\"]/p[1]");
					HtmlElement didattici2 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-7c69d602b1b94559ae0d823a3e120130\"]/p[2]");
					HtmlElement didattici3 = page1.getFirstByXPath("//*[@id=\"parent-fieldname-text-7c69d602b1b94559ae0d823a3e120130\"]/p[3]");
					String did1 = didattici1.asText().replaceAll("I software.*", "");
					String did2 = didattici2.asText();
					String did3 = didattici3.asText();
					String did4 = "Per maggiori informazioni inerenti alle modalità di accesso si consiglia di visitare la sezione \"Laboratori Didattici\" presente sul sito del Dipartimento";
					try {
						
		                final FormAnswer answer1 = new FormAnswer();
		                final FormAnswer answer2 = new FormAnswer();
		                final FormAnswer answer3 = new FormAnswer();
		                final FormAnswer answer4 = new FormAnswer();
		                answer1.setAnswerText(did1);
		                answer2.setAnswerText(did2);
		                answer2.media("https://i.pinimg.com/originals/c3/dc/6a/c3dc6ab1a4ffb8ab463f92385dca7177.jpg");
		                answer2.mediaType("img");
		                answer3.setAnswerText(did3);
		                answer4.setAnswerText(did4);
		                answer4.setMedia("https://www.uniba.it/ricerca/dipartimenti/informatica/laboratori-didattici-1");
		                answer4.setMediaType("link");
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer1);
		                answerlist.add(answer2);
		                answerlist.add(answer3);
		                answerlist.add(answer4);
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					
					
					
					
				
				}
				catch(Exception e){
					 e.printStackTrace();
					 }finally{
						 client.close();
						}

			
			}
			
			case "Laboratori Tesisti":{
				String baseUrl2 = "https://www.uniba.it/ricerca/dipartimenti/informatica/manuzio/laboratorio-manuzio";
				WebClient client = new WebClient();
				client.getOptions().setCssEnabled(false);
				client.getOptions().setJavaScriptEnabled(false);
				
				try {
					
					HtmlPage page2 = client.getPage(baseUrl2);
					HtmlElement tesisti1 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-d455f35fd63f4f92a619713c926d8282\"]/p[1]");
					HtmlElement tesisti2 = page2.getFirstByXPath("//*[@id=\"parent-fieldname-text-d455f35fd63f4f92a619713c926d8282\"]/p[2]");
					String tes1 = tesisti1.asText();
					String tes2 =tesisti2.asText();
					String tes3 = "Per maggiori informazioni inerenti alle modalità di accesso e al modulo di attivazione dell'account si consiglia di visitare la sezione \"Laboratorio Tesisti\" presente sul sito del Dipartimento";
				
					try {
					
		                final FormAnswer answer1 = new FormAnswer();
		                final FormAnswer answer2 = new FormAnswer();
		                final FormAnswer answer3 = new FormAnswer();
		                
		                answer1.setAnswerText(tes1);
		                answer2.setAnswerText(tes2);
		                answer3.setAnswerText(tes3);
		                answer3.setMedia("https://www.uniba.it/ricerca/dipartimenti/informatica/manuzio/laboratorio-manuzio");
		                answer3.setMediaType("link");
		                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
		                answerlist.add(answer1);
		                answerlist.add(answer2);
		                answerlist.add(answer3);
		                
		                response.setAnswers(answerlist);

						return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

					} catch (final Exception e) {
						log.error("Couldn't serialize response for content type application/json", e);
						return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					
				}
				catch(Exception e){
					 e.printStackTrace();
					 }finally{
						 client.close();
						}
			}
			
			
			
			case "Piano Seminterrato Dipartimento":{
                  try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/de/fc/b6/defcb6693a9f944864b0e2aa3e0a9ba8.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			
			
			case "Piano Rialzato Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/be/46/10/be46102eea39c28e2e822534427bfe17.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Primo Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/da/e4/ff/dae4ffb0b2e63b23169f856ddaca1c92.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Secondo Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/e8/f5/cc/e8f5ccaf63a34d7b3463a6f423bbb4d9.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Terzo Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/9d/6b/e0/9d6be05786397ea6459bf52df87a799d.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Quarto Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/5b/e6/c5/5be6c53ef0e563ed5c6883cd25b12365.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Quinto Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/e2/12/c3/e212c3ce8b84a3979ebe3b2cfd4805bf.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			
			case "Sesto Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/fb/76/ab/fb76ab65686f72ac8c73bbcab71d82c5.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			
			case "Settimo Piano Dipartimento":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/d1/cf/e9/d1cfe9bbef44d262f86614b881bdd9a2.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Piano Terra Palazzo delle Aule":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/02/01/ca/0201caa81371889feccbbec59e388101.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Primo Piano Palazzo delle Aule":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/d2/a8/f1/d2a8f181ba8abc16cef08ef2ff271177.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Secondo Piano Palazzo delle Aule":{
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco a te la piantina del " + scelta + ":");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/ce/c9/f6/cec9f67277143e061a3977ea01a8a02f.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			case "Elenco dei Locali per ogni Piano":{ 
                try {
					
	                final FormAnswer answer = new FormAnswer();
	                
	                answer.setAnswerText("Ecco una guida contenente la divisione dei locali per ogni piano.");
	                answer.mediaType("img");
	                answer.media("https://i.pinimg.com/originals/bd/1f/00/bd1f005e49930ffaecf030a52b419ae4.jpg");
	                
	                
	                final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
	                answerlist.add(answer);
	                
	                response.setAnswers(answerlist);

					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

				} catch (final Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
			
			}
			
			
			
			
			
			
			
		}
		if (!attività.isEmpty()) {
			String baseUrl="https://classbook.di.uniba.it/";
			
			try {
				 Document doc = Jsoup.connect(baseUrl).get();
				 Set<String> s = new LinkedHashSet<>();
				 Set<String> a = new LinkedHashSet<>();
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
					  s.add(prova);
					 }
					 break;
					 }
					
					 }
				 final List<FormAnswer> answerlist = new ArrayList<FormAnswer>();
				if (s.isEmpty()) {
					final FormAnswer answer1 = new FormAnswer();
					answer1.setAnswerText("Nessuna attività in data odierna");
					answerlist.add(answer1);
					response.setAnswers(answerlist);
					final FormAnswer answer2 = new FormAnswer();
					answer2.setAnswerText("Per verificare quotidianamente le attività didattiche del Dib puoi visitare la sezione \"Occupazione aule/laboratori\" presente sul sito del Dipartimento");
					answer2.setMedia("https://classbook.di.uniba.it/");
	                answer2.setMediaType("link");
	                answerlist.add(answer2);
	                response.setAnswers(answerlist);
					return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
				} else if(attività.toLowerCase().equals("tutte")){
					for (String prova : s) {
		                final FormAnswer answer = new FormAnswer();
		                
		                answer.setAnswerText(prova);
		                answerlist.add(answer);
		                }
					 final FormAnswer answer2 = new FormAnswer();
					 answer2.setAnswerText("Per verificare quotidianamente le attività didattiche del Dib puoi visitare la sezione \"Occupazione aule/laboratori\" presente sul sito del Dipartimento");
					 answer2.setMedia("https://classbook.di.uniba.it/");
		             answer2.setMediaType("link");
		             answerlist.add(answer2);
					 response.setAnswers(answerlist);
					 return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
				}else {
				 for (String prova : s) {
					 String[] newStr = prova.toLowerCase().split("\\s+");
					 String[] newStr2 = attività.toLowerCase().split("\\s+");
					 int i;
					 int j;
					 if (newStr2.length==1) {
					 for (i=0; i<newStr.length;i++) {
						 if (attività.toLowerCase().equals(newStr[i])) {
							 final FormAnswer answer = new FormAnswer();
				                answer.setAnswerText(prova);
				                answerlist.add(answer);
				                break;
				                }
						 }
					 }else {
						 for (i=0; i<newStr.length;i++) {
							 for (j=0; j<newStr2.length;j++) {
							 if (newStr2[j].equals(newStr[i])) {
								 a.add(prova);
					             break;
					                }
							 }
					 }
						 
						 }
					 
				 }        
				 for (String risposta : a) {
							final FormAnswer answer = new FormAnswer();
				            answer.setAnswerText(risposta);
				            answerlist.add(answer);
						 }
				
				if (answerlist.size()==0) {
					final FormAnswer answer1 = new FormAnswer();
					answer1.setAnswerText("Non sono riuscita a trovare l'attività desiderata, per sicurezza ecco tutte le attività previste per oggi: ");
					answerlist.add(answer1);
					
					for (String prova : s) {
		                final FormAnswer answer = new FormAnswer();
		                answer.setAnswerText(prova);
		                answerlist.add(answer);
		                }
					final FormAnswer answer2 = new FormAnswer();
					answer2.setAnswerText("Per verificare quotidianamente le attività didattiche del Dib puoi visitare la sezione \"Occupazione aule/laboratori\" presente sul sito del Dipartimento");
					answer2.setMedia("https://classbook.di.uniba.it/");
	                answer2.setMediaType("link");
	                answerlist.add(answer2);
					response.setAnswers(answerlist);
					
				    return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
				}
				final FormAnswer answer2 = new FormAnswer();
				answer2.setAnswerText("Per verificare quotidianamente le attività didattiche del Dib puoi visitare la sezione \"Occupazione aule/laboratori\" presente sul sito del Dipartimento");
				answer2.setMedia("https://classbook.di.uniba.it/");
                answer2.setMediaType("link");
                answerlist.add(answer2);
				response.setAnswers(answerlist);
				return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
				 }
			}
			catch(Exception e){
				 e.printStackTrace();
			 }
		}
		

		
		
		
		
		
		
		}


		return new ResponseEntity<FormSubmitResponse>(HttpStatus.NOT_IMPLEMENTED);
		
	}

	@Override
	public ResponseEntity<FormFieldValidationResponse> validate(
			@ApiParam(value = "Json data", required = true) @Valid @RequestBody final FormFieldValidationRequest data,
			@ApiParam(value = "Algho-Token") @RequestHeader(value = "Algho-Token", required = false) final String alghoToken) {

		final String accept = "application/json"; // request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			
		}

		return new ResponseEntity<FormFieldValidationResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<FormFieldValuesResponse> values(
			@ApiParam(value = "Json data", required = true) @Valid @RequestBody final FormFieldValuesRequest data,
			@ApiParam(value = "JWT Algho-Token") @RequestHeader(value = "Algho-Token", required = false) final String alghoToken) {

		final String accept = "application/json"; // request.getHeader("Accept");

		if (accept != null && accept.contains("application/json")) {
			try {

				final FormFieldValuesResponse response = new FormFieldValuesResponse();

				if (data.getFieldName().equals("city")) {
					final List<FormAdmissibileValue> values = new ArrayList<FormAdmissibileValue>();
					values.add(new FormAdmissibileValue().name("Siena"));
					values.add(new FormAdmissibileValue().name("Firenze"));
					values.add(new FormAdmissibileValue().name("Roma"));
					values.add(new FormAdmissibileValue().name("Milano"));
					values.add(new FormAdmissibileValue().name("Bologna"));
					response.setValues(values);
				}

				return new ResponseEntity<FormFieldValuesResponse>(response, HttpStatus.OK);

			} catch (final Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormFieldValuesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<FormFieldValuesResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

}
