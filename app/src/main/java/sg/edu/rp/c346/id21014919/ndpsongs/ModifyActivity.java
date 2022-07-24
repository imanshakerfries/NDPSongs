package sg.edu.rp.c346.id21014919.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    EditText etSongID, etSongTitle, etSinger, etYear;
    RadioGroup rgStar;
    RadioButton rbStar;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etSongID = findViewById(R.id.etSongID);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rg);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etSongID.setFocusable(false);
        etSongID.setText(String.valueOf(data.getId()));
        etSongTitle.setText(data.getTitle());
        etSinger.setText(data.getSinger());
        etYear.setText(String.valueOf(data.getYear()));
        int selected = data.getStar();
        if(selected == 1) {
            rbStar = findViewById(R.id.rbtn1);
            rbStar.setChecked(true);
        } else if(selected == 2) {
            rbStar = findViewById(R.id.rbtn2);
            rbStar.setChecked(true);
        } else if(selected == 3) {
            rbStar = findViewById(R.id.rbtn3);
            rbStar.setChecked(true);
        } else if(selected == 4) {
            rbStar = findViewById(R.id.rbtn4);
            rbStar.setChecked(true);
        } else if(selected == 5) {
            rbStar = findViewById(R.id.rbtn5);
            rbStar.setChecked(true);
        }

        etSongID.setText(data.getId());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setTitle(etSongTitle.getText().toString());
                data.setSinger(etSongTitle.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                int selected = rgStar.getCheckedRadioButtonId();
                rbStar = findViewById(selected);
                data.setStar(Integer.parseInt(rbStar.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}