package com.aldeamo.BartenderTest;

import com.aldeamo.BartenderTest.entity.BartenderEntity;
import com.aldeamo.BartenderTest.exceptions.IdNotFound;
import com.aldeamo.BartenderTest.response.BartenderResponse;
import com.aldeamo.BartenderTest.service.BartenderService;
import com.aldeamo.BartenderTest.util.Constants;
import com.aldeamo.BartenderTest.ws.IBartenderWS;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.aldeamo.BartenderTest.ws.BartenderWS.primeNumbers;
import static com.aldeamo.BartenderTest.ws.BartenderWS.solve;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BartenderTestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	IBartenderWS bartenderWS;

	@Autowired BartenderService entityService;

	@Test
	public void testBartenderController() throws Exception {
		mockMvc.perform(get("/api/bartender/solve/3/1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testPrimeNumbersGenerator() {
		List<Integer> generated = primeNumbers(10);
		List<Integer> first10PrimeNumbers = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
		assertIterableEquals(generated, first10PrimeNumbers);
	}

	@Test
	public void testSolvingProblem() {
		List<Integer> input_array = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6,7));
		String result = solve(3, input_array);
		assertEquals("6,4,2,3,5,7", result);
	}

	@Test
	public void testBartenderService() throws IdNotFound {
		BartenderResponse response = null;
		response = bartenderWS.execute(3, 1);
		assertEquals(response.getStatus(), Constants.SUCCESS_TYPE);
	}

	@Test
	public void testBartenderEntity() {
		Optional<BartenderEntity> bartender = entityService.findById(1L);
		assertEquals(bartender.isPresent(), true);
	}


}
