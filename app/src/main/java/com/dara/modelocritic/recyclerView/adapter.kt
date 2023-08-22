package com.dara.modelocritic.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dara.modelocritic.Alternativa
import com.dara.modelocritic.Criterio
import com.dara.modelocritic.R

class TableAdapter(private val criterios: List<Criterio>, private val alternativas: List<Alternativa>, private val datos:Array<List<Any>>) :
    RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Aquí puedes encontrar las vistas individuales dentro de la fila
        val textViewAlternativa: TextView = itemView.findViewById(R.id.textViewAlternativa)
        val textViewCriterio1: TextView = itemView.findViewById(R.id.textViewCriterio1)
        // ... Agrega los TextViews para otros criterios aquí
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table_row, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val alternativa = alternativas[position]
        val data = datos[position]

        holder.textViewAlternativa.text = alternativa.nombre
        holder.textViewCriterio1.text = data[0].toString()
        // ... Establece los datos para otros criterios aquí
    }

    override fun getItemCount(): Int {
        return alternativas.size
    }
}