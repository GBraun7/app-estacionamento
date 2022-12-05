package com.braun.gustavo.estacionamento

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_CARRO
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_MOTO
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_VAN
import com.braun.gustavo.estacionamento.databinding.RecyclerViewVeiculosEstacionadosBinding
import com.braun.gustavo.estacionamento.entity.Veiculo

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
        setBtnRemove(veiculo)
    }

    private fun setBtnRemove(veiculo: Veiculo) {
        binding.buttonRemove.setOnClickListener {
            veiculo.removerVeiculo(itemView.context)
            BroadcastVeiculosEstacionados(itemView.context).emitiBroadcastVeiculoEstacionado()
        }
    }
}