package com.example.movieroom2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_insert.*

class Insert : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        ref = FirebaseDatabase.getInstance().getReference("MOVIES")

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, Show::class.java)
            startActivity(intent)
        }

    }
    private fun savedata() {
        val title = etTitle.text.toString()
        val description= etDescription.text.toString()

        val movie = Movies(title,description)
        val movieId = ref.push().key.toString()

        ref.child(movieId).setValue(movie).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            etTitle.setText("")
            etDescription.setText("")
        }
    }
}
