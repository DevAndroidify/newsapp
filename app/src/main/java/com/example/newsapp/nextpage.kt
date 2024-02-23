package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.databinding.ActivityNextpageBinding

class nextpage : AppCompatActivity() {
    private lateinit var binding: ActivityNextpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_nextpage)

        val a=intent.getStringExtra("title")
        val b=intent.getStringExtra("description")
        val c=intent.getStringExtra("image")
        binding.title.text="Title:"+a
        binding.description.text="Description:"+b
        Glide.with(applicationContext).load(c).into(binding.image)


    }
}