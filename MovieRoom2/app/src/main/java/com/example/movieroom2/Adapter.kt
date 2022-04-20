package com.example.movieroom2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter (val mCtx: Context, val layoutResId: Int, val list: List<Movies> )
    : ArrayAdapter<Movies>(mCtx,layoutResId,list)  {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textTitle = view.findViewById<TextView>(R.id.textTitle)
        val textDescription = view.findViewById<TextView>(R.id.textDescription)

        val movie = list[position]

        textTitle.text = movie.title
        textDescription.text = movie.description


        return view

    }
}