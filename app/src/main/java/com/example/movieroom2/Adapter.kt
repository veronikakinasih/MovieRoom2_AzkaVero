package com.example.movieroom2

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Adapter (val mCtx: Context, val layoutResId: Int, val list: List<Movies> )
    : ArrayAdapter<Movies>(mCtx,layoutResId,list)  {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textTitle = view.findViewById<TextView>(R.id.textTitle)
        val textDescription = view.findViewById<TextView>(R.id.textDescription)
        val textUpdate = view.findViewById<TextView>(R.id.textUpdate)
        val textDelete = view.findViewById<TextView>(R.id.textDelete)

        val movie = list[position]

        textTitle.text = movie.title
        textDescription.text = movie.description
        textUpdate.setOnClickListener {
            showUpdateDialog(movie)
        }
        textDelete.setOnClickListener {
            Deleteinfo(movie)
        }


        return view

    }

    private fun showUpdateDialog(movie: Movies) { val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val textTitle = view.findViewById<EditText>(R.id.etTitle)
        val textDescription = view.findViewById<EditText>(R.id.etDescription)

        textTitle.setText(movie.title)
        textDescription.setText(movie.description)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbMovies = FirebaseDatabase.getInstance().getReference("MOVIES")

            val title = textTitle.text.toString().trim()

            val description = textDescription.text.toString().trim()

            if (title.isEmpty()){
                textTitle.error = "please enter name"
                textTitle.requestFocus()
                return@setPositiveButton
            }

            if (description.isEmpty()){
                textDescription.error = "please enter status"
                textDescription.requestFocus()
                return@setPositiveButton
            }

            val movie = Movies(movie.id,title,description)

            dbMovies.child(movie.id).setValue(movie).addOnCompleteListener {
                Toast.makeText(mCtx,"Updated",Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }

    private fun Deleteinfo(movie: Movies){
        val progressDialog = ProgressDialog(
            context,
            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("MOVIES")
        mydatabase.child(movie.id).removeValue()
        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Show::class.java)
        context.startActivity(intent)
    }
}