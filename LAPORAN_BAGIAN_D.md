# ANALISIS OBJECT REFERENCE, STATIC, DAN ENCAPSULATION
## TUGAS PRAKTIKUM 5 (TP05) - AP6 SPRINT #2: CHARACTER MANAGER
### MATA KULIAH: PEMROGRAMAN VISUAL / JAVA (SIH-6406)

---

### **IDENTITAS MAHASISWA**
- **Nama Lengkap** : Keysi Permata Sari
- **NIM**          : 253303621228
- **Kelas**        : SIH-6406
- **Program Studi**: Sistem Informasi / Teknik Informatika
- **Tanggal**      : 31 Oktober 2026

---

## **1. Analisis Perilaku Object Reference & Aliasing pada Assignment Objek**

### **Pertanyaan:**
Untuk potongan kode berikut:
```java
Character a = new Character("KsatriaA", CharacterType.WARRIOR, 1);
Character b = a;
b.setLevel(8);
```
Jelaskan apa yang dicetak oleh pemanggilan `a.getLevel()` dan evaluasi ekspresi `a == b` beserta alasannya dengan menggunakan terminologi *reference*! Bagaimana cara membuat objek `b` benar-benar independen?

### **Penjelasan Analitis:**
Ketika kode di atas dieksekusi oleh Java Virtual Machine (JVM), nilai yang dicetak adalah:
- `a.getLevel()` akan mencetak nilai **`8`**.
- `(a == b)` akan mencetak nilai **`true`**.

Fenomena ini terjadi karena karakteristik fundamental dari variabel bertipe objek (non-primitif) di dalam Java. Di dalam arsitektur memori JVM, memori dibagi ke dalam dua area utama: **Call Stack** dan **Managed Heap**.
1. Ketika baris `Character a = new Character(...)` dieksekusi, operator `new` mengalokasikan blok memori baru di area **Heap** untuk menampung instansiasi objek `Character`. Variabel `a` yang berada di area **Stack** tidak secara langsung menyimpan data fisik dari objek tersebut, melainkan menyimpan **alamat memori (pointer/reference)** yang menunjuk ke lokasi objek di Heap.
2. Ketika baris `Character b = a;` dieksekusi, Java menerapkan mekanisme *pass-by-value of the reference*. Artinya, JVM tidak menduplikasi isi objek di Heap, melainkan hanya menyalin nilai alamat memori yang disimpan di variabel `a` ke variabel `b`. Akibatnya, baik `a` maupun `b` memegang nilai referensi yang persis sama dan menunjuk ke satu instans objek fisik yang sama di memori Heap. Kondisi di mana dua atau lebih variabel referensi menunjuk ke objek yang sama dikenal dengan istilah **Object Aliasing**.
3. Ketika pemanggilan method mutator `b.setLevel(8);` dilakukan, JVM mengikuti pointer referensi dari `b` menuju objek di Heap dan memodifikasi atribut `level` dari objek tersebut menjadi `8` (sekaligus menghitung ulang nilai `hitPoints` dan `attack`). Karena variabel `a` menunjuk ke objek yang sama persis, maka pemanggilan `a.getLevel()` membaca perubahan tersebut dan menghasilkan nilai `8`.
4. Evaluasi operator perbandingan relasional `(a == b)` pada tipe objek di Java bertindak sebagai pengecekan **Identity Equality** (kesamaan alamat memori/referensi), bukan kesamaan struktural isi data (Structural Equality). Karena referensi `a` dan `b` menunjuk ke alamat Heap yang sama, maka evaluasi bernilai mutlak `true`.

### **Solusi Agar Objek `b` Benar-Benar Independen:**
Untuk membuat objek `b` terisolasi dan sepenuhnya independen dari objek `a`, kita tidak boleh sekadar melakukan assignment referensi (`b = a`). Kita harus membuat instansiasi objek baru di Heap melalui teknik **Copy Constructor** atau pembuatan method kloning eksplisit seperti `copy()`:

