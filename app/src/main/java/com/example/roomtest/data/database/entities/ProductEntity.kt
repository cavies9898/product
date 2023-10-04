package com.example.roomtest.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products_table")
data class ProductEntity(

    @PrimaryKey
    val sku: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "image")
    val image: String

) : Serializable