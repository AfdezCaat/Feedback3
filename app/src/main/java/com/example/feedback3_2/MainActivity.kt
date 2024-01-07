package com.example.feedback3_2

import android.media.MediaRecorder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.feedback3_2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var mediaRecorder: MediaRecorder
    lateinit var binding: ActivityMainBinding
    lateinit var fichero: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*Realizar una app que grabe audio. La interfaz debe tener al
        menos dos botones: uno para iniciar la grabación y otro para parar.*/
        binding.grabar.setOnClickListener{
            mediaRecorder = MediaRecorder()
            Log.d("AudioRecorder", "Before setAudioSource")
            //mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.DEFAULT)
            Log.d("AudioRecorder", "After setAudioSource")
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            val filePath = filesDir.absolutePath + "/audio_record.3gp"
            mediaRecorder?.setOutputFile(filePath)


            try {
                mediaRecorder?.prepare()
                mediaRecorder?.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Snackbar.make(binding.root,"Grabando",
                Snackbar.LENGTH_SHORT).show()
        /*  mediaAudio.apply {

            }*/
        }
        binding.parar.setOnClickListener{
            mediaRecorder.stop()
            mediaRecorder.reset()
            mediaRecorder.release()
            Snackbar.make(binding.root, "Grabación detenida", Snackbar.LENGTH_SHORT).show()
        }
    }
}