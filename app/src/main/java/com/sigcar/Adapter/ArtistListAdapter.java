package com.sigcar.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sigcar.Classes.Artist;
import com.sigcar.R;

import java.util.List;

public class ArtistListAdapter extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> artistList;  // lista para armazenar os artitas

    public ArtistListAdapter(Activity context, List<Artist> artistList) {

        super(context, R.layout.layout_artist_item, artistList);
        this.context = context;
        this.artistList = artistList;
    }

    // método que é chamado para fornecer cada item da lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // criando um objeto "inflador"
        LayoutInflater inflater = context.getLayoutInflater();

        // usando o inflador para criar uma View a partir do arquivo de layout
        // que fizemos definindo os itens da lista
        View listViewItem = inflater.inflate(R.layout.layout_artist_item, null, true);

        // pegando referências para as views que definimos dentro do item da lista,
        // isto é, os 2 textviews
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewIdade = (TextView) listViewItem.findViewById(R.id.textViewIdade);

        // a posição do artista na lista (armazenamento) é a mesma na lista (listview)
        // então usamos esse valor (position) para acessar o objeto "Artist" correto
        // dentro da lista artistList
        Artist artist = artistList.get(position);

        // finalmente, colocamos os valores do objeto artista recuperado
        // nas views que formam nosso item da lista
        textViewName.setText(artist.getArtistName());
        textViewGenre.setText(artist.getArtistGenre());
        textViewIdade.setText(artist.getArtistIdade());

        // a view está pronta! É só devolver para quem pediu
        return listViewItem;
    }
}