package com.braun.gustavo.estacionamento.presentation

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.braun.gustavo.estacionamento.utils.BroadcastVeiculosEstacionados
import com.braun.gustavo.estacionamento.R
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_CARRO
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_MOTO
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_VAN
import com.braun.gustavo.estacionamento.databinding.RecyclerViewVeiculosEstacionadosBinding
import com.braun.gustavo.estacionamento.veiculo.Veiculo

class VeiculosEstacionadosViewHolder(private val binding: RecyclerViewVeiculosEstacionadosBinding) : RecyclerView.ViewHolder(binding.root) {

    fun fill(veiculo: Veiculo) {
        val infoVeiculo = veiculo.modelo + " placa: " + veiculo.placa
        binding.textViewModelo.text = infoVeiculo
        when (veiculo.tipoVeiculo()) {
            VEICULO_TIPO_MOTO ->
                binding.imageViewVeiculo.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.moto))
            VEICULO_TIPO_CARRO ->
                binding.imageViewVeiculo.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.carro))
            VEICULO_TIPO_VAN ->
                binding.imageViewVeiculo.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.van))
        }
        setClickBtnRemove(veiculo)
    }

    private fun setClickBtnRemove(veiculo: Veiculo) {
        binding.buttonRemove.setOnClickListener {
            veiculo.removerVeiculo(itemView.context)
            BroadcastVeiculosEstacionados(itemView.context).emitiBroadcastVeiculoEstacionado()
        }
    }
}