package robertalblas.nordland.resource.sound;

import java.util.ArrayList;
import java.util.List;

import robertalblas.nordland.resource.Resource;

public abstract class Sound extends Resource {

	private List<SoundFinishedPlayingListener> soundFinishedPlayingListeners;

	public Sound(String name) {
		super(name);
		soundFinishedPlayingListeners = new ArrayList<SoundFinishedPlayingListener>();
	}

	public void addSoundFinishedPlayingListener(
			SoundFinishedPlayingListener soundFinishedPlayingListener) {
		this.soundFinishedPlayingListeners.add(soundFinishedPlayingListener);
	}
	
	public void notifyObservers(){
		for(SoundFinishedPlayingListener s: soundFinishedPlayingListeners){
			s.onSoundFinishedPlaying(this);
		}
	}

	public abstract void play();

	public abstract void pause();

	public abstract void stop();

	public abstract int getCurrentTime();

}
