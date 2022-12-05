package com.braun.gustavo.estacionamento

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_CARRO
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_MOTO
import com.braun.gustavo.estacionamento.VeiculoConstants.VEICULO_TIPO_VAN
import com.braun.gustavo.estacionamento.databinding.ActivityMainBinding
import com.braun.gustavo.estacionamento.entity.Moto
import com.braun.gustavo.estacionamento.entity.Veiculo
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosDTO
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VeiculosEstacionadosAdapter
    private val mMyBroadCastReceiver = VeiculosEstacionadosBroadcastReceiver()

    // TODO: contador de vagas
    // TODO: Remover veiculo do estacionamento
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerBroadcast()
        setOnClickBtnMotorcycle()
        setOnClickBtnCar()
        setOnClickBtnVan()
        setupAdapter()
    }

    private fun setupAdapter() {
        setVacancyNumbers()
        val veiculo = VeiculosEstacionadosRepository(this).getVeiculosEstacionados()
        adapter = VeiculosEstacionadosAdapter(veiculo)
        binding.recyclerViewVeiculosEstacionados.adapter = adapter
        binding.recyclerViewVeiculosEstacionados.layoutManager = LinearLayoutManager(this)
    }

    private fun setOnClickBtnMotorcycle() {
        binding.constraintLayoutMoto.setOnClickListener {
            EstacionarVeiculoFragment(VEICULO_TIPO_MOTO).show(supportFragmentManager, EstacionarVeiculoFragment.FRAGMENT_TAG)
        }
    }

    private fun setOnClickBtnCar() {
        binding.constraintLayoutCarro.setOnClickListener {
            EstacionarVeiculoFragment(VEICULO_TIPO_CARRO).show(supportFragmentManager, EstacionarVeiculoFragment.FRAGMENT_TAG)
        }
    }

    private fun setOnClickBtnVan() {
        binding.constraintLayoutVan.setOnClickListener {
            EstacionarVeiculoFragment(VEICULO_TIPO_VAN).show(supportFragmentManager, EstacionarVeiculoFragment.FRAGMENT_TAG)
        }
    }

    private fun setVacancyNumbers() {
        val veiculosEstacionadosDTO = VeiculosEstacionadosRepository(this).getVeiculosEstacionadosDTO()
        binding.textViewVagasCarrosDisponveis.text = "Disponível: " + veiculosEstacionadosDTO.vagas_carro.toString()
        binding.textViewVagasCarrosOcupados.text = "Ocupadas: " + (5 - veiculosEstacionadosDTO.vagas_carro).toString()
        binding.textViewVagasMotoDisponiveis.text = "Disponível: " + veiculosEstacionadosDTO.vagas_carro.toString()
        binding.textViewVagasMotoOcupadas.text = "Ocupadas: " + (3 - veiculosEstacionadosDTO.vagas_carro).toString()
        binding.textViewVagasVanDisponiveis.text = "Disponível: " + veiculosEstacionadosDTO.vagas_carro.toString()
        binding.textViewVagasVanOcupadas.text = "Ocupadas: " + (1 - veiculosEstacionadosDTO.vagas_carro).toString()

    }

    inner class VeiculosEstacionadosBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(aContext: Context?, aIntent: Intent?) {
            try {
                when (aIntent?.action) {
                    BroadcastVeiculosEstacionados.veiculosEstacionados -> {
                        setupAdapter()
                    }
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }

    private fun registerBroadcast() {
        val lIntentFilter = IntentFilter()
        lIntentFilter.addAction(BroadcastVeiculosEstacionados.veiculosEstacionados)
        registerReceiver(mMyBroadCastReceiver, lIntentFilter)
    }


}