package com.braun.gustavo.estacionamento

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.braun.gustavo.estacionamento.databinding.RecyclerViewVeiculosEstacionadosBinding
import com.braun.gustavo.estacionamento.entity.Veiculo

class VeiculosEstacionadosViewHolder(private val binding: RecyclerViewVeiculosEstacionadosBinding) : RecyclerView.ViewHolder(binding.root) {

    fun fill(veiculo: Veiculo) {
        val infoVeiculo = veiculo.modelo + " placa: " + veiculo.placa
        binding.textViewModelo.text = infoVeiculo
        binding.imageViewVeiculo.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.moto))
    }
}