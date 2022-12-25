package com.islamgad.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.islamgad.myapplication.databinding.ActivityProductDetailsBinding
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)

        val intent = this.intent
        binding.tvTitle.text = "Title : ${intent.getStringExtra("PRD_TITLE")}"
        binding.tvDesp.text = "Description : ${intent.getStringExtra("PRD_DESCRIPTION")}"
        binding.tvprice.text = "Price : ${intent.getDoubleExtra("PRD_PRICE", 0.0)}"
        binding.tvCount.text = "Count : ${intent.getIntExtra("PRD_RATING_COUNT", 0)}"
        binding.tvRate.text = "Rate : ${intent.getDoubleExtra("PRD_RATING.RATE", 0.0)} / 5 "

        Picasso.get().load(intent.getStringExtra("PRD_IMAGE")).into(binding.imageView)


    }
}