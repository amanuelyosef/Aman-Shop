package com.example.amanshop.domain.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File, onCompletion:()->Unit)
    fun stop()
    fun pause()
    fun resume()
}