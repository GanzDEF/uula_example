package dev.aisdev.example.ui.lessonslist.viewHolders

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import dev.aisdev.example.BuildConfig
import dev.aisdev.example.R
import dev.aisdev.example.entities.lesson.LessonVideo
import dev.aisdev.example.extentions.toFormattedTimeString
import dev.aisdev.example.extentions.visible
import dev.aisdev.example.ui.base.adapters.BaseMultiTypeAdapter
import dev.aisdev.example.ui.lessonslist.LessonsAdapter
import kotlinx.android.synthetic.main.view_lesson_video_detail.view.*

class VideoItemViewHolder(
    itemView: View,
    private val listener: LessonsAdapter.LessonsListener
) : BaseMultiTypeAdapter.ViewHolder<Any>(itemView){

    override fun bindView(item: Any) = (item as LessonVideo).let {
        itemView.run {
            clVideoHolder.visibility = View.VISIBLE
            setOnClickListener { listener.onVideoClicked(it.id.toString()) }
            Glide.with(itemView.context).load(it.images?.medium).into(ivVideoPreview)
            tvLessonTitle.text = it.title
            ivIconContent.setImageDrawable(context.getDrawable(R.drawable.reply_icon))
            ivIconContent.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.colorTextGrey), PorterDuff.Mode.SRC_ATOP)
            tvCountContentItems.text = String.format("%s comments", it.comments_count.toString())
            tvLenghtTotal.text = (it.duration?.toInt()?.toLong()?.toFormattedTimeString("mm:ss"))
            ivIsVisited.visible(it.visited)
        }
    }

}
