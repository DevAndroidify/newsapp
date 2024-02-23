package com.example.newsapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.newsapp.databinding.ActivityMainBinding
import kotlinx.coroutines.newSingleThreadContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adap:adapter
    private lateinit var binding:ActivityMainBinding
    private var page:Int = -1
    private var pageno:Int=1
    private lateinit var userlist:ArrayList<article>
    val apii=retrofithelper.getInstance().create(api::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
      var userlist= arrayListOf<article>()
          getnews()

        binding.recylerview.addOnScrollListener(object :OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val ll=recyclerView.layoutManager as LinearLayoutManager
                val last=ll.findFirstVisibleItemPosition()
                val item=ll.itemCount
                Log.d(TAG, "onScrolled: $last")
                if(page>item && last>=(item-5)){
                    pageno++
                    getnews()
                }
            }
        })


        }
    fun getnews(){




        val news=apii.getdata("us",pageno)
        news.enqueue(object :Callback<news> {
            override fun onResponse(call: Call<news>, response: Response<news>) {
                val new = response.body()
                if (new != null) {
                    page = new.totalResults

                    adap = adapter(this@MainActivity, new.articles)
                    binding.recylerview.adapter = adap
                    binding.recylerview.layoutManager = LinearLayoutManager(this@MainActivity)


                }
            }

            override fun onFailure(call: Call<news>, t: Throwable) {
                Toast.makeText(applicationContext, "error in fetching data", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


}

