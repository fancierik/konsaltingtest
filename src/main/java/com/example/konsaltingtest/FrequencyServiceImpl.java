package com.example.konsaltingtest;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class FrequencyServiceImpl implements FrequencyService {

    // Реализация метода calculateFrequency интерфейса FrequencyService
    @Override
    public Map<Character, Integer> calculateFrequency(String input) {
        // Создаем пустой Map, где ключи - символы, а значения - количество их вхождений
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Проходим по каждому символу во входной строке
        for (char c : input.toCharArray()) {
            // Если символ уже есть в Map, увеличиваем его счетчик на 1, иначе добавляем его в Map с счетчиком 1
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Возвращаем Map, содержащий частоту символов во входной строке
        return frequencyMap;
    }
}


