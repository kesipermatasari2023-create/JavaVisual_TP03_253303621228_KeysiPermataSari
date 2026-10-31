package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

/**
 * Class CharacterManager mengelola kumpulan data karakter permainan.
 * Menerapkan prinsip Object-Oriented Programming, Encapsulation, validasi integritas data,
 * serta pencegahan aliasing reference melalui defensive copying.
 */
public class CharacterManager {

    // Internal list penyimpan objek karakter (private untuk menjaga enkapsulasi)
    private ArrayList<Character> characters;

    /**
     * Konstruktor default CharacterManager.
     * Menginisialisasi list kosong untuk menampung karakter.
     */
    public CharacterManager() {
        this.characters = new ArrayList<>();
    }

    // =========================================================================
    // BAGIAN A - IMPLEMENTASI CORE
    // =========================================================================

    /**
     * Menambahkan karakter baru ke dalam manager.
     * Memvalidasi bahwa nama karakter belum pernah terdaftar sebelumnya (mencegah duplikasi).
     *
     * @param character Objek Character yang akan ditambahkan.
     * @return true jika penambahan berhasil; false jika karakter null atau nama duplikat.
     */
    public boolean addCharacter(Character character) {
        if (character == null) {
            System.out.println("[GAGAL] Karakter tidak boleh bernilai null.");
            return false;
        }

        // Cek duplikasi menggunakan equals() atau pencarian berdasarkan nama
        if (characters.contains(character) || findByName(character.getName()) != null) {
            System.out.println("[DITOLAK] Karakter dengan nama '" + character.getName() + "' sudah ada! Penambahan nama duplikat ditolak.");
            return false;
        }

        characters.add(character);
        System.out.println("[SUKSES] Karakter '" + character.getName() + "' (Tipe: " + character.getType() + ", Level: " + character.getLevel() + ") berhasil ditambahkan.");
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama (pencarian case-insensitive).
     *
     * @param name Nama karakter yang dicari.
     * @return Objek Character jika ditemukan; null jika tidak ditemukan.
     */
    public Character findByName(String name) {
        if (name == null) {
            return null;
        }
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name.trim())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Menghapus karakter dari manager berdasarkan nama.
     *
     * @param name Nama karakter yang akan dihapus.
     * @return true jika karakter berhasil ditemukan dan dihapus; false jika tidak ditemukan.
     */
    public boolean removeCharacter(String name) {
        Character target = findByName(name);
        if (target != null) {
            characters.remove(target);
            System.out.println("[SUKSES] Karakter '" + name + "' berhasil dihapus dari sistem.");
            return true;
        }
        System.out.println("[GAGAL] Karakter '" + name + "' tidak ditemukan, tidak ada yang dihapus.");
        return false;
    }

    /**
     * Mengembalikan jumlah total karakter aktif yang tersimpan saat ini.
     *
     * @return Jumlah karakter aktif.
     */
    public int getTotalCharacters() {
        return characters.size();
    }

    /**
     * Mengembalikan seluruh karakter yang terdaftar.
     * PENTING: Mengembalikan salinan baru (defensive copy) dari ArrayList, bukan referensi asli.
     * Hal ini menjaga encapsulation agar manipulasi dari luar tidak merusak list internal.
     *
     * @return ArrayList baru yang berisi salinan referensi karakter.
     */
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(this.characters);
    }

    // =========================================================================
    // BAGIAN B - MINI-CHALLENGE
    // =========================================================================

    /**
     * Menghitung nilai rata-rata level dari seluruh karakter yang ada.
     * Mengembalikan 0.0 jika list kosong untuk mencegah ArithmeticException (pembagian nol).
     *
     * @return Rata-rata level (double), atau 0.0 jika belum ada karakter.
     */
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (Character c : characters) {
            sum += c.getLevel();
        }
        return (double) sum / characters.size();
    }

    /**
     * Mencari dan mengumpulkan semua karakter yang memiliki tipe tertentu.
     * Menghasilkan ArrayList baru sehingga tidak mengekspos list internal.
     *
     * @param type Tipe karakter yang ingin difilter (CharacterType).
     * @return ArrayList berisi semua karakter yang cocok dengan tipe yang dicari.
     */
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();
        if (type == null) {
            return result;
        }
        for (Character c : characters) {
            if (c.getType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    // =========================================================================
    // BAGIAN C - FITUR ANALITIK RANCANGAN SENDIRI
    // =========================================================================

    /**
     * [Fitur Analitik Mandiri 1]
     * Mengubah nama karakter yang sudah ada dengan validasi ketat bentrok nama (name collision).
     * Memastikan nama baru belum dipakai oleh karakter manapun di dalam list manager.
     *
     * @param oldName Nama lama karakter yang ingin diganti.
     * @param newName Nama baru yang diinginkan untuk karakter tersebut.
     * @return true jika nama berhasil diperbarui; false jika nama lama tidak ditemukan atau nama baru sudah terpakai.
     */
    public boolean renameCharacter(String oldName, String newName) {
        if (oldName == null || newName == null || newName.trim().isEmpty()) {
            System.out.println("[GAGAL GANTI NAMA] Nama lama dan nama baru tidak boleh kosong.");
            return false;
        }

        Character target = findByName(oldName);
        if (target == null) {
            System.out.println("[GAGAL GANTI NAMA] Karakter dengan nama '" + oldName + "' tidak ditemukan.");
            return false;
        }

        String sanitizedNewName = newName.trim();
        // Cek jika nama baru bentrok dengan karakter lain
        if (!oldName.equalsIgnoreCase(sanitizedNewName) && findByName(sanitizedNewName) != null) {
            System.out.println("[DITOLAK GANTI NAMA] Nama '" + sanitizedNewName + "' sudah digunakan oleh karakter lain! Pergantian nama dibatalkan.");
            return false;
        }

        target.setName(sanitizedNewName);
        System.out.println("[SUKSES GANTI NAMA] Nama karakter '" + oldName + "' berhasil diubah menjadi '" + sanitizedNewName + "'.");
        return true;
    }

    /**
     * [Fitur Analitik Mandiri 2]
     * Mencari karakter dengan stat attack tertinggi dalam sistem.
     * Menjaga encapsulation secara maksimal dengan mengembalikan salinan (copy) independen,
     * sehingga caller tidak dapat memodifikasi state internal karakter asli secara tidak sengaja.
     *
     * @return Objek Character salinan independen dengan attack tertinggi, atau null jika list kosong.
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }
        Character strongest = characters.get(0);
        for (Character c : characters) {
            if (c.getAttack() > strongest.getAttack()) {
                strongest = c;
            }
        }
        return strongest.copy();
    }

    /**
     * [Fitur Analitik Mandiri 3]
     * Menghitung total jumlah karakter yang memiliki tipe tertentu.
     *
     * @param type Tipe karakter yang ingin dihitung.
     * @return Jumlah total karakter dengan tipe tersebut.
     */
    public int countByType(CharacterType type) {
        if (type == null) {
            return 0;
        }
        int count = 0;
        for (Character c : characters) {
            if (c.getType() == type) {
                count++;
            }
        }
        return count;
    }
}

