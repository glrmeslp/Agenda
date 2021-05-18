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

//    companion object {
//        var contacts: MutableList<Contact> = mutableListOf()
//            private set
//            //POR QUE NAO CONSIGO MUDAR O GET
//        var contId = 1
//            private set
//    }
//
//    fun saves(contact: Contact){
//        contact.id = contId
//        contacts.add(contact)
//        contId++
//    }
//
//    fun edit(contactEdit: Contact){
//        val index = indexContactEdit(contactEdit.id)
//        if (index != -1){
//            contacts[index] = contactEdit
//        }
//    }
//
//    fun remove(contactRemoveId: Int){
//        val contactRemove: Contact = returnContactById(contactRemoveId)
//        contacts.remove(contactRemove)
//    }
//
//    private fun returnContactById(contactId: Int) : Contact {
//        for(contact in contacts){
//            if (contact.id == contactId){
//                return contact
//            }
//        }
//        return Contact("","","","")
//    }
//    private fun indexContactEdit(contactId: Int) : Int{
//        for(contact in contacts){
//            if (contact.id == contactId){
//                return contacts.indexOf(contact)
//            }
//        }
//        return -1
//    }
}