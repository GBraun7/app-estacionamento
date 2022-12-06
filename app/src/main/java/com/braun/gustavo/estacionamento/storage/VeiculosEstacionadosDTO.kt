package com.braun.gustavo.estacionamento.storage

import com.braun.gustavo.estacionamento.entity.Carro
import com.braun.gustavo.estacionamento.entity.Moto
import com.braun.gustavo.estacionamento.entity.Van

data class VeiculosEstacionadosDTO(
    var vagas_carro: Int = 5,
    var vagas_moto: Int = 3,
    var vagas_grande: Int = 1,
    val carros_estacionados: ArrayList<Carro> = ArrayList(),
    val motos_estacionadas: ArrayList<Moto> = ArrayList(),
    val vans_estacionadas: ArrayList<Van> = ArrayList(),
)