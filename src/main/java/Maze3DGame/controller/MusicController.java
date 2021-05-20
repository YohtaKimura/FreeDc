package Maze3DGame.controller;

import java.io.File;
import javafx.scene.media.AudioClip;

public class MusicController {
	public static AudioClip c = new AudioClip(new File(System.getProperty("user.dir") + "/src/main/resources/music/hidokeinooka.mp3").toURI().toString());
}
