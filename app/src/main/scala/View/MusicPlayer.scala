package View

import javafx.scene.media.{Media, MediaPlayer}

import java.nio.file.Paths

object MusicPlayer {

  def play(): Thread = {
    val thread = new Thread {
      val hit: Media = new Media(Paths.get(getClass.getResource("/music/wave_music.mp3").toURI).toUri.toString)
      val mediaPlayer: MediaPlayer = new MediaPlayer(hit)
      mediaPlayer.play()
    }
    thread
  }
}
