package com.example.grabarvideo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    private lateinit var btn_capture: Button
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_capture = findViewById(R.id.btn_view)
        videoView = findViewById(R.id.videoView)
    }

    fun onClick(view: View){
      when(view.id){
          R.id.btn_cambiar ->{

              val intent = Intent(this,shida::class.java)
              startActivity(intent)
          }
          R.id.btn_view -> {
              val takeVideoIntent: Intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
              if(takeVideoIntent.resolveActivity(this.packageManager) != null){
                  startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
              }
          }
      }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK){
            val videoUri = data!!.data
            Log.d("VIDEO",videoUri.toString())
            videoView.setVideoURI(videoUri)
            videoView.setMediaController(MediaController(this))
            videoView.requestFocus()
            videoView.start()
        }
    }

    companion object{
        const val REQUEST_VIDEO_CAPTURE = 1
    }
}