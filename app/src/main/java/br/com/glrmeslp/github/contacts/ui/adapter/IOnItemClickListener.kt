package br.com.glrmeslp.github.contacts.ui.adapter

import br.com.glrmeslp.github.contacts.models.Contact

interface IOnItemClickListener {

    fun onItemClick(contact: Contact)
}