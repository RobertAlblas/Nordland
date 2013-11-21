package robertalblas.nordland.resource.sound.music;

import java.util.List;

import robertalblas.nordland.resource.sound.Sound;
import robertalblas.nordland.resource.sound.SoundSet;

public interface MusicPlayer{
	public void appendToQueue(Sound sound);

	public void appendQueue(SoundSet soundSet);

	public void insertToQueue(Sound sound);

	public void insertToQueue(SoundSet soundSet);

	public void repeatAll();

	public void repeatSingle();

	public void shuffleQueue();
	
	public void playRandomly(boolean randomly);

	public void clearQueue();

	public void play();

	public void pause();

	public void next();

	public void previous();

	public List<Sound> getQueue();

	public Sound getCurrentSound();

	public int getQueueSize();
}
