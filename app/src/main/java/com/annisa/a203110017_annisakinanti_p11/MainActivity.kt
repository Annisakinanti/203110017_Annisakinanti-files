package com.annisa.a203110017_annisakinanti_p11

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//TODO 1: menagmbil isi variabel dari activity_main.xml
        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)

        //TODO 2: apabila btnSave ditekan blok kode ini akan dijalankan
        btnSave.setOnClickListener(View.OnClickListener {

            //mengkonversi variabel ke string
            val file:String = fileName.text.toString()
            val data:String = fileData.text.toString()
            val fileOutputStream:FileOutputStream

            // proses menyimpan data
            try {
                fileOutputStream = openFileOutput(file, MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }

            //apabila data berhasil disimpan
            Toast.makeText(applicationContext,"data tersimpan",Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()
        })

        //TODO 3: apabila btnRead ditekan blok kode ini akan dijalankan
        btnView.setOnClickListener(View.OnClickListener {
            val filename = fileName.text.toString()

            //membaca file dari directory
            if(filename.trim()!=""){
                val fileInputStream: FileInputStream?
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                fileData.setText(stringBuilder.toString()).toString()

            //apabila nama file kosong maka blok kode ini akan dijalankan
            }else{
                val applicationContext = null
                Toast.makeText(applicationContext,"nama file tidak boleh kosong",Toast.LENGTH_LONG).show()
            }
        })

    }
}