package com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.R
import com.coxtunes.kotlinstateflowhiltretrofit.model.Data
import com.squareup.picasso.Picasso


class CategoryAdapter(private val context: Context, private val categorylist: List<Data>?) :
    RecyclerView.Adapter<CategoryAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_category,
            parent,
            false
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val category = categorylist!![position]
        holder.name.text = category.cat_title

        if (!category.cat_image.isNullOrEmpty()) {
            Picasso.get().load("http://nexgen.tbltechnerds.xyz/uploads/" + category.cat_image)
                .error(R.drawable.ic_placeholder_asset).placeholder(
                    R.drawable.ic_placeholder_asset
                ).into(holder.image)
        }

    }

    override fun getItemCount(): Int {
        return categorylist?.size ?: 0
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val image: ImageView

        init {
            name = itemView.findViewById(R.id.name_cat)
            image = itemView.findViewById(R.id.image_cat)
        }
    }

}