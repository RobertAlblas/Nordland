package robertalblas.nordland.resource.sound.music;

public class Music {
	private static MusicPlayer musicPlayer;

	public static MusicPlayer getMusicPlayer() {
		if(musicPlayer == null){
			musicPlayer = new MusicPlayerImpl();
		}
		return musicPlayer;
	}
}
