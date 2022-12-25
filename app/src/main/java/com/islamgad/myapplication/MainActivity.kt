package com.islamgad.myapplication

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.islamgad.myapplication.RetrofitHandling.ProductService
import com.islamgad.myapplication.RetrofitHandling.RetrofitInstance
import com.islamgad.myapplication.databinding.ActivityMainBinding
import com.islamgad.myapplication.models.Product
import com.islamgad.myapplication.models.ProductItem
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productsRVAdapter: ProductsRVAdapter
    private lateinit var retrofitInstance: ProductService
    private lateinit var productResponse: LiveData<Response<Product>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Retrofit Instance
        retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ProductService::class.java)

        // Get Request
        productResponse = liveData {
            val response: Response<Product> = retrofitInstance.getProducts()
            emit(response)
        }

        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        productsRVAdapter = ProductsRVAdapter {
            clickListener(it)
        }
        binding.rvProducts.adapter = productsRVAdapter

        displayData()
    }

    private fun displayData() {

        productResponse.observe(this, Observer {
            val productsList: MutableListIterator<ProductItem>? = it.body()?.listIterator()
            if (productsList != null) {
                productsRVAdapter.setProducts(productsList.asSequence().toList())
                productsRVAdapter.notifyDataSetChanged()
                binding.pbLoading.visibility = View.GONE
            }
        })
    }

    private fun clickListener(productItem: ProductItem) {
        val detailsActivity = Intent(this, ProductDetailsActivity::class.java)
        detailsActivity.putExtra("PRD_TITLE", productItem.title)
        detailsActivity.putExtra("PRD_DESCRIPTION", productItem.description)
        detailsActivity.putExtra("PRD_IMAGE", productItem.image)
        detailsActivity.putExtra("PRD_PRICE", productItem.price)
        detailsActivity.putExtra("PRD_RATING_COUNT", productItem.rating.count)
        detailsActivity.putExtra("PRD_RATING.RATE", productItem.rating.rate)
        startActivity(detailsActivity)
    }

}