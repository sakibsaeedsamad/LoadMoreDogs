package com.sssakib.loadmoredogs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sssakib.loadmoredogs.model.DogBreed
import com.sssakib.loadmoredogs.model.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var count = 0

    private var dogBreedArrayList: ArrayList<DogBreed>? = null

    private var dogRVAdapter: DogRVAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogBreedArrayList = ArrayList()


        getData()


        idRVDogs.layoutManager = LinearLayoutManager(this)

        idNestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                count++

                idPBLoading.visibility = View.VISIBLE
                if (count < 10) {

                    getData()
                }
            }
        })
    }

    private fun getData() {

        val call: Call<List<DogBreed>?>? = RetrofitClient.RetrofitClient
            .instance
            ?.aPI
            ?.findAllDogs()
        call?.enqueue(object : Callback<List<DogBreed>?> {
            override fun onFailure(call: Call<List<DogBreed>?>?, t: Throwable) {
            }

            override fun onResponse(call: Call<List<DogBreed>?>?, response: Response<List<DogBreed>?>?) {
                if (response!!.isSuccessful) {
                    val list = response.body() as List
                    for (i in 0 until list.size) {
                        dogBreedArrayList!!.add(
                                DogBreed(list[i].name)
                        )

                        dogRVAdapter = DogRVAdapter(this@MainActivity, dogBreedArrayList!!)

                        idRVDogs.adapter = dogRVAdapter


                    }
                }
            }

        })

    }
}