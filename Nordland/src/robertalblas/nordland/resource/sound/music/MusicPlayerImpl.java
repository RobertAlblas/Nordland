package robertalblas.nordland.resource.sound.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import robertalblas.nordland.resource.Resource;
import robertalblas.nordland.resource.sound.Sound;
import robertalblas.nordland.resource.sound.SoundFinishedPlayingListener;
import robertalblas.nordland.resource.sound.SoundSet;
import robertalblas.nordland.system.log.Logger;
import robertalblas.nordland.system.log.LoggerManager;

public class MusicPlayerImpl implements MusicPlayer,
		SoundFinishedPlayingListener {

	private List<Sound> queue;
	private int currentSound;
	private RepeatBehaviour repeatBehaviour;
	private boolean playRandomly;

	private boolean isPaused;

	public MusicPlayerImpl() {
		this.queue = new ArrayList<Sound>();
		this.currentSound = 0;
		this.repeatBehaviour = RepeatBehaviour.REPEAT_NONE;
		this.isPaused = true;
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
			this.isPaused = false;
		}
	}

	@Override
	public void pause() {
		this.isPaused = true;
		if(currentSound < this.queue.size()){
			this.queue.get(currentSound).pause();
		}
	}

	@Override
	public void next() {
		try {
			this.queue.get(currentSound).stop();
			if (playRandomly) {
				currentSound = (new Random()).nextInt(queue.size());
			} else {
				if (currentSound == queue.size() - 1) {
					currentSound = 0;
					if (repeatBehaviour != RepeatBehaviour.REPEAT_ALL) {
						clearQueue();
					}
				} else {
					currentSound++;
				}
			}

			this.queue.get(currentSound).play();
		} catch (IndexOutOfBoundsException e) {
			LoggerManager.getInstance().getDefaultLogger()
					.log("Invalid index", Logger.LOGTYPE_WARNING);
		}

	}

	@Override
	public void previous() {
		try {
			this.queue.get(currentSound).stop();
			this.currentSound--;
			this.queue.get(currentSound).play();
		} catch (IndexOutOfBoundsException e) {
			LoggerManager.getInstance().getDefaultLogger()
					.log("Invalid index", Logger.LOGTYPE_WARNING);
		}

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
		if (!isPaused) {
			if (repeatBehaviour == RepeatBehaviour.REPEAT_SINGLE) {
				queue.get(currentSound).play();
			} else {
				next();
			}
		}
	}

	@Override
	public void setQueueIndex(int index) {
		try {
			int previousSound = currentSound;
			this.currentSound = index;
			this.queue.get(previousSound).stop();
			this.queue.get(currentSound).play();
		} catch (IndexOutOfBoundsException e) {
			LoggerManager.getInstance().getDefaultLogger()
					.log("Invalid index", Logger.LOGTYPE_WARNING);
		}
	}

}
