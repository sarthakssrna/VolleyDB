package in.example.android.volleydemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohitramaswamy on 13/01/18.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>
{
	List<Song> songs = new ArrayList<>();
	Context context;


	public SongsAdapter(List<Song> songs, Context context) {
		this.songs = songs;
		this.context = context;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view;
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		holder.song_name.setText(songs.get(position).getName());
		holder.artist_name.setText(songs.get(position).getArtist());
		Glide.with(context).load(songs.get(position).getCoverImage()).into(holder.coverImage);
	}
	
	@Override
	public int getItemCount()
	{
		if(songs == null)
		{
			return 0;
		}
		else
		{
			Log.v("size",String.valueOf(songs.size()));
			return (int) songs.size();
		}
	}
	
	class ViewHolder extends RecyclerView.ViewHolder
	{
		TextView song_name,artist_name;
		ImageView coverImage;
		
		public ViewHolder(View itemView)
		{
			super(itemView);
			song_name = itemView.findViewById(R.id.song_name);
			artist_name = itemView.findViewById(R.id.artist_name);
			coverImage=itemView.findViewById(R.id.cover_image);
		}
	}
}
