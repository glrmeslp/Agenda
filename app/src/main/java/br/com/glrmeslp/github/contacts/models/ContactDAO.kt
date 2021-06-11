package br.com.glrmeslp.github.contacts.models

import androidx.room.*

@Dao
interface ContactDAO {

    @Query("SELECT * FROM contact")
    fun getAll(): MutableList<Contact>

    @Query("SELECT * FROM contact WHERE uid IN (:contactIds)")
    fun loadAllByIds(contactIds: IntArray): List<Contact>

    @Insert
    fun insertAll(vararg contact: Contact)

    @Update
    fun update(vararg contact: Contact)

    @Delete
    fun delete(contact: Contact)

}