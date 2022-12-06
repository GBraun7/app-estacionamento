package com.braun.gustavo.estacionamento.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.braun.gustavo.estacionamento.utils.BroadcastVeiculosEstacionados
import com.braun.gustavo.estacionamento.R
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_CARRO
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_MOTO
import com.braun.gustavo.estacionamento.utils.VeiculoConstants.VEICULO_TIPO_VAN
import com.braun.gustavo.estacionamento.databinding.FragmentEstacionarVeiculoBinding
import com.braun.gustavo.estacionamento.veiculo.Carro
import com.braun.gustavo.estacionamento.veiculo.Moto
import com.braun.gustavo.estacionamento.veiculo.Van
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class EstacionarVeiculoFragment(private val tipoVeiculo: String) : DialogFragment() {

    private lateinit var binding: FragmentEstacionarVeiculoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentEstacionarVeiculoBinding.inflate(layoutInflater)
        val alerDialog = MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertIndicationDialogRounded).setView(binding.root)
        setOnClickBtnEstacionar()
        setImageVeiculo()
        return alerDialog.create()
    }

    private fun setImageVeiculo() {
        when (tipoVeiculo) {
            VEICULO_TIPO_MOTO ->
                binding.imageVeiculo.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.moto))
            VEICULO_TIPO_CARRO ->
                binding.imageVeiculo.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.carro))
            VEICULO_TIPO_VAN ->
                binding.imageVeiculo.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.van))
        }
    }

    private fun setOnClickBtnEstacionar() {
        binding.buttonEstacionar.setOnClickListener {
            try {
                when (tipoVeiculo) {
                    VEICULO_TIPO_MOTO -> {
                        Moto().apply {
                            modelo = binding.editModelo.text.toString()
                            placa = binding.editPlaca.text.toString()
                            estacionarVeiculo(requireContext())
                        }
                    }
                    VEICULO_TIPO_CARRO -> {
                        Carro().apply {
                            modelo = binding.editModelo.text.toString()
                            placa = binding.editPlaca.text.toString()
                            estacionarVeiculo(requireContext())
                        }
                    }
                    VEICULO_TIPO_VAN -> {
                        Van().apply {
                            modelo = binding.editModelo.text.toString()
                            placa = binding.editPlaca.text.toString()
                            estacionarVeiculo(requireContext())
                        }
                    }
                }
                BroadcastVeiculosEstacionados(requireContext()).emitiBroadcastVeiculoEstacionado()
                dismiss()
            } catch (e: Exception) {
                AlertDialog.Builder(context)
                    .setTitle("Atenção")
                    .setMessage(e.message)
                    .show()
            }
        }
    }

    companion object {
        val FRAGMENT_TAG = "EstacionarVeiculoFragment"
    }
}