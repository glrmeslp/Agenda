package br.com.glrmeslp.github.contacts.ui.layouts

interface IContactActivityConstants {
    val keyContact: String
        get() = "Contact"

    val requestCodeContactActivityResult: Int
        get() = 1

    val resultCodeSaveOrEdit: Int
        get() = 2

    val resultCodeRemove: Int
        get() = 3
}