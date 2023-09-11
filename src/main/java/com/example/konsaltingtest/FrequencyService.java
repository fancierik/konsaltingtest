package com.example.konsaltingtest;
import java.util.Map;

// Интерфейс FrequencyService определяет контракт для сервиса, вычисляющего частоту символов в строке
public interface FrequencyService {

    // Метод calculateFrequency принимает на вход строку input и возвращает Map, где ключи - это символы из input,
    // а значения - количество их вхождений в строку
    Map<Character, Integer> calculateFrequency(String input);
}
