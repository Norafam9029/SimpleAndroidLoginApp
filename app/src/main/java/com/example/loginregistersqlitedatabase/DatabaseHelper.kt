package com.example.loginregistersqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import at.favre.lib.crypto.bcrypt.BCrypt

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tbl_user"

        private const val COL_USER_ID = "user_id"
        private const val COL_USER_NAME = "user_name"
        private val COL_USER_EMAIL = "user_email"
        private val COL_USER_PASSWORD = "user_password" // Store hashed password
    }

    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COL_USER_NAME TEXT, " +
            "$COL_USER_EMAIL TEXT UNIQUE, " +
            "$COL_USER_PASSWORD TEXT)" // Store hashed password

    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USER)
        onCreate(db)
    }
    fun registerUser(user: User): Boolean {
        if (isEmailExists(user.email)) {
            return false // Email already exists
        }

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_USER_NAME, user.username)
        values.put(COL_USER_EMAIL, user.email)
        values.put(COL_USER_PASSWORD, hashPassword(user.password))

        db.insert(TABLE_USER, null, values)
        db.close()
        return true
    }
    fun loginUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(COL_USER_PASSWORD)
        val selection = "$COL_USER_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null)

        val passwordHash: String? = if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COL_USER_PASSWORD)
            if (columnIndex != -1) {
                cursor.getString(columnIndex)
            } else {
                null // Column doesn't exist
            }
        } else {
            null // Cursor is empty
        }
        cursor.close()
        db.close()

        return passwordHash != null && BCrypt.verifyer().verify(password.toCharArray(), passwordHash).verified
    }
    private fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }
    private fun isEmailExists(email: String): Boolean {
        val db = this.readableDatabase
        val selection = "$COL_USER_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(TABLE_USER, null, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        db.close()

        return count > 0
    }
}

