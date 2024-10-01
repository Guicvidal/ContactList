package br.edu.scl.ifsp.sdm.contactlist.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.RenderProcessGoneDetail
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.ActivityContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.sdm.contactlist.model.Constant.EXTRA_VIEW_CONTACT
import br.edu.scl.ifsp.sdm.contactlist.model.Contact

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
      ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_details)

        val receivedContact = intent.getParcelableExtra<Contact>(EXTRA_CONTACT)
        receivedContact?.let { received ->
            val viewContact = intent.getBooleanExtra(EXTRA_VIEW_CONTACT, false)
            with(acb) {
                if(viewContact) {
                    nameEt.isEnabled = false
                    adressEt.isEnabled = false
                    phoneEt.isEnabled = false
                    emailEt.isEnabled = false
                    saveBt.visibility = View.GONE


                }
                nameEt.setText(received.name)
                adressEt.setText(received.address)
                phoneEt.setText(received.phone)
                emailEt.setText(received.email)
            }
        }

        with(acb) {
            saveBt.setOnClickListener {
                 val contact = Contact(
                     id = receivedContact?.id?:hashCode(),
                     name = nameEt.text.toString(),
                     address = adressEt.text.toString(),
                     phone = phoneEt.text.toString(),
                     email = emailEt.text.toString()
                 )

                 val resultIntent = Intent()
                 resultIntent.putExtra(EXTRA_CONTACT, contact)
                 setResult(RESULT_OK, resultIntent)
                 finish()
            }
        }
    }
}
