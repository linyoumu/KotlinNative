package com.example.kotlinnative.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinnative.R
import com.example.kotlinnative.databinding.CommentDialogBinding

object CommentDialog {
    private lateinit var binding: CommentDialogBinding
    fun show(context: Context) {

        Dialog(context).apply {
            binding = CommentDialogBinding.inflate(LayoutInflater.from(context), null, false);
            setContentView(binding.root)

            binding.commentList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CommentAdapter()
            }

            binding.likeList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = LikeAdapter()
            }

            binding.commentCount.setOnClickListener {
                binding.commentList.visibility = View.VISIBLE
                binding.likeList.visibility = View.GONE
            }

            binding.likeCount.setOnClickListener {
                binding.likeList.visibility = View.VISIBLE
                binding.commentList.visibility = View.GONE
            }

            window?.attributes = window?.attributes?.apply {
                gravity = Gravity.BOTTOM
                height = 1278
                width = WindowManager.LayoutParams.MATCH_PARENT
            }
            window?.setBackgroundDrawable(null)
            show()
        }
    }
}