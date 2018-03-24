package in.example.android.volleydemo;

/**
 * Created by rohitramaswamy on 13/01/18.
 */

public class Song
{
	String name,artist,coverImage;


	public Song(String name, String artist, String coverImage) {
		this.name = name;
		this.artist = artist;
		this.coverImage = coverImage;
	}

    public Song() {
    }

    public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
}
