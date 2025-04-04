package com.example.amanshop.domain.playback

import android.media.MediaMetadataRetriever
import java.io.File
import java.util.Locale
import java.util.concurrent.TimeUnit

fun getAudioDuration(file: File?): Long {
    if (file == null) return 0L
    val retriever = MediaMetadataRetriever()
    return try {
        retriever.setDataSource(file.absolutePath)
        val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        durationStr?.toLong() ?: 0L
    } catch (e: Exception) {
        e.printStackTrace()
        0L
    } finally {
        retriever.release()
    }
}

fun formatDuration(millis: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(minutes)
    val hours = TimeUnit.MILLISECONDS.toHours(millis)
    val remainingMinutes = minutes - TimeUnit.HOURS.toMinutes(hours)

    return if (hours > 0) {
        String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, remainingMinutes, seconds)
    } else {
        String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }
}
