package io.swagger.api.demo;

import io.swagger.model.FormAdmissibileValue;
import io.swagger.model.FormAnswer;
import io.swagger.model.FormField;
import io.swagger.model.FormFieldValidationRequest;
import io.swagger.model.FormFieldValidationResponse;
import io.swagger.model.FormFieldValuesRequest;
import io.swagger.model.FormFieldValuesResponse;
import io.swagger.model.FormSubmitRequest;
import io.swagger.model.FormSubmitResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-18T12:39:28.338Z")

@Controller
public class DemoApiController implements DemoApi {

	private static final Logger log = LoggerFactory.getLogger(DemoApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	private final String token;

	@org.springframework.beans.factory.annotation.Autowired
	public DemoApiController(ObjectMapper objectMapper, 
			HttpServletRequest request, @Value("${token}") String token) {
		
		this.objectMapper = objectMapper;
		this.request = request;
		this.token = token;
	}

	public ResponseEntity<FormSubmitResponse> submit(
			@ApiParam(value = "Json data", required = true) @Valid @RequestBody FormSubmitRequest data,
			@ApiParam(value = "Algho-Token") @RequestHeader(value = "Algho-Token", required = false) String alghoToken) {

		String accept = "application/json"; // request.getHeader("Accept");
		
		FormSubmitResponse response = new FormSubmitResponse();
		FormAnswer answer = new FormAnswer();
		
		if (alghoToken == null || !alghoToken.contains(this.token)) {
	        answer.setAnswerText("Accesso negato. Token non valido.");
			response.addAnswersItem(answer);
			try {
				return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}

		if (accept != null && accept.contains("application/json")) {

			if (data.getFormName().equals("max - prenotazione concerto")) {
				
				String dataPrenotazione = "";
				String codiceFiscale = "";
				String genereMusicale = "";
				String marcaChitarra = "";
				String canzone = "";

				for (FormField field : data.getFields()) {
					String fieldName = field.getName();
					
					switch (fieldName) {
					case "data":
						dataPrenotazione = field.getValue();
						break;
					case "codice fiscale":
						codiceFiscale = field.getValue();
						break;
					case "genere musicale":
						genereMusicale = field.getValue();
						break;
					case "marca chitarra":
						marcaChitarra = field.getValue();
						break;
					case "canzone":
						canzone = field.getValue();
						break;
					default:
						break;
					}	
				}
				
				StringBuilder dettagliPrenotazioneBld = new StringBuilder();
				dettagliPrenotazioneBld
					.append("data: " + dataPrenotazione)
					.append("\ncodice fiscale: " + codiceFiscale)
					.append("\ngenere: " + genereMusicale)
					.append("\nmarca chitarra: " + marcaChitarra)
					.append("\ncanzone: " + canzone);
				String dettagliPrenotazione = dettagliPrenotazioneBld.toString();
				String dettagliPrenotazioneData = Base64.getUrlEncoder().encodeToString(dettagliPrenotazione.getBytes());
				String dettagliPrenotazioneMsg =
						"data:text/txt;base64,"
						.concat(dettagliPrenotazioneData);
				answer.setAnswerText("Prenotazione effettuata!");
				answer.setMediaType("file");
				answer.setMedia(dettagliPrenotazioneMsg);
				response.addAnswersItem(answer);
				
				log.info("Form ricevuto: {}", data.getFormName());
				log.info("data: {}", dataPrenotazione);
				log.info("Codice fiscale: {}", codiceFiscale);
				log.info("genere: {}", genereMusicale);
				log.info("marca chitarra: {}", marcaChitarra);
				log.info("canzone: {}", canzone);
			}
			
			log.info("Form ricevuto: {}", data.getFormName());
			
			
			try {
		        
				return new ResponseEntity<FormSubmitResponse>(response, HttpStatus.OK);

			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormSubmitResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<FormSubmitResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<FormFieldValidationResponse> validate(
			@ApiParam(value = "Json data", required = true) @Valid @RequestBody FormFieldValidationRequest data,
			@ApiParam(value = "Algho-Token") @RequestHeader(value = "Algho-Token", required = false) String alghoToken) {
		
		String accept = "application/json"; // request.getHeader("Accept");
		
		Set<String> marcheChitarre = Stream.of("fender", "gibson", "ibanez", "martin")
				.collect(Collectors.toCollection(HashSet::new));
		
		FormFieldValidationResponse response = new FormFieldValidationResponse();
		
		if (alghoToken == null || !alghoToken.contains(this.token)) {
			FormAnswer answer = new FormAnswer();
	        answer.setAnswerText("Accesso negato. Token non valido.");
			response.addAnswersItem(answer);		
			try {
				return new ResponseEntity<FormFieldValidationResponse>(response, HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormFieldValidationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}
		
		
		if (accept != null && accept.contains("application/json")) {
			
			if (data.getFieldName().equals("genere musicale")) {
				String genere = data.getText().toLowerCase();
				
				if (genere.equalsIgnoreCase("rock")) {
					response.setNextFormName("max - band preferita");
				}
				
				response.setIsValid(true);
				
			} else if (data.getFieldName().equals("marca chitarra")) {
				
				FormAnswer answer = new FormAnswer();
				String marca = data.getText().toLowerCase();
				String urlStratImg = 
						"https://upload.wikimedia.org/wikipedia/commons/b/b3/Fender_American_Standard_Stratocaster_body_%2B_Ibanez_TSA30_%28by_Christian_Mesiano%29.jpg";
				String urlLPImg = "https://upload.wikimedia.org/wikipedia/commons/0/05/Gibson_Les_Paul_02.jpg?uselang=it";
				
				if (marcheChitarre.contains(marca)) {
					response.setIsValid(true);
					String urlImg = "";
					
					switch (marca) {
					case "fender":
						urlImg = urlStratImg;
						break;
					case "gibson":
						urlImg = urlLPImg;
						break;
					default:
						break;
					}
					
					answer.setAnswerText("Ottima scelta! Questa chitarra ha fatto la storia del rock!"); 
					answer.setMediaType("img");
					answer.setMedia(urlImg);
					response.addAnswersItem(answer);
				} else {
					response.setIsValid(false);
			        answer.setAnswerText("Attenzione, la marca che hai inserito non e' valida!.");
					response.addAnswersItem(answer);
					List<FormAdmissibileValue> listaMarche = new ArrayList<>();
					
					for (String m : marcheChitarre) {
						FormAdmissibileValue value = new FormAdmissibileValue();
						value.setName(m);
						listaMarche.add(value);
					}
					
					response.setAdmissibleValues(listaMarche);
					response.setAutofill(true);
				}
			}
			
			
			try {
				
				return new ResponseEntity<FormFieldValidationResponse>(response, HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormFieldValidationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<FormFieldValidationResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<FormFieldValuesResponse> values(
			@ApiParam(value = "Json data", required = true) @Valid @RequestBody FormFieldValuesRequest data,
			@ApiParam(value = "JWT Algho-Token") @RequestHeader(value = "Algho-Token", required = false) String alghoToken) {

		String accept = "application/json"; // request.getHeader("Accept");
		
		FormFieldValuesResponse response = new FormFieldValuesResponse();
		
		if (alghoToken == null || !alghoToken.contains(this.token)) {
			FormAnswer answer = new FormAnswer();
	        answer.setAnswerText("Accesso negato. Token non valido.");
			response.addAnswersItem(answer);	
			response.setCommand("exit");
			try {
				return new ResponseEntity<FormFieldValuesResponse>(response, HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormFieldValuesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}

		if (accept != null && accept.contains("application/json")) {
			try {

				if (data.getFieldName().equals("data")) {
					List<FormAdmissibileValue> values = new ArrayList<FormAdmissibileValue>();
					values.add(new FormAdmissibileValue().name("2021-08-06"));
					values.add(new FormAdmissibileValue().name("2021-08-15"));
					values.add(new FormAdmissibileValue().name("2021-08-30"));
					response.setValues(values);
				}

				return new ResponseEntity<FormFieldValuesResponse>(response, HttpStatus.OK);

			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<FormFieldValuesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<FormFieldValuesResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

}
