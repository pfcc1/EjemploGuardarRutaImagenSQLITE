package com.example.ejemploguardarrutaimagensqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListarUsuarios extends AppCompatActivity {
    ManejadorBD manejadorBD;
    ListView lista;
    int contadorposicion=0;
    ArrayList<String> rutasArray = new ArrayList<String>();
    TextView textViewCamara,textViewGaleria;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
        ManejadorBD manejadorBD=new ManejadorBD(this);
        /*Intent intent=getIntent();
        String nombre=intent.getStringExtra(MainActivity.ENVIAR_NOMBRE);
        String rutaimagen=intent.getStringExtra(MainActivity.ENVIAR_RUTAIMAGEN);*/


       // textViewCamara=findViewById(R.id.textViewCamara);
        //textViewGaleria=findViewById(R.id.textViewGaleria);
        imageView=findViewById(R.id.imageView);
        lista=findViewById(R.id.listview);
      // System.out.println("Activity Listar: Nombre: "+nombre);
       // System.out.println("Activity Listar: Ruta Imagen: "+rutaimagen);

       /* textViewCamara.setText(nombre);
        textViewGaleria.setText(rutaimagen);*/
       // manejadorBD.insertar();

       /* Uri urifoto = Uri.parse(rutaimagen);

        try {
            Bitmap imagen =getBitmapFromUri(urifoto);
            imageView.setImageBitmap(imagen);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // System.out.println("IMG DECODE : "+imgFile.getAbsolutePath());

        final Cursor cursor = manejadorBD.listarUsuarios();

        ArrayAdapter<String> adapter;
        List<String> list = new ArrayList<String>();

        if ((cursor != null) && (cursor.getCount() > 0)) {
            while (cursor.moveToNext()) {
                String fila = "";
                fila += "ID: " + cursor.getString(0);
                fila += " NOMBRE: " + cursor.getString(1);
                //fila += " RUTA IMAGEN: " + cursor.getString(2);

                rutasArray.add(cursor.getString(2));


                list.add(fila);
            }

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                     String rimagen= rutasArray.get(position);

                          Uri urifoto = Uri.parse(rimagen);
                    System.out.println("Activity Listar: Ruta Imagen: "+rimagen);
                          try {


                              Bitmap imagen =getBitmapFromUri(urifoto);
                              imageView.setImageBitmap(imagen);


                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }





                });

            adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
            lista.setAdapter(adapter);

            cursor.close();
        } else {
            Toast.makeText(ListarUsuarios.this, "Nada que mostarr", Toast.LENGTH_SHORT).show();
        }


    }

    private Bitmap getBitmapFromUri ( Uri uri ) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver (). openFileDescriptor ( uri , "r" );
        FileDescriptor fileDescriptor = parcelFileDescriptor . getFileDescriptor ();
        Bitmap image = BitmapFactory . decodeFileDescriptor ( fileDescriptor );
        parcelFileDescriptor . close ();
        return image ;
    }




}
