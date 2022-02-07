package com.pdinc.flickr.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pdinc.flickr.R
import com.pdinc.flickr.model.PhotoItem

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var result:List<PhotoItem?>? =ArrayList()
    inner class PhotoViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
     fun bind(item:PhotoItem)=with(itemView){
//         Log.d("Here is the url", item.urlS!!)
         Glide.with(context).load(item.urlS).into(itemView.findViewById(R.id.photoimgV))
     }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun swapData(result:List<PhotoItem?>?){
        if (result != null) {
            this.result= result
        }
//        notifyItemRangeInserted(0,result!!.size)
//        notifyItemRangeChanged(0,result.size)
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