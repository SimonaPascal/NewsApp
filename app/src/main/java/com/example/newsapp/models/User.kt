package com.example.newsapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class User(var name:String, var emailAddress: String, var password: String, var phone: String, var gender: String): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
    override fun toString(): String {
        return "User(name='$name', emailAddress='$emailAddress', password='$password', phone='$phone', gender='$gender', id=$id)"
    }


}