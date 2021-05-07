package br.com.glrmeslp.github.contacts.ui.layouts

interface IContactActivityConstants {
    
    val keyContact: String
        get() = "Contact"

    val keyResultCodeContact: String
        get() = "ResultContact"

    val requestCodeContactActivityResult: Int
        get() = 1

    val resultCodeInvalid: Int
        get() = -1

    val resultCodeSave: Int
        get() = 1

    val resultCodeEdit: Int
        get() = 2

    val resultCodeRemove: Int
        get() = 3

}