```java
// Implementasi Copy Constructor pada Character.java:
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

// Penggunaan pada program utama:
Character a = new Character("KsatriaA", CharacterType.WARRIOR, 1);
Character b = new Character(a); // Objek baru dialokasikan pada alamat Heap terpisah
b.setLevel(8);

System.out.println(a.getLevel()); // Tetap bernilai 1 (objek asli terlindungi)
System.out.println(b.getLevel()); // Bernilai 8
System.out.println(a == b);       // Menghasilkan false (alamat memori berbeda)
```
Dengan mekanisme alokasi terpisah ini, modifikasi terhadap atribut objek `b` tidak akan pernah mempengaruhi atribut objek `a`.

---

## **2. Analogi Perbedaan Reference Value dan Primitive Value**

Untuk memahami kontras antara tipe data primitif (*primitive value*) dan tipe data referensi (*reference value*), mari kita gunakan analogi **"Kertas Catatan Resep Kue Masakan" vs. "Kunci Lemari Loker Sekolah"**:

1. **Primitive Value (Resep Masakan di Kertas Catatan):**
   Bayangkan tipe data primitif (`int`, `double`, `boolean`, `char`) seperti selembar kertas memo berisi teks resep kue. Jika Anda memiliki resep kue cokelat (misalkan variabel `x = 10`), lalu teman Anda meminta resep tersebut (`int y = x`), Anda menyalin tulisan resep itu ke selembar kertas baru dan memberikannya kepada teman Anda. Kini, ada dua lembar kertas terpisah. Jika teman Anda mencoret takaran gula di kertas miliknya menjadi 20 gram (`y = 20`), catatan di kertas resep milik Anda sama sekali tidak tercoret dan tetap bertuliskan 10 gram. Primitive value menyimpan nilai data aktual secara mandiri di dalam slot memori Stack.

2. **Reference Value (Kunci Lemari Loker):**
   Sebaliknya, tipe data referensi (seperti objek `Character` atau `ArrayList`) bekerja seperti **kunci duplikat dari sebuah loker fisik**. Objek yang sebenarnya diibaratkan seperti lemari loker bernomor di dalam gedung penyimpanan (Heap), yang menampung barang-barang berharga (atribut `name`, `level`, `hitPoints`, dll). Variabel `a` adalah sebuah gantungan kunci yang bertuliskan nomor loker tersebut. Ketika Anda menuliskan perintah `Character b = a;`, Anda tidak sedang membeli lemari loker baru; Anda hanya membuat duplikat kunci yang memiliki nomor loker yang sama persis dan menyerahkannya kepada `b`. 
   
   Sekarang, baik `a` maupun `b` sama-sama memegang kunci ke satu loker yang sama. Jika orang yang memegang kunci `b` membuka loker dan mengganti isi di dalamnya (misalnya mengubah level menjadi 8), maka ketika orang yang memegang kunci `a` membuka loker tersebut, ia akan mendapati bahwa isi barangnya telah berubah. Jika ingin `b` memiliki barang sendiri yang tidak saling mempengaruhi, `b` wajib menyewa lemari loker baru yang terpisah (`new Character(...)`), lalu menyalin isi barang dari loker `a` ke loker barunya tersebut.

---

## **3. Analisis Kata Kunci `static` pada `totalCharacterCreated`**

### **Alasan Mengapa `totalCharacterCreated` Harus Bersifat `static`:**
Di dalam pemrograman Java, kata kunci `static` menyatakan bahwa suatu anggota (variabel atau method) dimiliki oleh **Kelas (Class-Level Scope)**, bukan melekat pada instansiasi objek individual (**Instance-Level Scope**). 

Variabel `totalCharacterCreated` dirancang untuk menghitung akumulasi seluruh objek `Character` yang pernah dibuat selama siklus hidup aplikasi berjalan. Ketika dideklarasikan sebagai `private static int totalCharacterCreated = 0;`, JVM hanya mengalokasikan **satu ruang memori tunggal** di area *Metaspace / Class Area* untuk variabel tersebut. Ruang memori ini digunakan bersama (*shared state*) oleh semua objek `Character` tanpa memandang berapa banyak objek yang di-instansiasi. Setiap kali konstruktor `Character` dieksekusi (baik konstruktor baru maupun copy constructor), counter `totalCharacterCreated++` akan menambahkan nilai pada memori bersama tersebut.

