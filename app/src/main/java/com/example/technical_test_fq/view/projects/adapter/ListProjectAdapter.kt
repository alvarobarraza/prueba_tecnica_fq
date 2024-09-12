package com.example.technical_test_fq.view.projects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technical_test_fq.data.model.ResultProjectApi
import com.example.technical_test_fq.databinding.ItemProjectBinding

class ListProjectAdapter(
    private val listProjects: List<ResultProjectApi>,
    private val listener: OnProjectsClickListener,
    private val listenerDelete: OnProjectsDeleteClick
): RecyclerView.Adapter<ListProjectAdapter.ViewHolder>() {

    interface OnProjectsClickListener {
        fun onProjectClick(project: ResultProjectApi)
    }

    interface OnProjectsDeleteClick {
        fun onProjectsDelete(project: ResultProjectApi)
    }

    inner class ViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProjects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listProjects[position]

        with(holder.binding){
            tvName.text = data.name
            tvType.text = data.type
            tvStatus.text = "Estado: ${data.status}"
            tvDateCreated.text = "Fecha de creación: 11/09/2024"
            tvPrice.text = "$ ${data.value}"
            tvDescription.text = data.description

            cardView.setOnClickListener {
                listener.onProjectClick(data)
            }

            cardView.setOnLongClickListener {
                listenerDelete.onProjectsDelete(data)
                true
            }

        }

    }

}