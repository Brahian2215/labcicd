package com.udea.labCiCd;

import com.fasterxml.jackson.databind.JsonNode;
import com.udea.labCiCd.controller.DataController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LabCiCdApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	void healtCheck(){
		assertEquals("Proyecto Activo", dataController.healtCheck());
	}

	@Test
	void version(){
		assertEquals("La versión actual es 0.0.1", dataController.version());
	}

	@Test
	void nationLength(){
		Integer nationsLength = dataController.getRandomNation().size();
		assertEquals(10, nationsLength);
	}

	@Test
	void currenciesLength(){
		Integer currenciesLength = dataController.getRandomCurriencies().size();
		assertEquals(20, currenciesLength);
	}

	@Test
	public void testRandomCurrenciesCodeFormat(){
		DataController controller = new DataController();
		JsonNode response = controller.getRandomCurriencies();

		for(int i=0 ; i < response.size(); i++){
			JsonNode currency = response.get(i);
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}"));
		}
	}


	@Test
	public void testRandomNationsPerformance(){
		DataController controller = new DataController();
		long startTime = System.currentTimeMillis();
		controller.getRandomNation();
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		assertTrue(executionTime < 2000);
	}

	@Test
	void aviationLength(){
		Integer aviationLength = dataController.getRandomAviation().size();
		assertEquals(20, aviationLength);
	}


}