### **Apa yang Terjadi Jika Tidak Bersifat `static`?**
Jika kata kunci `static` dihilangkan (menjadi `private int totalCharacterCreated = 0;`), variabel tersebut berubah menjadi *instance variable*. Akibat fatalnya adalah:
1. **Pemisahan Memori Antar Objek:** Setiap kali kita membuat objek baru dengan `new Character(...)`, JVM akan membuat salinan baru dari atribut `totalCharacterCreated` di dalam blok memori objek tersebut di Heap.
2. **Kegagalan Fungsi Counter:** Nilai default variabel pada setiap objek baru berawal dari `0`. Ketika konstruktor berjalan dan mengeksekusi `totalCharacterCreated++`, nilai atribut pada objek tersebut hanya akan berubah dari `0` menjadi `1`. 
3. Objek `c1` akan memiliki `totalCharacterCreated = 1`, objek `c2` juga bernilai `1`, dan objek `c100` pun tetap bernilai `1`. Variabel tersebut kehilangan total kemampuannya untuk melakukan agregasi atau pelacakan global, karena tidak ada sinkronisasi state antar-objek yang berbeda.

Oleh karena itu, penandaan `static` adalah syarat mutlak bagi state global yang bersifat informatif lintas objek.

---

## **4. Keterbatasan `equals()` Bawaan Class `Object` dan Solusinya**

### **Mengapa `equals()` Bawaan Class `Object` Tidak Cocok?**
Di dalam hierarki bahasa Java, seluruh kelas secara implisit merupakan turunan dari superclass `java.lang.Object`. Implementasi default dari method `equals()` pada class `Object` didefinisikan sebagai berikut:

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

Dari implementasi aslinya, terlihat jelas bahwa method `equals()` bawaan class `Object` hanya menggunakan operator relasional `==`. Seperti yang telah diuraikan pada nomor 1, operator `==` hanya mengecek kesetaraan referensi memori (*reference equality* atau *identity equality*). Dua objek hanya dianggap sama (`equals == true`) jika keduanya merujuk ke alamat memori yang persis sama di Heap.

Jika kita ingin memvalidasi agar tidak terjadi duplikasi karakter berdasarkan nama (misalnya melarang pembuatan karakter baru bernama `"Luna"` jika sudah ada karakter `"Luna"` yang terdaftar), method `equals()` bawaan class `Object` akan gagal total. Ketika kode menjalankan:
```java
Character c1 = new Character("Luna", CharacterType.MAGE, 8);
Character c2 = new Character("Luna", CharacterType.ARCHER, 4);
boolean sama = c1.equals(c2); // Menghasilkan false jika memakai bawaan Object!
```
Karena `c1` dan `c2` diciptakan melalui pemanggilan `new` yang terpisah, keduanya berada di alamat memori yang berbeda (`c1 != c2`). Akibatnya, `c1.equals(c2)` bawaan Object mengembalikan nilai `false`, sehingga sistem keliru menganggap bahwa `"Luna"` kedua adalah karakter yang berbeda, dan duplikasi nama yang tidak diinginkan akan lolos ke dalam basis data/koleksi aplikasi.

### **Solusi:**
Solusinya adalah melakukan **Method Overriding** terhadap method `equals(Object o)` di dalam kelas `Character.java` untuk mendefinisikan *logical/semantic equality* berdasarkan kesamaan atribut nama. Selain itu, aturan baku Java mensyaratkan bahwa setiap kali `equals()` di-override, method `hashCode()` wajib di-override secara konsisten agar struktur data berbasis Hash (seperti `HashSet` dan `HashMap`) dapat berfungsi dengan tepat:

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true; // Cek kesamaan referensi untuk optimasi performa
    if (o == null || getClass() != o.getClass()) return false; // Cek null dan tipe kelas
    Character character = (Character) o;
    // Perbandingan semantik nilai nama (case-insensitive)
    return name != null && name.equalsIgnoreCase(character.name);
}

