package sg.edu.rp.c346.id20018318.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int selectedId = rgStars.getCheckedRadioButtonId();
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                int stars = Integer.parseInt(selectedButton.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singers, year, stars);
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();

                etTitle.setText("");
                etSingers.setText("");
                etYear.setText("");
                rgStars.clearCheck();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SongList.class);
                startActivity(i);
            }
        });

    }
}