package robertalblas.nordland.resource.sound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class WavSound extends Sound implements LineListener {

	private File audioFile;
	private Clip clip;

	public WavSound(String name, String filename) {
		super(name);
		this.audioFile = new File(filename);
	}

	@Override
	public void play() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(audioFile));
			clip.start();
			clip.addLineListener(this);
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}

	@Override
	public void pause() {
		if (clip != null) {
			clip.stop();
		}
	}

	@Override
	public void stop() {
		if (clip != null) {
			clip.stop();
			clip.setFramePosition(0);
		}
	}

	@Override
	public int getCurrentTime() {
		if (clip != null) {
			return clip.getFramePosition();
		} else {
			return 0;
		}
	}

	@Override
	public void update(LineEvent event) {
		if (event.getType() == LineEvent.Type.STOP) {
			clip.setFramePosition(0);
			clip.close();
			notifyObservers();
		}
	}

}
