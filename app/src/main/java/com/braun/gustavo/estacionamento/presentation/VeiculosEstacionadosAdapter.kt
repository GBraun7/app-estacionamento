package com.braun.gustavo.estacionamento.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braun.gustavo.estacionamento.databinding.RecyclerViewVeiculosEstacionadosBinding
import com.braun.gustavo.estacionamento.veiculo.Veiculo

class VeiculosEstacionadosAdapter(private val veiculo: List<Veiculo>) : RecyclerView.Adapter<VeiculosEstacionadosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiculosEstacionadosViewHolder {
        val binding = RecyclerViewVeiculosEstacionadosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VeiculosEstacionadosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VeiculosEstacionadosViewHolder, position: Int) {
        holder.fill(veiculo[position])
    }

    override fun getItemCount(): Int {
        return veiculo.size
    }
}