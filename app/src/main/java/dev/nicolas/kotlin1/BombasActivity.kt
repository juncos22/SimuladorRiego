package dev.nicolas.kotlin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class BombasActivity : AppCompatActivity() {
    private lateinit var nivelBar: ProgressBar
    private var progreso: Int = 0
    private lateinit var nivelTipo: TextView
    private lateinit var nivelNum: TextView
    private lateinit var estadoV1: TextView
    private lateinit var estadoV2: TextView
    private lateinit var estadoV3: TextView
    private lateinit var estadoTipo: TextView
    private var countBombas: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bombas)

        nivelBar = findViewById(R.id.nivel_bar)
        nivelNum = findViewById(R.id.nivel_num)
        nivelTipo = findViewById(R.id.nivel_tipo)
        estadoV1 = findViewById(R.id.estado_v1)
        estadoV2 = findViewById(R.id.estado_v2)
        estadoV3 = findViewById(R.id.estado_v3)
        estadoTipo = findViewById(R.id.estado_tipo)

        progreso = nivelBar.progress
        nivelNum.text = "$progreso %"
        estadoTipo.text = resources.getText(R.string.normal)
    }

    fun setLevel(view: View) {
        when (view.id) {
            R.id.btn_aumentar -> aumentarNivel()
            R.id.btn_disminuir -> disminuirNivel()
        }
    }

    private fun aumentarNivel() {
        if (progreso < 100) {
            progreso++
        }else {
            progreso--
        }
        nivelBar.progress = progreso
        nivelNum.text = "$progreso %"
        mostrarNivel(progreso)
    }

    private fun disminuirNivel() {
        if (progreso > 0) {
            progreso--
        }else {
            progreso++
        }
        nivelBar.progress = progreso
        nivelNum.text = "$progreso %"
        mostrarNivel(progreso)
    }

    fun mostrarNivel(progreso: Int) {
        when {
            progreso < 50 -> {
                nivelTipo.text = resources.getText(R.string.bajo)
            }
            progreso in 50..80 -> {
                nivelTipo.text = resources.getText(R.string.medio)
            }
            else -> {
                nivelTipo.text = resources.getText(R.string.alto)
            }
        }
    }

    fun activarBombas(view: View) {
        when (view.id) {
            R.id.bomba1 -> {
                if (estadoV1.text == resources.getText(R.string.activada)) {
                    estadoV1.text = resources.getText(R.string.desactivada)
                    if (countBombas > 0) {
                        countBombas--
                    }
                }else {
                    estadoV1.text = resources.getText(R.string.activada)
                    if (countBombas < 3) {
                        countBombas++
                    }
                }
            }
            R.id.bomba2 -> {
                if (estadoV2.text == resources.getText(R.string.activada)) {
                    estadoV2.text = resources.getText(R.string.desactivada)
                    if (countBombas > 0) {
                        countBombas--
                    }
                }else {
                    estadoV2.text = resources.getText(R.string.activada)
                    if (countBombas < 3) {
                        countBombas++
                    }
                }
            }
            R.id.bomba3 -> {
                if (estadoV3.text == resources.getText(R.string.activada)) {
                    estadoV3.text = resources.getText(R.string.desactivada)
                    if (countBombas > 0) {
                        countBombas--
                    }
                }else {
                    estadoV3.text = resources.getText(R.string.activada)
                    if (countBombas < 3) {
                        countBombas++
                    }
                }
            }
        }
        evaluarEstado(countBombas)
    }

    private fun evaluarEstado(countBombas: Int) {
        var estado = ""

        estado = when (countBombas) {
            in 0..1 -> {
                resources.getText(R.string.normal).toString()
            }
            2 -> {
                resources.getText(R.string.precaucion).toString()
            }
            else -> {
                resources.getText(R.string.peligro).toString()
            }
        }

        estadoTipo.text = estado
    }
}
