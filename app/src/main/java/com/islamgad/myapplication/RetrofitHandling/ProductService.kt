package com.islamgad.myapplication.RetrofitHandling

import com.islamgad.myapplication.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET(value = "/products")
    suspend fun getProducts(): Response<Product>
}