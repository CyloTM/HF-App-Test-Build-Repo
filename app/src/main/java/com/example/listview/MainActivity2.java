package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    ListView listView;
    String[] workoutsRoutines = {"Upper Body", "Lower body","Upper Body2", "Lower body2","Upper Body3", "Lower body3"};
    int[] workoutsBackgrounds = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        listView = findViewById(R.id.listview);
        MainActivity2.CustomAdapter customAdapter = new MainActivity2.CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener((parent, view, i, id) -> {
            Intent intent =  new Intent(getApplicationContext(),listdata.class);
            intent.putExtra("name", workoutsRoutines[i]);
            intent.putExtra("image", workoutsBackgrounds[i]);
            Toast.makeText(MainActivity2.this,"succ",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return workoutsBackgrounds.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.rowdata, null);

            TextView name = view1.findViewById((R.id.workouts));
            ImageView image = view1.findViewById((R.id.background));

            name.setText(workoutsRoutines[i]);
            image.setImageResource(workoutsBackgrounds[i]);

            view1.setOnClickListener(v -> Toast.makeText(MainActivity2.this,"SUCC",Toast.LENGTH_SHORT).show());

            return view1;

        }

    }


}