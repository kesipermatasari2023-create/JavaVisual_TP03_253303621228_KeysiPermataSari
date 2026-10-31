package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private ArrayList<Character> characters;

    public CharacterManager() {
        characters = new ArrayList<>();
    }

    // Menambahkan karakter
    public void addCharacter(Character character) {
        characters.add(character);
    }

    // Mengambil seluruh data karakter
    public ArrayList<Character> getAllCharacters() {
        return characters;
    }

    // Mengambil jumlah karakter
    public int getTotalCharacters() {
        return characters.size();
    }

    // Menghitung rata-rata level
    public double getAverageLevel() {

        if (characters.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character character : characters) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characters.size();
    }
}