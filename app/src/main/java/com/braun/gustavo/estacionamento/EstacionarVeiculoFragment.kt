package com.braun.gustavo.estacionamento

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_CARRO
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_MOTO
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_VAN
import com.braun.gustavo.estacionamento.databinding.FragmentEstacionarVeiculoBinding
import com.braun.gustavo.estacionamento.entity.Carro
import com.braun.gustavo.estacionamento.entity.Moto
import com.braun.gustavo.estacionamento.entity.Van
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EstacionarVeiculoFragment(private val tipoVeiculo: String) : DialogFragment() {

    private lateinit var binding: FragmentEstacionarVeiculoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentEstacionarVeiculoBinding.inflate(LayoutInflater.from(requireContext()))
        val alerDialog = MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertIndicationDialogRounded).setView(binding.root)
        setOnClickBtnEstacionar()
        return alerDialog.create()
    }

    private fun setOnClickBtnEstacionar() {
        binding.buttonEstacionar.setOnClickListener {
            val veiculosEstacionados = VeiculosEstacionadosRepository(requireContext()).getVeiculosEstacionadosDTO()
            when (tipoVeiculo) {
                VEICULO_TIPO_MOTO -> {
                    val moto = Moto(requireContext()).apply {
                        modelo = binding.editModelo.text.toString()
                        placa = binding.editPlaca.text.toString()
                        estacionarVeiculo()
                    }
                }
                VEICULO_TIPO_CARRO -> {
                    Carro(requireContext()).apply {
                        modelo = binding.editModelo.text.toString()
                        placa = binding.editPlaca.text.toString()
                        estacionarVeiculo()
                    }
                }
                VEICULO_TIPO_VAN -> {
                    Van(requireContext()).apply {
                        modelo = binding.editModelo.text.toString()
                        placa = binding.editPlaca.text.toString()
                        estacionarVeiculo()
                    }
                }
            }
            BroadcastVeiculosEstacionados(requireContext()).emitiBroadcastVeiculoEstacionado()
            dismiss()
        }
    }

    companion object {
        val FRAGMENT_TAG = "EstacionarVeiculoFragment"
    }
}