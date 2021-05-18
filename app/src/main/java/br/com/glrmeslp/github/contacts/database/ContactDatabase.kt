package br.com.glrmeslp.github.contacts.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.glrmeslp.github.contacts.models.Contact
import br.com.glrmeslp.github.contacts.models.ContactDAO

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO
}