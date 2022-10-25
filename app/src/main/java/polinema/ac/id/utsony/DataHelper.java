package polinema.ac.id.utsony;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todolist.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tugas(no integer primary key autoincrement, tugas text null, deskripsi text null, target date null, prioritas text null);";
        Log.d("Data", "onCreate"+sql);
        db.execSQL(sql);
        sql = "INSERT INTO tugas(tugas, deskripsi, target, prioritas) VALUES ('Pemrograman Mobile','Membuat desain aplikasi','2022-10-21','High');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
