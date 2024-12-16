package com.example.teeest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "user_database", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE,
                password TEXT
            )
        """
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun registerUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val query = "INSERT INTO users (username, password) VALUES (?, ?)"
        return try {
            db.execSQL(query, arrayOf(username, password))
            true
        } catch (e: Exception) {
            false
        }
    }

    fun authenticateUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM users WHERE username = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val isAuthenticated = cursor.count > 0
        cursor.close()
        return isAuthenticated
    }
}
