package com.sigcar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sigcar.R;

public class PrincipalActivity extends AppCompatActivity {


    private ListView listPrincipal;
    private String[] itens = {
            "Oleo do motor", "Pneu", "Filtro de Ar", "Pastilha de Freio", "Farol Lanterna", "Injeção/Carburador", "Filtro de Combustivel", "Outros serviçoes"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long positionSelected = getIntent().getIntExtra("POSITION",0);
        setContentView(R.layout.activity_principal);



        listPrincipal = findViewById(R.id.listPrincipal);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, android.R.id.text1, itens



        );

        listPrincipal.setAdapter(adaptador);

        listPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



                switch (position) {

                    case 0:


//                        Intent newActivity = new Intent(this, superleague.class);
//                        startActivity(newActivity);

                        Intent intent = new Intent(getApplicationContext(), MusicaActivity.class);
                        startActivity(intent);
                        break;

                    case 1:


                        break;

                    case 2:


                        break;

                    case 3:


                        break;

                    case 4:


                        break;

                    case 5:


                        break;

                    case 6:


                        break;


                }

            }

        });

    }


}