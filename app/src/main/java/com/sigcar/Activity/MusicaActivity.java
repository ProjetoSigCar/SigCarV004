package com.sigcar.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sigcar.Adapter.ArtistListAdapter;
import com.sigcar.Classes.Artist;
import com.sigcar.R;

import java.util.ArrayList;
import java.util.List;

public class MusicaActivity extends AppCompatActivity {


    EditText editTextName;
    EditText editTextIdade;
    Button buttonAddArtist;
    Spinner spinnerGenres;
    ListView listViewArtists;

    List<Artist> artistList;

    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);

        databaseArtists = FirebaseDatabase.getInstance().getReference("Artists");


        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);
        listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        editTextIdade = (EditText) findViewById( R.id.editTextIdade );
        artistList = new ArrayList<>();

        buttonAddArtist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addArtist();
            }
        });


        listViewArtists.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Artist artist = artistList.get(i);
            new AlertDialog.Builder(this)
                    .setTitle("Delete " + artist.getArtistName() + " "
                    )
                    .setMessage("Você deseja excluir o registro selecionado?")
                    .setPositiveButton("Delete", (dialogInterface, i1) -> {
                        databaseArtists.child(artist.getArtistId()).removeValue();

                        Toast.makeText(this, artist.getArtistName() + ""+" Excluido com Sucesso", Toast.LENGTH_LONG).show();


                    })
                    .setNegativeButton("Cancel", (dialogInterface, i12) -> {
                        dialogInterface.dismiss();
                    })
                    .create()
                    .show();
            return true;
        });


        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Artist artist = artistList.get(i);

                // vamos chamar a nova activity, para trabalhar com as musicas (tracks)
                Intent intent = new Intent(getApplicationContext(), AddTrackActivity.class);

                // guardando o nome e o id do artista na intent, para ser recuperada
                // pela nova activity
                intent.putExtra("ARTIST_ID", artist.getArtistId());
                intent.putExtra("ARTIST_NAME", artist.getArtistName());

                // enfim, chama a activity
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onStart() {

        super.onStart();

        databaseArtists.addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }

                ArtistListAdapter adapter = new ArtistListAdapter(MusicaActivity.this, artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }



    private void addArtist() {

        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();
        String idade = editTextIdade.getText().toString();

        if( !TextUtils.isEmpty(name) ) {

            String id = databaseArtists.push().getKey();
            Artist artist = new Artist(id, name, genre, idade);
            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this, "Anotação adicionada com Sucesso", Toast.LENGTH_LONG).show();
        }
        else {

            Toast.makeText(this, "Você deve digitar um nome", Toast.LENGTH_LONG).show();
        }
    }
}