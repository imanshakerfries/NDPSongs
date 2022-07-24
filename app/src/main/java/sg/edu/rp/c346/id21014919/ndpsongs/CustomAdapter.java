package sg.edu.rp.c346.id21014919.ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvSongName = rowView.findViewById(R.id.tvSongName);
        TextView tvSongYear = rowView.findViewById(R.id.tvSongYear);
        TextView tvSongStar = rowView.findViewById(R.id.tvSongStar);
        TextView tvSongSinger = rowView.findViewById(R.id.tvSongSinger);

        // Obtain the Android Version information based on the position
        Song currentSong = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvSongName.setText(currentSong.getTitle());
        tvSongYear.setText(String.valueOf(currentSong.getYear()));
        tvSongStar.setText(currentSong.toString());
        tvSongSinger.setText(currentSong.getSinger());


        return rowView;
    }

}
