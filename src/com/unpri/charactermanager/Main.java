package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("          CHARACTER MANAGER SPRINT #2 - ANALYTICS & OBJECT REFERENCE            ");
        System.out.println("                    Praktikum Pemrograman Berorientasi Objek                    ");
        System.out.println("================================================================================\n");

        CharacterManager manager = new CharacterManager();

        // =========================================================================
        // BAGIAN A: UJI IMPLEMENTASI CORE & VALIDASI INTEGRITAS DATA
        // =========================================================================
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("1. BAGIAN A - IMPLEMENTASI CORE & OPERASI CRUD");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("\n[A.1] Menambahkan Karakter Awal ke CharacterManager:");
        Character c1 = new Character("Arka", CharacterType.WARRIOR, 5);
        Character c2 = new Character("Luna", CharacterType.MAGE, 8);
        Character c3 = new Character("Raven", CharacterType.ARCHER, 3);
        Character c4 = new Character("Nero", CharacterType.ASSASSIN, 6);

        c1.setMana(60);
        c2.setMana(180);
        c3.setMana(100);
        c4.setMana(140);

        manager.addCharacter(c1);
        manager.addCharacter(c2);
        manager.addCharacter(c3);
        manager.addCharacter(c4);

        System.out.println("\nTotal Karakter Terdaftar: " + manager.getTotalCharacters());

        System.out.println("\n[A.2] Uji Penolakan Nama Duplikat (Memanfaatkan equals):");
        Character duplicateLuna = new Character("Luna", CharacterType.ARCHER, 4);
        boolean duplicateAdded = manager.addCharacter(duplicateLuna);
        System.out.println("Status penambahan 'Luna' kedua: " + (duplicateAdded ? "DITERIMA" : "DITOLAK (Valid)"));

        Character duplicateArkaLower = new Character("arka", CharacterType.MAGE, 10);
        boolean duplicateLowerAdded = manager.addCharacter(duplicateArkaLower);
        System.out.println("Status penambahan 'arka' (case-insensitive): " + (duplicateLowerAdded ? "DITERIMA" : "DITOLAK (Valid)"));

        System.out.println("\n[A.3] Uji Pencarian (findByName):");
        Character foundLuna = manager.findByName("Luna");
        System.out.println("Mencari 'Luna': " + (foundLuna != null ? foundLuna : "Tidak Ditemukan"));

        Character foundGhost = manager.findByName("Ghost");
        System.out.println("Mencari 'Ghost': " + (foundGhost != null ? foundGhost : "Tidak Ditemukan (Valid)"));

        System.out.println("\n[A.4] Uji Penghapusan (removeCharacter):");
        System.out.println("Total sebelum hapus 'Raven': " + manager.getTotalCharacters());
        manager.removeCharacter("Raven");
        System.out.println("Total setelah hapus 'Raven': " + manager.getTotalCharacters());

        System.out.println("\n[A.5] Eksperimen Halaman 7: Uji Encapsulation pada getAllCharacters():");
        ArrayList<Character> externalList = manager.getAllCharacters();
        System.out.println("Ukuran list salinan yang diterima caller : " + externalList.size());
        System.out.println("Ukuran list internal di CharacterManager: " + manager.getTotalCharacters());

        System.out.println("-> Melakukan manipulasi eksternal: externalList.clear()...");
        externalList.clear();

        System.out.println("Ukuran list eksternal setelah .clear()   : " + externalList.size());
        System.out.println("Ukuran list internal di CharacterManager: " + manager.getTotalCharacters());
        if (manager.getTotalCharacters() == 3) {
            System.out.println("[TERBUKTI] List internal tetap utuh (3 karakter)! getAllCharacters() mengembalikan defensive copy yang aman.");
        } else {
            System.out.println("[PERINGATAN] List internal bocor dan terpengaruh manipulasi eksternal!");
        }

        // =========================================================================
        // BAGIAN B: MINI-CHALLENGE (AP6 HALAMAN 8)
        // =========================================================================
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("2. BAGIAN B - MINI-CHALLENGE: STATISTIK & FILTERING");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("\n[B.1] Uji getAverageLevel() pada List Kosong (Pencegahan Division by Zero):");
        CharacterManager emptyManager = new CharacterManager();
        System.out.println("Rata-rata level pada manager kosong: " + emptyManager.getAverageLevel());

        System.out.println("\n[B.2] Uji getAverageLevel() dengan Data Aktif:");
        System.out.println("Daftar karakter saat ini: ");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(" - " + c.getName() + " (Level: " + c.getLevel() + ")");
        }
        System.out.printf("Nilai Rata-rata Level: %.2f\n", manager.getAverageLevel());

        System.out.println("\n[B.3] Uji findByType(CharacterType type):");
        // Tambahkan karakter Mage baru untuk memperkaya demo filtering
        Character cMage2 = new Character("Ignis", CharacterType.MAGE, 10);
        cMage2.setMana(250);
        manager.addCharacter(cMage2);

        System.out.println("\nFilter Karakter bertipe MAGE:");
        ArrayList<Character> mages = manager.findByType(CharacterType.MAGE);
        for (Character m : mages) {
            System.out.println(" * " + m);
        }

        System.out.println("\nFilter Karakter bertipe ARCHER (sudah dihapus sebelumnya):");
        ArrayList<Character> archers = manager.findByType(CharacterType.ARCHER);
        System.out.println("Jumlah Archer ditemukan: " + archers.size() + " karakter.");

        // =========================================================================
        // BAGIAN C: FITUR ANALITIK RANCANGAN SENDIRI
        // =========================================================================
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("3. BAGIAN C - FITUR ANALITIK RANCANGAN SENDIRI");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("\n[C.1] Uji Fitur Analitik 1: renameCharacter(oldName, newName)");
        System.out.println("Skenario 1 - Mengubah nama Nero menjadi Shadow (Valid):");
        manager.renameCharacter("Nero", "Shadow");

        System.out.println("\nSkenario 2 - Mengubah nama Luna menjadi Arka (Bentrok Nama Duplikat):");
        manager.renameCharacter("Luna", "Arka");

        System.out.println("\nSkenario 3 - Mengubah nama karakter yang tidak terdaftar:");
        manager.renameCharacter("Valkyrie", "Freya");

        System.out.println("\n[C.2] Uji Fitur Analitik 2: findStrongest() (Attack Tertinggi & Safe Copy)");
        Character strongest = manager.findStrongest();
        System.out.println("Karakter Terkuat (Highest Attack): " + strongest);

        System.out.println("\n[C.3] Uji Fitur Analitik 3: countByType(CharacterType)");
        System.out.println("Total WARRIOR  : " + manager.countByType(CharacterType.WARRIOR));
        System.out.println("Total MAGE     : " + manager.countByType(CharacterType.MAGE));
        System.out.println("Total ASSASSIN : " + manager.countByType(CharacterType.ASSASSIN));
        System.out.println("Total ARCHER   : " + manager.countByType(CharacterType.ARCHER));

        // =========================================================================
        // BAGIAN D: PEMBUKTIAN KONSEPTUAL OBJECT REFERENCE & STATIC
        // =========================================================================
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("4. BAGIAN D - DEMONSTRASI & PEMBUKTIAN KONSEPTUAL REFERENCE");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("\n[D.1] Simulasi Kasus Pertanyaan 1 (Aliasing Reference):");
        Character a = new Character("KsatriaA", CharacterType.WARRIOR, 1);
        Character b = a; // Aliasing: b menunjuk ke alamat memori yang sama dengan a
        b.setLevel(8);   // Mengubah state objek melalui referensi b

        System.out.println("Kode dieksekusi: Character a = new Character(...); Character b = a; b.setLevel(8);");
        System.out.println("Hasil a.getLevel(): " + a.getLevel());
        System.out.println("Hasil (a == b)   : " + (a == b));
        System.out.println("Penjelasan Singkat: Nilai a.getLevel() bernilai 8 dan (a == b) bernilai true karena variabel");
        System.out.println("a dan b adalah pointer/reference pada Stack yang menunjuk ke objek instance yang sama di Heap.");

        System.out.println("\n[D.2] Solusi Membuat Objek Benar-Benar Independen (Copy Constructor):");
        Character independentCopy = new Character(a); // Memakai Copy Constructor
        independentCopy.setLevel(25);
        System.out.println("Setelah independentCopy.setLevel(25):");
        System.out.println("Level objek asli 'a'       : " + a.getLevel() + " (tetap tidak berubah!)");
        System.out.println("Level objek independentCopy: " + independentCopy.getLevel());
        System.out.println("Hasil (a == independentCopy): " + (a == independentCopy) + " (Berada pada alamat memori terpisah)");

        System.out.println("\n[D.3] Pembuktian Static Variable totalCharacterCreated:");
        System.out.println("Total objek Character yang telah di-instansiasi sepanjang program: "
                + Character.getTotalCharacterCreated());

        System.out.println("\n================================================================================");
        System.out.println("                       SELURUH PENGUJIAN SELESAI & BERHASIL                     ");
        System.out.println("================================================================================");
    }
}
