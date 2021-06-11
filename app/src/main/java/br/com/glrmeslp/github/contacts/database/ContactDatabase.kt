package br.com.glrmeslp.github.contacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.glrmeslp.github.contacts.models.Contact
import br.com.glrmeslp.github.contacts.models.ContactDAO

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO


    fun getInstance(context: Context): ContactDatabase{
        return Room.databaseBuilder(context,
            ContactDatabase::class.java,
            "database-contact"
        ).allowMainThreadQueries().build()
    }

}