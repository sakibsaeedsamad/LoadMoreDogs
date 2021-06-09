package com.sssakib.loadmoredogs


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sssakib.loadmoredogs.model.DogBreed
import kotlinx.android.synthetic.main.dog_rv_item.view.*


class DogRVAdapter(context: Context, dogBreedArrayList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogRVAdapter.ViewHolder>() {

    private val context: Context
    private val dogBreedArrayList: ArrayList<DogBreed>

    init {
        this.context = context
        this.dogBreedArrayList = dogBreedArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogRVAdapter.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.dog_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DogRVAdapter.ViewHolder, position: Int) {

        val courses = dogBreedArrayList[position]
        holder.dogNameTV.text = courses.name
    }

    override fun getItemCount(): Int {
        return dogBreedArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dogNameTV: TextView = itemView.idTVDogName

    }

}