package com.example.ejerbotones12azael

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log
import kotlin.random.Random

private lateinit var tvHello : TextView
private lateinit var tvJuego : TextView
private lateinit var tvBuenaSuerte : TextView
private lateinit var tvNorma1 : TextView
private lateinit var tvNorma2 : TextView
private lateinit var tvNorma3 : TextView
private lateinit var btnEmpezarJugar : Button
private lateinit var tvTiempo : TextView
private lateinit var tvJugadasTotales : TextView
private lateinit var btnPuls01 : Button
private lateinit var btnPuls02 : Button
private lateinit var btnPuls03 : Button
private lateinit var btnPuls04 : Button
private lateinit var btnPuls05 : Button
private lateinit var btnPuls06 : Button
private lateinit var btnPuls07 : Button
private lateinit var btnPuls08 : Button
private lateinit var btnPuls09 : Button
private lateinit var btnPuls10 : Button
private lateinit var btnPuls11 : Button
private lateinit var btnPuls12 : Button
private lateinit var btnReset : Button
private lateinit var etPlayerName : EditText
private lateinit var tvBestPlayer : TextView
private lateinit var tvLastPlays : TextView



private var tiempoRestante:Int = 0;
private var jugadasRestantes:Int = 0;
private var juegoEnCurso:Boolean = false;
private var playerName = "Desconocido"
private var bestPlayer = ""
private var bestScore = 0
private val lastFivePlays = mutableListOf<String>()
private var numRandom = 0
private var rachaenable = false;
private var pararBoton = false;
private var numeroRachaRandom=0


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()
        inicio()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public fun inicio(){
        rachaenable = false;
        pararBoton = false;
        intentoDeRachaActiva();
        if (rachaenable){
            tvTiempo.setBackgroundResource(R.color.green);
            tvJugadasTotales.setBackgroundResource(R.color.green);
        }else{
            tvTiempo.setBackgroundResource(R.color.red);
            tvJugadasTotales.setBackgroundResource(R.color.red);
        }
        reiniciarColorBotones()
        playerName = "Desconocido"
        juegoEnCurso = true;
        tiempoRestante = 10;
        tvTiempo.text = "Tiempo restante: " + tiempoRestante.toString() + " seg."
        btnReset.isEnabled = false
        btnEmpezarJugar.isEnabled = true
        jugadasRestantes= 0
        tvJugadasTotales.text = "jugadas totales: "+ jugadasRestantes.toString() + " botones."
        ocultarBotones()

    }

    private fun intentoDeRachaActiva() {

        numeroRachaRandom = Random.nextInt(1, 10)
        Log.i("El numero Random es: ", numeroRachaRandom.toString());
        if (numeroRachaRandom > 6){
            rachaenable = true
        }

    }


    public fun initComponents (){

        tvHello = findViewById(R.id.tvHello)
        tvJuego = findViewById(R.id.tvJuego)
        tvBuenaSuerte = findViewById(R.id.tvBuenaSuerte)
        tvNorma1 = findViewById(R.id.tvNorma1)
        tvNorma2 = findViewById(R.id.tvNorma2)
        tvNorma3 = findViewById(R.id.tvNorma3)
        btnEmpezarJugar = findViewById(R.id.btnEmpezarJugar)
        tvTiempo = findViewById(R.id.tvTiempo)
        tvJugadasTotales = findViewById(R.id.tvJugadasTotales)
        btnPuls01 = findViewById(R.id.btnPuls01)
        btnPuls02 = findViewById(R.id.btnPuls02)
        btnPuls03 = findViewById(R.id.btnPuls03)
        btnPuls04 = findViewById(R.id.btnPuls04)
        btnPuls05 = findViewById(R.id.btnPuls05)
        btnPuls06 = findViewById(R.id.btnPuls06)
        btnPuls07 = findViewById(R.id.btnPuls07)
        btnPuls08 = findViewById(R.id.btnPuls08)
        btnPuls09 = findViewById(R.id.btnPuls09)
        btnPuls10 = findViewById(R.id.btnPuls10)
        btnPuls11 = findViewById(R.id.btnPuls11)
        btnPuls12 = findViewById(R.id.btnPuls12)
        btnReset = findViewById(R.id.btnReset)
        etPlayerName = findViewById(R.id.etPlayerName)
        tvBestPlayer = findViewById(R.id.tvBestPlayer)
        tvLastPlays = findViewById(R.id.tvLastPlays)

        botonesPulsadosContador(btnPuls01)
        botonesPulsadosContador(btnPuls02)
        botonesPulsadosContador(btnPuls03)
        botonesPulsadosContador(btnPuls04)
        botonesPulsadosContador(btnPuls05)
        botonesPulsadosContador(btnPuls06)
        botonesPulsadosContador(btnPuls07)
        botonesPulsadosContador(btnPuls08)
        botonesPulsadosContador(btnPuls09)
        botonesPulsadosContador(btnPuls10)
        botonesPulsadosContador(btnPuls11)
        botonesPulsadosContador(btnPuls12)

    }

    public fun initListeners (){

        btnEmpezarJugar.setOnClickListener{
            jugar()
        }

        btnReset.setOnClickListener{
            reset()
        }
    }

    private fun reset() {

        btnEmpezarJugar.isEnabled = true
        btnReset.isEnabled = false
        btnEmpezarJugar.text = "JUGAR"
        btnReset.text=" "
        inicio()

    }

    private fun jugar() {

        btnEmpezarJugar.isEnabled = false
        btnReset.text = " "
        btnEmpezarJugar.text=" "

        var postDelayed01 = android.os.Handler().postDelayed({
            cuentaAtrasTexto() }, 1000)
        var postDelayed02 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()        }, 2000)
        var postDelayed03 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()        }, 3000)
        var postDelayed04 = android.os.Handler().postDelayed({
            cuentaAtrasTexto() }, 4000)
        var postDelayed05 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()        }, 5000)
        var postDelayed06 = android.os.Handler().postDelayed({
            cuentaAtrasTexto()
            if (rachaenable){
                pararBoton = true
            } }, 6000)

        if (rachaenable){
            var postDelayed07 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.green);
                cuentaAtrasTexto()}, 7000)
            var postDelayed07_5 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.blue); }, 7500)
            var postDelayed08 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.green);
                cuentaAtrasTexto()        }, 8000)
            var postDelayed08_5 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.blue); }, 8500)
            var postDelayed09 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.green);
                cuentaAtrasTexto() }, 9000)
            var postDelayed09_5 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.blue); }, 9500)
            var postDelayed10 = android.os.Handler().postDelayed({
                tvTiempo.setBackgroundResource(R.color.green);
                cuentaAtrasTexto()
                btnEmpezarJugar.text = " "
                btnReset.text = "RESET"
                btnReset.isEnabled = true
                juegoEnCurso = false;
                val player = etPlayerName.text.toString()
                actualizarRegistros(player)
                ocultarBotones()
                reiniciarColorBotones() }, 10000)
        }else{
            var postDelayed07 = android.os.Handler().postDelayed({
                cuentaAtrasTexto()}, 7000)
            var postDelayed08 = android.os.Handler().postDelayed({
                cuentaAtrasTexto()        }, 8000)
            var postDelayed09 = android.os.Handler().postDelayed({
                cuentaAtrasTexto()        }, 9000)
            var postDelayed10 = android.os.Handler().postDelayed({
                cuentaAtrasTexto()
                btnEmpezarJugar.text = " "
                btnReset.text = "RESET"
                btnReset.isEnabled = true
                juegoEnCurso = false;
                val player = etPlayerName.text.toString()
                actualizarRegistros(player)
                ocultarBotones()
                reiniciarColorBotones() }, 10000)
        }


            mostrarBotonesRandom()



    }


    private fun mostrarBotonesRandom() {

        numRandom = Random.nextInt(1, 13)
        when (numRandom) {

            1->{
                reiniciarColorBotones()
                btnPuls01.isEnabled = true
                btnPuls01.setBackgroundResource(R.color.cyan)
            }
            2->{
                reiniciarColorBotones()
                btnPuls02.isEnabled = true
                btnPuls02.setBackgroundResource(R.color.cyan)
            }
            3->{
                reiniciarColorBotones()
                btnPuls03.isEnabled = true
                btnPuls03.setBackgroundResource(R.color.cyan)
            }
            4->{
                reiniciarColorBotones()
                btnPuls04.isEnabled = true
                btnPuls04.setBackgroundResource(R.color.cyan)
            }
            5->{
                reiniciarColorBotones()
                btnPuls05.isEnabled = true
                btnPuls05.setBackgroundResource(R.color.cyan)
            }
            6->{
                reiniciarColorBotones()
                btnPuls06.isEnabled = true
                btnPuls06.setBackgroundResource(R.color.cyan)
            }
            7->{
                reiniciarColorBotones()
                btnPuls07.isEnabled = true
                btnPuls07.setBackgroundResource(R.color.cyan)
            }
            8->{
                reiniciarColorBotones()
                btnPuls08.isEnabled = true
                btnPuls08.setBackgroundResource(R.color.cyan)
            }
            9->{
                reiniciarColorBotones()
                btnPuls09.isEnabled = true
                btnPuls09.setBackgroundResource(R.color.cyan)
            }
            10->{
                reiniciarColorBotones()
                btnPuls10.isEnabled = true
                btnPuls10.setBackgroundResource(R.color.cyan)
            }
            11->{
                reiniciarColorBotones()
                btnPuls11.isEnabled = true
                btnPuls11.setBackgroundResource(R.color.cyan)
            }
            12->{
                reiniciarColorBotones()
                btnPuls12.isEnabled = true
                btnPuls12.setBackgroundResource(R.color.cyan)
            }
        }

    }

    private fun reiniciarColorBotones() {
        btnPuls01.setBackgroundResource(R.color.white)
        btnPuls02.setBackgroundResource(R.color.white)
        btnPuls03.setBackgroundResource(R.color.white)
        btnPuls04.setBackgroundResource(R.color.white)
        btnPuls05.setBackgroundResource(R.color.white)
        btnPuls06.setBackgroundResource(R.color.white)
        btnPuls07.setBackgroundResource(R.color.white)
        btnPuls08.setBackgroundResource(R.color.white)
        btnPuls09.setBackgroundResource(R.color.white)
        btnPuls10.setBackgroundResource(R.color.white)
        btnPuls11.setBackgroundResource(R.color.white)
        btnPuls12.setBackgroundResource(R.color.white)

    }

    private fun ocultarBotones() {
        btnPuls01.isEnabled = false
        btnPuls02.isEnabled = false
        btnPuls03.isEnabled = false
        btnPuls04.isEnabled = false
        btnPuls05.isEnabled = false
        btnPuls06.isEnabled = false
        btnPuls07.isEnabled = false
        btnPuls08.isEnabled = false
        btnPuls09.isEnabled = false
        btnPuls10.isEnabled = false
        btnPuls11.isEnabled = false
        btnPuls12.isEnabled = false

    }

    private fun cuentaAtrasTexto() {

        tiempoRestante -= 1
        tvTiempo.text = "Tiempo restante: " + tiempoRestante.toString() + " seg."

    }

    private fun botonesPulsadosContador(Button: Button) {

        Button.setOnClickListener() {
            if (juegoEnCurso) {
                if (rachaenable && pararBoton){
                    jugadasRestantes += 1
                    tvJugadasTotales.text = "jugadas totales: "+ jugadasRestantes.toString() + " botones."
                    Button.isEnabled = true
                } else{
                    jugadasRestantes += 1
                    tvJugadasTotales.text = "jugadas totales: "+ jugadasRestantes.toString() + " botones."
                    Button.isEnabled = false
                    mostrarBotonesRandom()
                }
                /*jugadasRestantes += 1
                tvJugadasTotales.text = "jugadas totales: "+ jugadasRestantes.toString() + " botones."
                Button.isEnabled = false
                mostrarBotonesRandom()*/
            }
        }

    }

    private fun imprimir_MejorJugador_Registros(tvBestPlayer: TextView, tvLastPlays: TextView) {

        tvBestPlayer.text = "Record de puntos: \n Best: $bestPlayer $bestScore"
        tvLastPlays.text = "Últimas jugadas:\n" + lastFivePlays.joinToString("\n")
    }

    private fun actualizarRegistros(player: String) {
        if (player.isNotEmpty()) {
            playerName = player
        }

        if (lastFivePlays.size == 5) {
            lastFivePlays.removeLast() // Elimina el más antiguo
        }
        lastFivePlays.add(0, "$playerName: $jugadasRestantes")  // Añade la nueva jugada al principio

        if (jugadasRestantes > bestScore) {
            bestPlayer = playerName
            bestScore = jugadasRestantes
        }

        imprimir_MejorJugador_Registros(tvBestPlayer, tvLastPlays)
    }
}