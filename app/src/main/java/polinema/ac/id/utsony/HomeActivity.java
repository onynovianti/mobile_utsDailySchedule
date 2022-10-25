package polinema.ac.id.utsony;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import polinema.ac.id.utsony.adapters.SuperHeroAdapter;
import polinema.ac.id.utsony.models.SuperHero;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static HomeActivity ma;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    // RecycleView
    DataHelper dbcenter;
    RecyclerView rvSuperHero;
    protected Cursor cursor;
    String[] daftar;

    // Instansi List Superhero
    List<SuperHero> listSuperHero = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // DRAWER
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // NAVIGATION
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigasi);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

        // RECYCLE VIEW
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

//        FloatingActionButton
        FloatingActionButton myFab = (FloatingActionButton)  findViewById(R.id.floating_action_button);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, BuatTask.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tugas", null);
        daftar = new String[cursor.getCount()];
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                listSuperHero.add(new SuperHero(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }

        // Menyambungkan rvSuperHero ke layout
        rvSuperHero = findViewById(R.id.rvSuperHero);
        // Instansi Adapter
        SuperHeroAdapter superHeroAdapter = new SuperHeroAdapter(listSuperHero);
        // Set adapter dan layoutmanager
        rvSuperHero.setAdapter(superHeroAdapter);
        rvSuperHero.setLayoutManager(new LinearLayoutManager(this));

        superHeroAdapter.setOnItemClickListener(new SuperHeroAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                final String selection = listSuperHero.get(position).getTugas();
                Intent i = new Intent(HomeActivity.this, UpdateTask.class);
                i.putExtra("tugas", selection);
                startActivity(i);
            }
        });
    }

    public void clear() {
        listSuperHero.clear();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String id = (String) item.getTitleCondensed();

        if(id.equals("today")){
            Intent i = new Intent(getApplicationContext(), TodayActivity.class);
            startActivity(i);
        }else if(id.equals("all")){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
        }else if(id.equals("logout")){
            MainActivity.main.onBtnLogout_Click();
        }

        return false;
    }
}
