package br.com.glrmeslp.github.contacts.ui.layouts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.glrmeslp.github.contacts.R
import br.com.glrmeslp.github.contacts.database.ContactDatabase
import br.com.glrmeslp.github.contacts.models.Contact
import br.com.glrmeslp.github.contacts.models.ContactDAO
import br.com.glrmeslp.github.contacts.ui.adapter.ContactAdapter
import br.com.glrmeslp.github.contacts.ui.adapter.IOnItemClickListener

class MainActivity : AppCompatActivity(), IContactActivityConstants {

    private lateinit var listOfContacts: RecyclerView
    private lateinit var adapter: ContactAdapter
    private lateinit var dao: ContactDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "database-contact"
        ).allowMainThreadQueries().build()
        dao = db.contactDao()

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_top_bar))
        configureRecyclerView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_list_contact_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.list_contact_menu_add) {
            openContactForm(createIntentFormContact())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openContactForm(intent: Intent) {
        startActivityForResult(intent, requestCodeContactActivityResult)
    }

    private fun createIntentFormContact() = Intent(
        this@MainActivity,
        FormContactsActivity::class.java
    )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (isContactResult(requestCode, data)) {
            if (resultCode == Activity.RESULT_OK) {
                val contact: Contact = data!!.getParcelableExtra(keyContact)!!
                when (data.getIntExtra(keyResultCodeContact, resultCodeInvalid)) {
                    resultCodeSave -> {
                        dao.insertAll(contact)
                        adapter.insert(contact)
                    }
                    resultCodeEdit -> {
                        dao.update(contact)
                        adapter.update(contact)
                    }
                    resultCodeRemove -> {
                        dao.delete(contact)
                        adapter.delete(contact)
                    }
                }
                adapter.actualize()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun isContactResult(
        requestCode: Int,
        data: Intent?
    ): Boolean {
        return if (data == null) {
            false
        } else requestCode == requestCodeContactActivityResult &&
                data.hasExtra(keyContact)
    }

    private fun configureRecyclerView() {
        listOfContacts = findViewById(R.id.rv_contacts)
        configureAdapter()
    }

    private fun configureAdapter() {
        adapter = ContactAdapter(dao.getAll())
        listOfContacts.adapter = adapter
        adapter.onItemClickListener = object : IOnItemClickListener {
            override fun onItemClick(contact: Contact) {
                openContactForm(createIntentFormContact().putExtra(keyContact, contact))
            }
        }
    }

}