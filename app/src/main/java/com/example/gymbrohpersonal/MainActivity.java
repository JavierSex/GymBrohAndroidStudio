package com.example.gymbrohpersonal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.media.MediaPlayer;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MediaPlayer jmediaChad;
    EditText jetcodigo, jetnombre, jetciudad;
    RadioButton jrbPluma, jrbMedio, jrbPesado;
    CheckBox jcbActivo;
    String codigo,nombre,ciudad;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //colocar musiquita epica de fondo
        jmediaChad = MediaPlayer.create(this, R.raw.media);
        jmediaChad.start();

        //ocultar la barra de titulo y asociar objetos jjava con Xml
        getSupportActionBar().hide();

        jetciudad=findViewById(R.id.etCiudad);
        jetcodigo=findViewById(R.id.etCodigo);
        jetnombre=findViewById(R.id.etNombre);
        jrbPluma=findViewById(R.id.rbPluma);
        jrbMedio=findViewById(R.id.rbMedio);
        jrbPesado=findViewById(R.id.rbPesado);
        jcbActivo=findViewById(R.id.cbActivo);
    }

    public void Adicionar(View view){
        codigo=jetcodigo.getText().toString();
        nombre=jetnombre.getText().toString();
        ciudad=jetciudad.getText().toString();

        if (codigo.isEmpty() || nombre.isEmpty() || ciudad.isEmpty()){
            Toast.makeText(this, "Se requiere de todos los campos", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else {
            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("first", "Ada");
            user.put("last", "Lovelace");
            user.put("born", 1815);

            // Add a new document with a generated ID
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }
}