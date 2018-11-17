package com.xgh.eventhandlers.operational.notifier.email.templates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CancelledOperation implements EmailTemplate {

	private String operationType;
	private String ownerName;
	private String animalName;
	private LocalDateTime dateTime;

	public CancelledOperation(String operationType, String ownerName, String animalName, LocalDateTime dateTime) {
		this.operationType = operationType;
		this.ownerName = ownerName;
		this.animalName = animalName;
		this.dateTime = dateTime;
	}

	public String getSubject() {
		return String.format("Espaço Animal - Cancelamento de %s", operationType);
	}

	public String getBody() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");

		String date = dateTime.format(dateFormatter).toString();
		String hour = dateTime.format(hourFormatter).toString();

		return String.join("<meta charset=\"UTF-8\">",
				"<table width=\"50%\" align=\"center\"><tr><td><center><img src=\"https://s3-sa-east-1.amazonaws.com/carvajal-r2-dms/dms/46/98/12/1129846.png\" width=\"250\" height=\"130\" style=\"display: block;\"/></center>",
				"<h2>Olá ", ownerName,
				"!</h2><p style=\"font-size:110%;\">Estamos enviando este e-mail para lembrar você sobre o <b>Cancelamento</b> da ",
				operationType, " do(a) <b>", animalName, "</b>, agendada para <b>", date, "</b> às <b>", hour,
				"</b>, na Clínica Veterinária Espaço Animal.<br>",
				"<h3>Cuidar do seu companheiro de 4 patas é o nosso compromisso!</h3></p></td></tr></table>");

	}

}
