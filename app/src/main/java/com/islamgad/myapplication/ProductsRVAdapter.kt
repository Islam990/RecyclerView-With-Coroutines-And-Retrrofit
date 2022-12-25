package com.islamgad.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.islamgad.myapplication.models.ProductItem
import com.squareup.picasso.Picasso

class ProductsRVAdapter(private val clickListener: (ProductItem) -> Unit) :
    RecyclerView.Adapter<ProductViewHolder>() {

    private val productsArrayList = ArrayList<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.products_list_item, parent, false)
        return ProductViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productsArrayList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return productsArrayList.size
    }

    fun setProducts(list: List<ProductItem>) {
        productsArrayList.clear()
        productsArrayList.addAll(list)
    }

}


class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(productItem: ProductItem, clickListener: (ProductItem) -> Unit) {
        val titleTextView = itemView.findViewById<TextView>(R.id.tvProductName)
        val priceTextView = itemView.findViewById<TextView>(R.id.tvProductPrice)
        val productImageView = itemView.findViewById<ImageView>(R.id.ivProduct)

        titleTextView.text = productItem.title
        "Price: ${productItem.price}".also { priceTextView.text = it }
        Picasso.get().load(productItem.image).into(productImageView)

        productImageView.setOnClickListener {
            clickListener(productItem)
        }


    }
}