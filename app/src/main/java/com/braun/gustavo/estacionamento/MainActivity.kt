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
import com.braun.gustavo.estacionamento.storage.VeiculosEstacionadosRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VeiculosEstacionadosAdapter
    private val mMyBroadCastReceiver = VeiculosEstacionadosBroadcastReceiver()
    private var vagasCarro = 10
    private var vagasMoto = 5
    private var vagasVan = 3

    // TODO: fazer o tipo da vaga: grande, carro, moto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerBroadcast()
        setOnClickBtnMotorcycle()
        setOnClickBtnCar()
        setOnClickBtnVan()

//        val veiculo: ArrayList<Veiculo> = ArrayList()
//        veiculo.add(Carro(this).apply {
//            modelo = "GOl"
//            placa = "AWS123"
//        })



        setupAdapter()
    }

    private fun setupAdapter() {
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

    inner class VeiculosEstacionadosBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(aContext: Context?, aIntent: Intent?) {
            try {
                when (aIntent?.action) {
                    BroadcastVeiculosEstacionados.veiculosEstacionados -> {
//                        adapter.notifyDataSetChanged()
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