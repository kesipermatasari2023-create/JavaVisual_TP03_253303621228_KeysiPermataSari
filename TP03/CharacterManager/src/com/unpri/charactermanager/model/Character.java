package com.unpri.charactermanager.model;

public class Character {

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    // Constructor 1: Level default 1, mana awal 0
    public Character(String name, CharacterType type) {
        this(name, type, 1);
    }

    // Constructor 2: Level kustom
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        setLevel(level);
        this.mana = 0;
    }

    // Getter dan Setter untuk Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan Setter untuk Type
    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    // Getter untuk Level
    public int getLevel() {
        return level;
    }

    // Setter untuk Level: menghitung ulang HP dan Attack
    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level tidak boleh kurang dari 1");
        }
        this.level = level;
        this.hitPoints = 100 + (level * 20);
        this.attack = 10 + (level * 5);
    }

    // Getter untuk HitPoints
    public int getHitPoints() {
        return hitPoints;
    }

    // Getter untuk Attack
    public int getAttack() {
        return attack;
    }

    // Getter untuk Mana
    public int getMana() {
        return mana;
    }

    // Setter untuk Mana dengan validasi nilai tidak boleh negatif
    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    // Method untuk mengecek apakah karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // Representasi string dari karakter
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hitPoints=" + hitPoints +
                ", attack=" + attack +
                ", mana=" + mana +
                '}';
    }
}

