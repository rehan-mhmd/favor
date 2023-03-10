package com.fyp.favorproject.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyp.favorproject.R
import com.fyp.favorproject.databinding.ChatRowItemBinding
import com.fyp.favorproject.mainFragment.ChatFragment
import com.fyp.favorproject.mainFragment.ChattingActivity
import com.fyp.favorproject.model.Chats
import com.squareup.picasso.Picasso
import java.util.*

class UsersAdapter(
    val context: Context,
    private val usersArrayList: ArrayList<Chats>,
    private val parentFrag: ChatFragment
) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ChatRowItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_row_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = usersArrayList[position]
        @Suppress("DEPRECATION")
        val date = "${Date(usersArrayList[position].lastMessageTime).toLocaleString().subSequence(0, 11)} "

        holder.binding.tvUsernameChatsitem.text = item.friendName
        holder.binding.tvLastmessaageChatsitem.text = item.lastMessage
        holder.binding.tvTimestampChatitem.text = date

        try {

            Picasso.get().load(item.friendProfileURI).placeholder(R.drawable.avataar)
                .into(holder.binding.ivProfilePicChatsitem)

        }catch (e:Exception){
            Log.d("qqq", "onBindViewHolder: ${e.message}")
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChattingActivity::class.java)
            intent.putExtra("name", item.friendName)
            intent.putExtra("picture", item.friendProfileURI)
            intent.putExtra("friendUID", item.friendUID)
            Log.d("UUSSEERR", "onBindViewHolder:  ${item.friendUID}")
            parentFrag.requireContext().startActivity(intent)

        }
    }

    override fun getItemCount(): Int = usersArrayList.size
}
