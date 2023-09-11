package com.example.konsaltingtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController // REST-контроллер, который обрабатывает HTTP-запросы и возвращает JSON-ответы
@RequestMapping("/api/frequency") // Базовый URL для всех запросов, обрабатываемых этим контроллером
public class FrequencyController {

    private final FrequencyService frequencyService; // Сервис для вычисления частоты символов

    @Autowired
    public FrequencyController(FrequencyService frequencyService) {
        this.frequencyService = frequencyService; // Внедрение зависимости на сервис FrequencyService через конструктор
    }

    @GetMapping("/calculate") // Обработка GET-запросов по пути /api/frequency/calculate
    public ResponseEntity<Map<Character, Integer>> calculateFrequency(@RequestParam String input) {
        // Метод, который будет вызываться при запросе /api/frequency/calculate
        Map<Character, Integer> frequencyMap = frequencyService.calculateFrequency(input); // Вычисление частоты символов с использованием сервиса

        return ResponseEntity.ok(frequencyMap); // Возвращение результата в виде HTTP-ответа с JSON-телом
    }
}

