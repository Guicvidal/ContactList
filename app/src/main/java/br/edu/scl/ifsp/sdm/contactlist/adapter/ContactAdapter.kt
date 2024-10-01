package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact

class ContactAdapter(context: Context, private val contactList: MutableList<Contact>) :
    ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = contactList[position]

        var contactTileView = convertView
        if (contactTileView == null) {
            val tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )
            contactTileView = tcb.root

            val tileContactHoler = TileContactHoler(tcb.nameTv, tcb.emailTv)
            contactTileView.tag = tileContactHoler
        }
        val holder = contactTileView.tag as TileContactHoler
        holder.nameTv.text = contact.name
        holder.emailTv.text = contact.email

        return contactTileView
    }

    private  data class TileContactHoler(val nameTv: TextView, val emailTv: TextView)
}