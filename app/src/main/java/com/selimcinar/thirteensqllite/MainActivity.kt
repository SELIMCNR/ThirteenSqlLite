package com.selimcinar.thirteensqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            //Dene kodları hata yoksa çalıştır varsa catch bloğuna git

            //Veritabanı varsa aç veya veritabanı oluştur.("veritabanı adı",modugizlimivs,faktörü)
            val veritabanı = this.openOrCreateDatabase("Urunler", MODE_PRIVATE,null)

            //Veritabanı tablosu oluşturma(Tablo oluştur daha önce oluşmadıysa(İd alanı integer anahtar kelimeli,isim alanı varchar tipli,fiyat alanı int tipli))
            veritabanı.execSQL("CREATE TABLE IF NOT EXISTS urunler (id INTEGER PRIMARY KEY,isim VARCHAR,fiyat INT)")

            //Tabloya ekle alanlara değerler urunlertablosu(isim,fiyat alanına )değer olarak('ayakkabi',100) ekle
           // veritabanı.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Ayakkabi',100)")
            //Ekleme işlemleri kolana
/*
            veritabanı.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Elbise',150)")
            veritabanı.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Tshirt',50)")
            veritabanı.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Atki',200)")
            veritabanı.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Bere',10)")
            */

            //Silme işlemleri kolona veriye göre
            //İdsi 5 olanı sil
            veritabanı.execSQL("DELETE FROM urunler Where id = 5")

            //Güncelleme işlemleri kolona veriye göre
            //Fiyatı günceller
            veritabanı.execSQL("Update urunler SET fiyat = 250 WHERE isim = 'Elbise'")
            //ismi günceller
            veritabanı.execSQL("Update urunler SET isim = 'Ayakkab' Where id = 1")


            //Seçim işlemleri ve filtreleme
            //Cursor  veritabanınquerysine sorgusuna göre (tüm tabloyu seç ,seçili argüman:null seçili filtre gibi)
            val cursor = veritabanı.rawQuery("SELECT * FROM urunler",null)
            // Cursor veritanıquerysine sorgusuna göre (tüm tabloyu seç ve isimi 'bere' olan değeri getir)
            //val cursor = veritabanı.rawQuery("SELECT* FROM urunler WHERE isim = 'Bere'",null)
            //val cursor = veritabanı.rawQuery("SELECT* FROM urunler WHERE id=3",null)
            //val cursor = veritabanı.rawQuery("SELECT* FROM urunler WHERE isim = 'Bere'",null)

            //Ismı A ile başlayan değerleri getir.
            //val cursor = veritabanı.rawQuery("SELECT* FROM urunler WHERE isim LIKE 'A%'",null)

            //Sonu i ile biten değerleri getir.
            //val cursor = veritabanı.rawQuery("SELECT* FROM urunler WHERE isim LIKE '%i'",null)


            //Yazdırma işlemleri
            //İD İSMİNE sahip kolonu getir
            val idColumnIndex = cursor.getColumnIndex("id")
           //isim adına sahip kolonu getir
            val isimColumnIndex = cursor.getColumnIndex("isim")
            //fiyat adına sahip kolonu getir
            val fiyatColumnIndex=cursor.getColumnIndex("fiyat")

            //cursorla dolaşım yap
            while (cursor.moveToNext()){
                //İdColumn ındexte ınt değeri al yazdır.
                println("ID:${cursor.getInt(idColumnIndex)}")

                //isimColumnındexte string değeri al yazdır.
                println("ISIM: ${cursor.getString(isimColumnIndex)}")

                //Fiyatcolumnındexte string değeri al yazdır.
                println("Fiyat: ${cursor.getString(fiyatColumnIndex)}")

            }
            cursor.close() // cursoru kapat


        }
        catch (e:Exception){
            //Hata var ise buradaki işlemi yap
            e.printStackTrace() //Hata var ise hatayı loglara yazdır.
        }
    }
}