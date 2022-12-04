package com.braun.gustavo.estacionamento

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.braun.gustavo.estacionamento.databinding.RecyclerViewVeiculosEstacionadosBinding
import com.braun.gustavo.estacionamento.entity.Veiculo

class VeiculosEstacionadosViewHolder(private val binding: RecyclerViewVeiculosEstacionadosBinding) : RecyclerView.ViewHolder(binding.root) {

    fun fill(veiculo: Veiculo) {
        binding.textViewModelo.text = veiculo.modelo
        binding.textViewPlaca.text = veiculo.placa
        binding.imageViewVeiculo.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.moto))
    }
}