package com.rubahapi.moviedb.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rubahapi.moviedb.R
import com.rubahapi.moviedb.model.TvShow

class TvShowAdapter(private val context: Context):
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>(){

    private val mData:ArrayList<TvShow> = arrayListOf()

    fun setData(items:ArrayList<TvShow>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TvShowViewHolder(LayoutInflater.from(context).inflate(
        R.layout.list_item_tv_show, parent, false))

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) = holder.bindItem(context, mData[position])


    class TvShowViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.listview_item_title)
        private val description = view.findViewById<TextView>(R.id.listview_item_short_description)
        private val imagePath = view.findViewById<ImageView>(R.id.image_logo)

        fun bindItem(context: Context, items: TvShow){
            name.text = items.name
            description.text = items.overview
            Glide.with(context).load("https://image.tmdb.org/t/p/w370_and_h556_bestv2${items.poster_path}")
                .into(imagePath)
//            itemView.setOnClickListener{listener(items)}
        }
    }

}