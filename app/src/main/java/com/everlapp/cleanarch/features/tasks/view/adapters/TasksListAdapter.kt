package com.everlapp.cleanarch.features.tasks.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.everlapp.cleanarch.R
import com.everlapp.cleanarch.core.extension.inflate
import com.everlapp.cleanarch.core.navigation.Navigator
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import kotlinx.android.synthetic.main.row_task.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class TasksListAdapter
@Inject constructor() : RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {


    internal var collection: List<TaskData> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (TaskData, Navigator.Extras) -> Unit = { _, _ -> }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.row_task))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount(): Int = collection.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(taskData: TaskData, clickListener: (TaskData, Navigator.Extras) -> Unit) {
            itemView.taskName.text = taskData.name
            itemView.taskDate.text = taskData.createdAt.toString()
            itemView.setOnClickListener { clickListener(taskData, Navigator.Extras(itemView.taskName)) }
        }
    }

}