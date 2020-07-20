package com.example.calculatror.model;

import com.example.calculatror.exceptions.InvalidInputException;

import java.util.ArrayList;

public class Calculator {

    private static ArrayList<Character> extractSymbols(String str) {
        if (str == null || str == "")
            return new ArrayList<>();
        ArrayList<Character> symbols = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && c == '-')
                continue;
            if (c == '+' || c == '-' || c == '×' || c == '÷') {
                symbols.add(c);
            }
        }
        return symbols;
    }

    private static ArrayList<Double> extractNumbers(String str) {
        if (str == null || str == "")
            return new ArrayList<>();
        ArrayList<Double> numbers = new ArrayList<>();
        String number = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && c == '-') {
                number += '-';
            } else {
                if (c == '+' || c == '-' || c == '×' || c == '÷') {
                    numbers.add(Double.parseDouble(number));
                    number = "";
                } else {
                    number += c;
                }
            }
            if (i == str.length() - 1) {
                numbers.add(Double.parseDouble(number));
            }
        }
        return numbers;
    }

    public static double calculate(String str) throws InvalidInputException {
        if (!isInputValid(str))
            throw new InvalidInputException();
        ArrayList<Character> symbolsList = extractSymbols(str);
        ArrayList<Double> numbersList = extractNumbers(str);
        for (int i = 0; symbolsList.contains('÷') || symbolsList.contains('×'); i++) {
            if (symbolsList.get(i) == '×') {
                double r = numbersList.get(i) * numbersList.get(i + 1);
                numbersList.set(i, r);
                numbersList.remove(i + 1);
                symbolsList.remove(i);
                i--;
            } else if (symbolsList.get(i) == '÷') {
                double r = numbersList.get(i) / numbersList.get(i + 1);
                numbersList.set(i, r);
                numbersList.remove(i + 1);
                symbolsList.remove(i);
                i--;
            }
        }
        for (int i = 0; symbolsList.contains('+') || symbolsList.contains('-'); i++) {
            if (symbolsList.get(i) == '+') {
                double r = numbersList.get(i) + numbersList.get(i + 1);
                numbersList.set(i, r);
                numbersList.remove(i + 1);
                symbolsList.remove(i);
                i--;
            } else if (symbolsList.get(i) == '-') {
                double r = numbersList.get(i) - numbersList.get(i + 1);
                numbersList.set(i, r);
                numbersList.remove(i + 1);
                symbolsList.remove(i);
                i--;
            }
        }
        return numbersList.get(0);
    }

    private static boolean isInputValid(String str) {
        if (str == null || str == "")
            return false;
        char lastChar = str.charAt(str.length() - 1);
        if (lastChar == '+'
                || lastChar == '-'
                || lastChar == '×'
                || lastChar == '÷'
                || lastChar == '.'
                || extractNumbers(str).size() == 0
                || extractSymbols(str).size() == 0
        )
            return false;
        return true;
    }


}
