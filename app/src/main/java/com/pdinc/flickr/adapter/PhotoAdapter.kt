package com.pdinc.flickr.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pdinc.flickr.R
import com.pdinc.flickr.model.PhotoItem

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var result:MutableList<PhotoItem?>? =ArrayList()
    inner class PhotoViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        var imageData=itemView.findViewById<TextView>(R.id.imginftv)
     fun bind(item:PhotoItem)=with(itemView){
         imageData.text=item.title
         Glide.with(context).load(item.urlS).into(itemView.findViewById(R.id.photoimgV))
     }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun swapData(result:MutableList<PhotoItem?>?){
        if (result != null) {
            this.result= result
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        result!!.get(position)!!.let { holder.bind(it) }
    }

    override fun getItemCount(): Int =result!!.size
}