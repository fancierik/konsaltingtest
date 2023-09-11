package com.example.konsaltingtest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FrequencyController.class)
class KonsaltingtestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FrequencyService frequencyService;

	@Test
	void contextLoads() {
		// Пустой метод, используется для проверки контекста приложения
	}

	@Test
	void testCalculateFrequency() throws Exception {
		// Подготовка ожидаемых результатов для тестирования (ввод "aaaaabcccc")
		Map<Character, Integer> expectedFrequency = new HashMap<>();
		expectedFrequency.put('a', 5);
		expectedFrequency.put('c', 4);
		expectedFrequency.put('b', 1);

		// Имитация поведения сервиса: когда метод calculateFrequency вызывается с вводом "aaaaabcccc",
		// он вернет ожидаемый результат (словарь с частотами символов)
		Mockito.when(frequencyService.calculateFrequency("aaaaabcccc")).thenReturn(expectedFrequency);

		// Выполнение HTTP GET-запроса к контроллеру с вводом "aaaaabcccc" и проверка результата
		mockMvc.perform(MockMvcRequestBuilders.get("/api/frequency/calculate")
						.param("input", "aaaaabcccc")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Ожидаем успешный HTTP-статус
				.andExpect(MockMvcResultMatchers.jsonPath("$['a']").value(is(5))) // Ожидаем результат в JSON формате
				.andExpect(MockMvcResultMatchers.jsonPath("$['c']").value(is(4))) // Ожидаем результат в JSON формате
				.andExpect(MockMvcResultMatchers.jsonPath("$['b']").value(is(1))) // Ожидаем результат в JSON формате
				.andDo(print()); // Печать результатов для отладки
	}

	@Test
	void testCalculateFrequencyEmptyInput() throws Exception {
		// Подготовка ожидаемых результатов для тестирования (пустой ввод)
		Map<Character, Integer> expectedFrequency = new HashMap<>();

		// Имитация поведения сервиса: когда метод calculateFrequency вызывается с пустой строкой в качестве аргумента,
		// он вернет ожидаемый результат (пустой словарь)
		Mockito.when(frequencyService.calculateFrequency("")).thenReturn(expectedFrequency);

		// Выполнение HTTP GET-запроса к контроллеру с пустым вводом и проверка результата
		mockMvc.perform(MockMvcRequestBuilders.get("/api/frequency/calculate")
						.param("input", "")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Ожидаем успешный HTTP-статус
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty()) // Ожидаем пустой JSON-результат
				.andDo(print()); // Печать результатов для отладки
	}

	@Test
	void testCalculateFrequencySingleCharacterInput() throws Exception {
		// Подготовка ожидаемых результатов для тестирования (один символ 'a')
		Map<Character, Integer> expectedFrequency = new HashMap<>();
		expectedFrequency.put('a', 1);

		// Имитация поведения сервиса: когда метод calculateFrequency вызывается с вводом "a",
		// он вернет ожидаемый результат (словарь с одним элементом)
		Mockito.when(frequencyService.calculateFrequency("a")).thenReturn(expectedFrequency);

		// Выполнение HTTP GET-запроса к контроллеру с вводом "a" и проверка результата
		mockMvc.perform(MockMvcRequestBuilders.get("/api/frequency/calculate")
						.param("input", "a")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Ожидаем успешный HTTP-статус
				.andExpect(MockMvcResultMatchers.jsonPath("$['a']").value(is(1))) // Ожидаем результат в JSON формате
				.andDo(print()); // Печать результатов для отладки
	}

	@Test
	void testCalculateFrequencySpecialCharacters() throws Exception {
		// Подготовка ожидаемых результатов для тестирования (специальные символы)
		Map<Character, Integer> expectedFrequency = new HashMap<>();
		expectedFrequency.put('!', 3);
		expectedFrequency.put('1', 2);
		expectedFrequency.put('@', 1);

		// Имитация поведения сервиса: когда метод calculateFrequency вызывается с вводом "!1!11@",
		// он вернет ожидаемый результат (словарь с соответствующими частотами символов)
		Mockito.when(frequencyService.calculateFrequency("!1!11@")).thenReturn(expectedFrequency);

		// Выполнение HTTP GET-запроса к контроллеру с вводом "!1!11@" и проверка результата
		mockMvc.perform(MockMvcRequestBuilders.get("/api/frequency/calculate")
						.param("input", "!1!11@")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Ожидаем успешный HTTP-статус
				.andExpect(MockMvcResultMatchers.jsonPath("$['!']").value(is(3))) // Ожидаем результат в JSON формате
				.andExpect(MockMvcResultMatchers.jsonPath("$['1']").value(is(2))) // Ожидаем результат в JSON формате
				.andExpect(MockMvcResultMatchers.jsonPath("$['@']").value(is(1))) // Ожидаем результат в JSON формате
				.andDo(print()); // Печать результатов для отладки
	}
}


