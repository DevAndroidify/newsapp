package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class adapter(val context:Context,private var articles:List<article>):RecyclerView.Adapter<adapter.ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val image=itemView.findViewById<ImageView>(R.id.newsimage)
        val newstitle=itemView.findViewById<TextView>(R.id.newsTitle)
        val newsdescription=itemView.findViewById<TextView>(R.id.newsdescription)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val a=LayoutInflater.from(context).inflate(R.layout.listdata,parent,false)
        return ViewHolder(a)
;    }


    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article=articles[position]
        holder.newstitle.text=article.title
        holder.newsdescription.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.image)

        holder.itemView.setOnClickListener {
            val a =Intent(context,nextpage::class.java)

            a.putExtra("title",article.title)
            a.putExtra("description",article.description)
            a.putExtra("image",article.urlToImage)
            context.startActivity(a)
        }

    }

}