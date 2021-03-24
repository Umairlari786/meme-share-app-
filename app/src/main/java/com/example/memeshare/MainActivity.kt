package com.example.memeshare


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VIEW_LOG_TAG
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    //var memeimageView :memeimageView
    lateinit var progressBar:ProgressBar
    lateinit var meme_image:ImageView
     var current_image:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //val memeimageView=findViewById<ImageView>(R.id.memeimageView)
        meme_image=findViewById(R.id.memeimageView)
        progressBar=findViewById(R.id.progressBar)
        loadMeme()
    }

    private fun loadMeme() {

        progressBar.visibility=  View.VISIBLE




        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    current_image  = response.getString("url")


                    Glide.with(this).load(current_image).into(meme_image)
                    progressBar.visibility= View.GONE
                },

                Response.ErrorListener { error ->
                    // TODO: Handle error
                }


        )


// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }

    fun memeshare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="Image/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"CHECKOUT THIS COOL MEMES $current_image")
        val chooser=Intent.createChooser(intent,"share this memes")
        startActivity(chooser)


    }

    fun nextmeme(view: View) {
        loadMeme()

    }
}






