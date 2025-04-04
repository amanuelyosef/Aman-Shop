package com.example.amanshop.domain.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile:File)
    fun stop()
}