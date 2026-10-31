package com.unpri.charactermanager.model;

public class Character {

    private String name;
    private int level;
    private CharacterType type;

    public Character(String name, int level, CharacterType type) {
        this.name = name;
        this.level = level;
        this.type = type;
    }

    // Getter nama
    public String getName() {
        return name;
    }

    // Setter nama
    public void setName(String name) {
        this.name = name;
    }

    // Getter level
    public int getLevel() {
        return level;
    }

    // Setter level
    public void setLevel(int level) {
        this.level = level;
    }

    // Getter tipe karakter
    public CharacterType getType() {
        return type;
    }

    // Setter tipe karakter
    public void setType(CharacterType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " - Level " + level + " (" + type + ")";
    }
}