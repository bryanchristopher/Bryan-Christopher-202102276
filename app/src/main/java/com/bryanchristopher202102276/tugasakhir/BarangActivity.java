package com.bryanchristopher202102276.tugasakhir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BarangActivity extends AppCompatActivity {

    EditText kode,nama,jenis,banyak,harga;
    String isikode,isinama,isijenis,isibanyak,isiharga;
    Button simpan,tampil,hapus,edit;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        kode = findViewById(R.id.edtnim);
        nama = findViewById(R.id.edtnama);
        jenis = findViewById(R.id.edtjk);
        banyak = findViewById(R.id.edtalamat);
        harga = findViewById(R.id.edtemail);
        simpan = findViewById(R.id.btnsimpan);
        tampil = findViewById(R.id.btntampil);
        hapus = findViewById(R.id.btnhapus);
        edit = findViewById(R.id.btnedit);
        db = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  isikode = kode.getText().toString();
                String isijudul = nama.getText().toString();
                String isipengarang = jenis.getText().toString();
                String isipenerbit = banyak.getText().toString();
                String isiisbn = harga.getText().toString();

                if (TextUtils.isEmpty(isikode) || TextUtils.isEmpty(isijudul) || TextUtils.isEmpty(isipengarang)
                        || TextUtils.isEmpty(isipenerbit) || TextUtils.isEmpty(isiisbn)){
                    Toast.makeText(BarangActivity.this,"Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkkode = db.checkkodebarang(isikode);
                    if (checkkode == false){
                        Boolean insert = db.insertDataBarang(isikode,isijudul,isipengarang,isipenerbit,isiisbn);
                        if (insert == true){
                            Toast.makeText(BarangActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            //   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            //  startActivity(intent);
                        }else {
                            Toast.makeText(BarangActivity.this,"Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(BarangActivity.this,"Data Barang Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });


        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.tampilDataBarang();
                if(res.getCount() == 0){
                    Toast.makeText(BarangActivity.this,"Tidak ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Kode Barang : " + res.getString(0) + "\n");
                    buffer.append("Nama Barang : " + res.getString(1) + "\n");
                    buffer.append("Jenis Barang : " + res.getString(2) + "\n");
                    buffer.append("Banyak Barang : " + res.getString(3) + "\n");
                    buffer.append("Harga Barang : " + res.getString(4) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(BarangActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Data Barang");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kb = kode.getText().toString();
                Boolean cekHapusData = db.hapusDatabarang(kb);
                if (cekHapusData == true)
                    Toast.makeText(BarangActivity.this,"Data Terhapus", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BarangActivity.this,"Data Tidak Ada", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  isikode = kode.getText().toString();
                String isijudul = nama.getText().toString();
                String isipengarang = jenis.getText().toString();
                String isipenerbit = banyak.getText().toString();
                String isiisbn = harga.getText().toString();

                if (TextUtils.isEmpty(isikode) || TextUtils.isEmpty(isijudul) || TextUtils.isEmpty(isipengarang)
                        || TextUtils.isEmpty(isipenerbit) || TextUtils.isEmpty(isiisbn)){
                    Toast.makeText(BarangActivity.this,"Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                }else {
                    Boolean edit = db.editDataBarang(isikode, isijudul, isipengarang, isipenerbit, isiisbn);
                    if (edit == true) {
                        Toast.makeText(BarangActivity.this, "Data Berhasil di Edit", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BarangActivity.this, "Data Gagal di Edit", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }




}