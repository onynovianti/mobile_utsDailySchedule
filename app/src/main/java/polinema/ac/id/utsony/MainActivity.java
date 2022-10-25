package polinema.ac.id.utsony;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String DUMMY_PASSWORD = "12345678";
    public static MainActivity main;

    // Komponen
    private EditText edtPassword;
    private CheckBox chkKeepLogin;

    // SharedPreferences yang akan digunakan untuk menulis dan membaca data
    private SharedPreferences sharedPrefs;

    // Key-key untuk data yang disimpan di SharedPreferences
    private static final String KEEP_LOGIN_KEY = "key_keep_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = this;
        // Inisialisasi SharedPreferences
        this.sharedPrefs = this.getSharedPreferences("shrine", Context.MODE_PRIVATE);
        this.initComponents();

        this.autoLogin();
    }

    private void initComponents()
    {
        // Init components
        this.edtPassword = this.findViewById(R.id.edt_password);
        this.chkKeepLogin = this.findViewById(R.id.chk_keep_login);
    }

    public void onBtnLogin_Click(View view)
    {
        boolean valid = this.validateCredential();

        if(valid){
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);

            this.makeAutoLogin();
        }else{
            Toast.makeText(this, "Invalid password!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validateCredential(){
        String currentPassword = this.edtPassword.getText().toString();

        return (Objects.equals(currentPassword, DUMMY_PASSWORD));
    }

    private void makeAutoLogin()
    {
        // Mengatur agar selanjutnya pada saat aplikasi dibuka menjadi otomatis login
        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        if(this.chkKeepLogin.isChecked())
            editor.putBoolean(KEEP_LOGIN_KEY, true);
        else
            editor.remove(KEEP_LOGIN_KEY);

        editor.apply();
    }

    // QUIZ!
    private void autoLogin()
    {
        // Cek apakah sebelumnya aplikasi diatur agar bypass login?
        // Jika ya maka langsung buka activity berikutnya

        boolean keepLogin = this.sharedPrefs.getBoolean(KEEP_LOGIN_KEY,false);

        if(keepLogin != false){
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }

    public void onBtnLogout_Click()
    {
        // Mengatur agar selanjutnya pada saat aplikasi dibuka menjadi otomatis login
        SharedPreferences.Editor editor = this.sharedPrefs.edit();

        editor.remove(KEEP_LOGIN_KEY);
        editor.apply();
//        this.finish();

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}