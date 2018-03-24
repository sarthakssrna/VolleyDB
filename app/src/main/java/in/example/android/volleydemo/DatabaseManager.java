package in.example.android.volleydemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarthak Sharma on 24-03-2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="songs";

    private static final String CREATE_SONG_TABLE="CREATE TABLE "+Constants.SONGS_TABLE_NAME+"("+Constants.SONG_NAME+" VARCHAR , "
            +Constants.SONG_ARTIST+" VARCHAR , "+Constants.SONG_COVER_IMAGE+" VARCHAR )";
    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null ,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_SONG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public void insertSong(Song song)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+Constants.SONGS_TABLE_NAME+" WHERE "+Constants.SONG_NAME+" = '"+song.getName()+"'",null);

        if(cursor.getCount()==0)
        {
            ContentValues values = new ContentValues();
            values.put(Constants.SONG_NAME,song.getName());
            values.put(Constants.SONG_ARTIST,song.getArtist());
            values.put(Constants.SONG_COVER_IMAGE,song.getCoverImage());
            db.insert(Constants.SONGS_TABLE_NAME,null,values);

        }
        cursor.close();
    }
    public List<Song> getAllSongs(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+Constants.SONGS_TABLE_NAME,null);
        List<Song> songList = new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            Song song = new Song();
            song.setName(cursor.getString(cursor.getColumnIndex(Constants.SONG_NAME)));
            song.setArtist(cursor.getString(cursor.getColumnIndex(Constants.SONG_ARTIST)));
            song.setCoverImage(cursor.getString(cursor.getColumnIndex(Constants.SONG_COVER_IMAGE)));
            songList.add(song);
        }
        cursor.close();
        return songList;

    }
}
