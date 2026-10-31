# ANALISIS OBJECT REFERENCE, STATIC, DAN ENCAPSULATION

### IDENTITAS MAHASISWA
**Nama:** Keysi Permata Sari
**NIM:** 253303621228

---

## Object Reference dan Aliasing

Perhatikan kode berikut:

```java
Character a = new Character("KsatriaA", CharacterType.WARRIOR, 1);
Character b = a;
b.setLevel(8);
```

**Hasil:**
- `a.getLevel()` menghasilkan **8**.
- `a == b` menghasilkan **true**.

**Penjelasan:**

`b = a` menyalin reference, bukan membuat objek baru. Kedua variabel menunjuk objek yang sama di Heap. Kondisi ini disebut **Object Aliasing**.

Agar objek independen, gunakan copy constructor:

```java
Character b = new Character(a);
b.setLevel(8);
```

Dengan demikian, perubahan pada `b` tidak memengaruhi `a` dan `a == b` bernilai `false`.

---

## Primitive Value dan Reference Value

- **Primitive Value:** Assignment menyalin nilai secara langsung. Perubahan variabel salinan tidak memengaruhi variabel asal.
- **Reference Value:** Assignment menyalin nilai referensi sehingga dua variabel dapat menunjuk objek yang sama.

**Analogi:** Primitive seperti menyalin catatan ke kertas baru, sedangkan reference seperti dua kunci yang membuka loker yang sama.

---

## Penggunaan Static

Variabel `totalCharacterCreated` harus menggunakan `static` karena berfungsi menghitung seluruh objek `Character` yang dibuat.

```java
private static int totalCharacterCreated = 0;
```

Dengan `static`, semua objek menggunakan satu penghitung bersama. Jika tidak menggunakan `static`, setiap objek memiliki penghitung sendiri sehingga jumlah total karakter tidak dapat dihitung dengan benar.

---

## equals() dan hashCode()

Method bawaan `Object.equals()` membandingkan identitas reference, bukan isi objek. Akibatnya, dua objek berbeda dengan nama sama dianggap tidak sama.

Solusinya adalah melakukan override `equals()` dan `hashCode()`:

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Character character = (Character) o;
    return name != null && name.equalsIgnoreCase(character.name);
}

@Override
public int hashCode() {
    return name != null ? name.toLowerCase().hashCode() : 0;
}
```

Dengan implementasi tersebut, karakter dengan nama sama dianggap setara tanpa membedakan huruf besar dan kecil.

---

## Enkapsulasi dan Defensive Copying

Mengembalikan `ArrayList` asli dapat menyebabkan data internal `CharacterManager` diubah langsung oleh pihak luar.

Contohnya:
- Menambahkan karakter duplikat.
- Memasukkan nilai `null`.
- Menghapus seluruh data karakter.

Solusinya menggunakan **Defensive Copying**:

```java
public ArrayList<Character> getAllCharacters() {
    return new ArrayList<>(this.characters);
}
```

Dengan demikian, pemanggil mendapatkan list baru sehingga perubahan pada list tersebut tidak memengaruhi list internal `CharacterManager`.

## Screenshot Output CRUD
![alt text](image-1.png)
![alt text](image-2.png)
![alt text](image-3.png)
![alt text](image-4.png)

## Google Docs
Link Google Docs: "<https://docs.google.com/document/d/1rGlaiYQTcKmhFxSZHE_rc9npC0_kj8XpU547rbVT4JY/edit?usp=sharing>"

**Keysi Permata Sari | 253303621228**