package com.example.amanshop.domain.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(
    private val context : Context
) :AudioPlayer {

    private var player : MediaPlayer? = null
    private var isPaused: Boolean = false
    private var currentPosition: Int = 0

    override fun playFile(file: File, onCompletion:()->Unit) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()

            setOnCompletionListener {
                onCompletion()
            }


        }

    }

    override fun stop() {
        player?.stop()
        player?.release()

        player = null
    }

    override fun pause() {
        player?.let {
            if (it.isPlaying) {
                it.pause()
                currentPosition = it.currentPosition
                isPaused = true
            }
        }
    }

    override fun resume() {
        player?.let {
            if (isPaused) {
                it.seekTo(currentPosition)
                it.start()
                isPaused = false
            }
        }
    }
}