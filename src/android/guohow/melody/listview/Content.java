package android.guohow.melody.listview;

public class Content {

	private String letter;
	private String song_title;
	private String duration;
	private String artist;
	private String url;
	
		
	
	public Content(String letter, String name,String artist,String duration,String url) {
		super();
		this.letter = letter;
		this.song_title = name;
		this.artist=artist;
		this.duration=duration;
		this.url=url;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getName() {
		return song_title;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public String getDuration(){
		
		return duration;
	}
	
public String getUrl(){
		
		return url;
	}
	
	public void setName(String name) {
		this.song_title = name;
	}
	
	
}
