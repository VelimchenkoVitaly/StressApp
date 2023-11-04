package com.example.stressapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {

        private val DATABASE_NAME = "SAN_SAN"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "San_table"

        val ID_COL = "id"

        val MOOD = "Настроение"

        val ACTIVE = "Активность"

        val WELL_BEING = "Самочувствие"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WELL_BEING + " INTEGER, " +
                ACTIVE + " INTEGER, " +
                MOOD + " INTEGER"
                + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("Таблица уже существует " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(dataWellbeing: Int, dataActivity: Int, dataMood: Int) {

        val values = ContentValues()
        //values.put(DAY_OF_WEEK, dataDayOfWeek.toString())
        values.put(MOOD, dataMood)
        values.put(WELL_BEING, dataWellbeing)
        values.put(ACTIVE, dataActivity)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    @SuppressLint("Range")
    fun retreive(): List<UserModel> {
        val userList: ArrayList<UserModel> = ArrayList<UserModel>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val ourDB = this.readableDatabase
        var ourCursor: Cursor?
        try {
            ourCursor = ourDB.rawQuery(selectQuery + " ORDER BY $ID_COL DESC LIMIT 7 ", null)
            // ourCursor =ourDB.query()
        } catch (e: SQLiteException) {
            ourDB.execSQL(selectQuery)
            return ArrayList()
        }

        var userWellbeingReturned: Int
        var userActivityReturned: Int
        var userMoodReturned: Int
        //  var iDReturned: Int
        //fetch all the records until all are finished
        if (ourCursor.moveToFirst()) {
            do {

                userWellbeingReturned = ourCursor.getInt(ourCursor.getColumnIndex("Самочувствие"))
                userActivityReturned = ourCursor.getInt(ourCursor.getColumnIndex("Активность"))
                userMoodReturned = ourCursor.getInt(ourCursor.getColumnIndex("Настроение"))
                //  iDReturned = ourCursor.getInt(ourCursor.getColumnIndex("id"))


                //add the values to the Model class and later to the arraylist
                val userRow = UserModel(
                    userWellbeing = userWellbeingReturned,
                    userActive = userActivityReturned,
                    userMood = userMoodReturned
                )
                userList.add(userRow)
            } while (ourCursor.moveToNext())
        }
        return userList
    }

}