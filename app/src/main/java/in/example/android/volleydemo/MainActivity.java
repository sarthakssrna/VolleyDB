package in.example.android.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	
	private final String LOG_TAG = MainActivity.class.getSimpleName();
	StringRequest request;
	RequestQueue queue;
	RecyclerView recyclerView;
	SongsAdapter adapter;
	DatabaseManager databaseManager;
	List<Song> songs = new ArrayList<>();

	String url = "http://starlord.hackerearth.com/studio";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		databaseManager= new DatabaseManager(MainActivity.this);
		recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		getSongs();
		
	}
	
	public void getSongs()
	{
		queue = Volley.newRequestQueue(this);
		request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
		{
			@Override
			public void onResponse(String response)
			{
				Log.v("MainActivity",response);
				try
				{
					JSONArray root = new JSONArray(response);
					for(int i=0;i<root.length();i++)
					{
						JSONObject songInfo =  root.optJSONObject(i);
						String song_name = songInfo.optString("song");
						String artist_name = songInfo.optString("artists");
						String coverImage=songInfo.optString("cover_image");
						Song song= new Song(song_name,artist_name,coverImage);
						//songs.add(song);
						databaseManager.insertSong(song);
					}

					songs=databaseManager.getAllSongs();
					adapter = new SongsAdapter(songs,MainActivity.this);
					recyclerView.setAdapter(adapter);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error)
			{
				Log.v(LOG_TAG,"error");
				Log.v(LOG_TAG,error.toString());
				songs=databaseManager.getAllSongs();
				adapter = new SongsAdapter(songs,MainActivity.this);
				recyclerView.setAdapter(adapter);
			}
		});
		queue.add(request);
	}
}
