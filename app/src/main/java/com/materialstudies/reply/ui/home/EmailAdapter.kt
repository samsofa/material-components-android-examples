package com.materialstudies.reply.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.materialstudies.reply.R
import com.materialstudies.reply.data.Email

/**
 * Simple adapter to display Email's in MainActivity.
 */
class EmailAdapter(
        private val listener: EmailAdapterListener
) : ListAdapter<Email, EmailViewHolder>(DIFF_CALLBACK) {

    interface EmailAdapterListener {
        fun onEmailClicked(email: Email)
        fun onEmailLongPressed(email: Email): Boolean
        fun onEmailStarChanged(email: Email, newValue: Boolean)
        fun onEmailArchived(email: Email)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Email>() {
            override fun areItemsTheSame(oldItem: Email, newItem: Email): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Email, newItem: Email): Boolean {
                return oldItem.sender == newItem.sender &&
                    oldItem.subject == newItem.subject &&
                    oldItem.body == newItem.body &&
                    oldItem.senderImg == newItem.senderImg &&
                    oldItem.isStarred == newItem.isStarred
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        return EmailViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.email_item_layout, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}