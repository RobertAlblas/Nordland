package robertalblas.nordland.resource.sound.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.sound.Sound;
import robertalblas.nordland.resource.sound.SoundFinishedPlayingListener;
import robertalblas.nordland.resource.sound.SoundSet;

public class MusicPlayerImpl implements MusicPlayer,
		SoundFinishedPlayingListener {

	private List<Sound> queue;
	private int currentSound;
	private RepeatBehaviour repeatBehaviour;
	private boolean playRandomly;

	public MusicPlayerImpl() {
		this.queue = new ArrayList<Sound>();
		this.currentSound = 0;
		this.repeatBehaviour = RepeatBehaviour.REPEAT_NONE;
	}

	@Override
	public void appendToQueue(Sound sound) {
		this.queue.add(sound);
		addSoundFinishedPlayingListener(sound);
	}

	@Override
	public void appendQueue(SoundSet soundSet) {
		queue.addAll(getSoundsFromSoundSet(soundSet));
		addSoundFinishedPlayingListener(soundSet);
	}

	@Override
	public void insertToQueue(Sound sound) {
		queue.add(currentSound + 1, sound);
		addSoundFinishedPlayingListener(sound);
	}

	@Override
	public void insertToQueue(SoundSet soundSet) {
		queue.addAll(currentSound + 1, getSoundsFromSoundSet(soundSet));
		addSoundFinishedPlayingListener(soundSet);
	}

	private List<Sound> getSoundsFromSoundSet(SoundSet soundSet) {
		List<Sound> soundsFromSoundSet = new ArrayList<Sound>();
		for (Resource r : soundSet.getResources()) {
			soundsFromSoundSet.add((Sound) r);
		}
		return soundsFromSoundSet;
	}

	private void addSoundFinishedPlayingListener(Sound sound) {
		sound.addSoundFinishedPlayingListener(this);
	}

	private void addSoundFinishedPlayingListener(SoundSet soundSet) {
		List<Sound> soundsFromSoundSet = getSoundsFromSoundSet(soundSet);
		for (Sound s : soundsFromSoundSet) {
			s.addSoundFinishedPlayingListener(this);
		}
	}

	@Override
	public void repeatAll() {
		this.repeatBehaviour = RepeatBehaviour.REPEAT_ALL;
	}

	@Override
	public void repeatSingle() {
		this.repeatBehaviour = RepeatBehaviour.REPEAT_SINGLE;
	}

	@Override
	public void shuffleQueue() {
		Collections.shuffle(queue);
	}

	@Override
	public void clearQueue() {
		this.queue.clear();
	}

	@Override
	public void play() {
		if (queue.get(currentSound) != null) {
			this.queue.get(currentSound).play();
		}
	}

	@Override
	public void pause() {
		this.queue.get(currentSound).pause();
	}

	@Override
	public void next() {
		this.queue.get(currentSound).stop();
		if(playRandomly){
			currentSound = (new Random()).nextInt(queue.size());
		}
		else{
			if(currentSound == queue.size() -1 ){
				currentSound = 0;
				if(repeatBehaviour != RepeatBehaviour.REPEAT_ALL){
					clearQueue();
				}
			}
			else{
				currentSound++;
			}
		}
		
		this.queue.get(currentSound).play();
	}

	@Override
	public void previous() {
		this.queue.get(currentSound).stop();
		this.currentSound--;
		this.queue.get(currentSound).play();
	}

	@Override
	public List<Sound> getQueue() {
		return this.queue;
	}

	@Override
	public Sound getCurrentSound() {
		return this.queue.get(currentSound);
	}

	@Override
	public int getQueueSize() {
		return queue.size();
	}

	@Override
	public void playRandomly(boolean randomly) {
		this.playRandomly = randomly;
	}

	@Override
	public void onSoundFinishedPlaying() {
		if (repeatBehaviour == RepeatBehaviour.REPEAT_SINGLE) {
			queue.get(currentSound).play();
		} else {
			next();
		}
	}

}
