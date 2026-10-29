package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // Bagian A: Membuat dan Menampilkan Karakter
        // ==========================================
        Character c1 = new Character("Arka", CharacterType.WARRIOR);
        Character c2 = new Character("Luna", CharacterType.MAGE, 5);
        Character c3 = new Character("Raven", CharacterType.ARCHER, 3);

        // Mengatur mana karakter
        c1.setMana(50);
        c2.setMana(150);
        c3.setMana(100);

        // Menampilkan 3 karakter
        System.out.println("=== DATA KARAKTER ===");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        // Demo level up
        System.out.println("\n=== DEMO LEVEL UP ===");
        c1.setLevel(c1.getLevel() + 1);
        System.out.println(c1);

        // ==========================================
        // Bagian B: Ekstensi Fitur Mandiri
        // ==========================================
        System.out.println("\n=== KARAKTER BARU ===");
        Character c4 = new Character("Nero", CharacterType.MAGE, 4);
        c4.setMana(200);
        System.out.println(c4);

        // Memanggil method isAlive()
        System.out.println("Apakah Nero masih hidup? " + c4.isAlive());

        // Menguji validasi nilai mana negatif
        System.out.println("\n=== UJI VALIDASI MANA ===");
        try {
            c4.setMana(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi berhasil: " + e.getMessage());
        }

        // Menampilkan data akhir
        System.out.println("\n=== DATA AKHIR NERO ===");
        System.out.println(c4);
    }
}

