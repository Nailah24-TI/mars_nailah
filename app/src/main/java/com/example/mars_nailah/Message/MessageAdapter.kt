
package com.example.mars_nailah.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.mars_nailah.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val binding = ItemMessageBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        val view = binding.root
        val data = messages[position]

        Glide.with(binding.avatarImg)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textAge.text = "${data.age} tahun"
        binding.textMessage.text = data.messageText

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
