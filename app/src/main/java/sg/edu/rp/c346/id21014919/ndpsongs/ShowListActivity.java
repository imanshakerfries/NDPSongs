package sg.edu.rp.c346.id21014919.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {

    Button btn5star;
    ListView lvSongs;
    ArrayList<Song> al;
    CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        btn5star = findViewById(R.id.btnFiveStar);
        lvSongs = findViewById(R.id.lvSongs);

        al = new ArrayList<Song>();
//        aa = new ArrayAdapter<Song>(this,
//                android.R.layout.simple_list_item_1, al);
        ca = new CustomAdapter(this, R.layout.row, al);
        lvSongs.setAdapter(ca);

        DBHelper dbh = new DBHelper(ShowListActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        ca.notifyDataSetChanged();

        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowListActivity.this);
                al.clear();
                int filterText = 5;
                al.addAll(dbh.getStarSongs(filterText));
                ca.notifyDataSetChanged();
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Song song = al.get(position);
                Intent i = new Intent(ShowListActivity.this,
                        ModifyActivity.class);
                i.putExtra("song", song);
                startActivity(i);

            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowListActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        ca.notifyDataSetChanged();
    }
}