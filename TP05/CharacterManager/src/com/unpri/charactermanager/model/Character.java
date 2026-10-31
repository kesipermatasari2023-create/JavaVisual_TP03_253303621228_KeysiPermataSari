package com.unpri.charactermanager.model;

public class Character {

    // Variable static untuk melacak total karakter yang pernah di-instansiasi
    private static int totalCharacterCreated = 0;

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
        totalCharacterCreated++;
    }

    // Copy constructor: membuat objek baru yang berdiri sendiri (independen) dari
    // objek lain
    public Character(Character other) {
        if (other == null) {
            throw new IllegalArgumentException("Karakter sumber tidak boleh null");
        }
        this.name = other.name;
        this.type = other.type;
        this.level = other.level;
        this.hitPoints = other.hitPoints;
        this.attack = other.attack;
        this.mana = other.mana;
        totalCharacterCreated++;
    }

    // Static Getter untuk totalCharacterCreated
    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    // Method untuk menghasilkan salinan independen
    public Character copy() {
        return new Character(this);
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

    // Implementasi equals() berdasarkan perbandingan nama karakter
    // (case-insensitive)
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Character character = (Character) o;
        return name != null && name.equalsIgnoreCase(character.name);
    }

    // Implementasi hashCode() konsisten dengan equals()
    @Override
    public int hashCode() {
        return name != null ? name.toLowerCase().hashCode() : 0;
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
