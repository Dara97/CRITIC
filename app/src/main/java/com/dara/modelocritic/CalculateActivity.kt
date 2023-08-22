package com.dara.modelocritic

import com.dara.modelocritic.recyclerView.TableAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dara.modelocritic.databinding.ActivityCalculateBinding


class CalculateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alternativas = intent.getParcelableArrayListExtra<Alternativa>("ALTERNATIVAS") as List<Alternativa>
        val criterios = intent.getParcelableArrayListExtra<Criterio>("CRITERIOS") as List<Criterio>
        val datos = intent.getSerializableExtra("DATOS") as Array<List<Any>>

        val recyclerView = binding.recyclerView // Aquí debes usar el ID correcto del RecyclerView en tu layout
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = TableAdapter(criterios, alternativas, datos)
        recyclerView.adapter = adapter

    }
}