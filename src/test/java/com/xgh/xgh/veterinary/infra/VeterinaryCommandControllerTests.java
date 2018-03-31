package com.xgh.xgh.veterinary.infra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.xgh.infra.PostgresEventStore;
import com.xgh.valueobjects.Crmv;
import com.xgh.valueobjects.Mail;
import com.xgh.valueobjects.Name;
import com.xgh.valueobjects.Phone;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.VeterinaryId;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VeterinaryCommandControllerTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostgresEventStore<Veterinary, VeterinaryId> eventStore;

	@Before
	private void before() {

	}

	@Test
	public void register() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Veterinary veterinary = new Veterinary();
		veterinary.register(new VeterinaryId(), new Name("Ricardo Requena"), new Phone("044998015821"),
				new Crmv("9375"), new Mail("espacoanimal.vet@hotmail.com"),
				new Date(new SimpleDateFormat("yyyy-MM-dd").parse("1986-10-03").getTime()), true);
		
//		ResponseEntity<Veterinary>

	}
}