@Override
public int hashCode() {
    return name != null ? name.toLowerCase().hashCode() : 0;
}
```
Dengan override ini, pemanggilan `characters.contains(characterBaru)` pada `ArrayList` di dalam `CharacterManager` akan memanggil logika `equals` kustom kita, sehingga nama duplikat dapat dideteksi dan ditolak secara konsisten.

---

## **5. Risiko Pengembalian ArrayList Asli pada `getAllCharacters()` dan Kaitannya dengan Reference**

### **Risiko Kebocoran Enkapsulasi (Encapsulation Breach):**
Prinsip enkapsulasi (*Encapsulation*) dalam Object-Oriented Programming mengamanatkan bahwa data internal suatu objek (`private ArrayList<Character> characters;`) harus disembunyikan dan hanya dapat dimanipulasi melalui antarmuka (*interface*) atau method publik yang telah divalidasi oleh kelas pemiliknya.

Jika method `getAllCharacters()` diimplementasikan secara naif dengan mengembalikan referensi langsung ke koleksi internal:
```java
// CONTOH KODE BURUK (ANTI-PATTERN / MEMBUKA CELAH EKSPLOITASI):
public ArrayList<Character> getAllCharacters() {
    return this.characters; // Mengembalikan referensi asli!
}
```
Maka risiko keamanan dan integritas data yang terjadi meliputi:

1. **Pemberian Akses Kontrol Penuh kepada Pihak Luar (Leaking Representation):**
   Mengacu pada konsep referensi di nomor 1, pihak luar (*caller*) yang memanggil `ArrayList<Character> list = manager.getAllCharacters();` akan menerima salinan alamat memori Stack yang menunjuk langsung ke blok `ArrayList` asli di Heap milik `CharacterManager`.
2. **Bypass Validasi Integritas Data:**
   Kelas `CharacterManager` telah bersusah payah merancang method `addCharacter()` dengan validasi penolakan nama duplikat dan validasi null. Namun, karena caller memegang referensi ke `characters` asli, caller dapat melakukan manipulasi langsung tanpa melalui method `addCharacter()`, misalnya:
   ```java
   manager.getAllCharacters().add(new Character("Luna", CharacterType.WARRIOR)); // Memasukkan duplikat!
   manager.getAllCharacters().add(null); // Memasukkan objek null yang merusak sistem!
   ```
3. **Kerusakan Total State Koleksi (Destructive Mutation):**
   Caller dapat secara tidak sengaja atau sengaja mengeksekusi operasi destruktif seperti:
   ```java
   manager.getAllCharacters().clear(); // Seluruh data karakter di dalam manager langsung terhapus seketika!
   ```
   Operasi ini menghancurkan seluruh data internal aplikasi tanpa disadari oleh class `CharacterManager`.

### **Solusi Arsitektural (Defensive Copying):**
Untuk meniadakan risiko tersebut, method `getAllCharacters()` wajib menerapkan teknik **Defensive Copying**, yaitu membungkus koleksi ke dalam instansiasi `ArrayList` baru sebelum dikembalikan kepada caller:

```java
// IMPLEMENTASI YANG AMAN DAN MEMENUHI PRINSIP ENKAPSULASI:
public ArrayList<Character> getAllCharacters() {
    return new ArrayList<>(this.characters); // Mengembalikan salinan list baru
}
```
Dengan mengembalikan instansiasi list baru di Heap, jika pihak luar memanggil `.clear()` atau `.add()`, yang termodifikasi hanyalah objek list salinan di tangan caller tersebut. Koleksi internal asli `this.characters` di dalam `CharacterManager` tetap utuh, terjaga, dan terisolasi sempurna dari gangguan eksternal, sebagaimana dibuktikan secara empiris dalam eksperimen Halaman 7 pada pengujian `Main.java`.

---
*Dokumen laporan ini disusun secara mandiri dan komprehensif sebagai bagian dari pemenuhan Tugas Praktikum 5 (Sprint #2) - Pemrograman Berorientasi Objek.*
