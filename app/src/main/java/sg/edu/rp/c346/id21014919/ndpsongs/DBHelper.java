package sg.edu.rp.c346.id21014919.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STAR = "star";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT, "
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STAR + " INTEGER) ";
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN  module_name TEXT ");
    }

    public long insertSong(String title, String singer, int year, int star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STAR, star);
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, should not be -1
        return result;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STAR};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song song = new Song(id, title, singer, year, star);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getStarSongs(int keyword) {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STAR};
        String condition = COLUMN_STAR + " Like ? ";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song song = new Song(id, title, singer, year, star);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGER, data.getSinger());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STAR, data.getStar());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

}
