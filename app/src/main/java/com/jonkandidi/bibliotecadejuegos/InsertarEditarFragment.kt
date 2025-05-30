package com.jonkandidi.bibliotecadejuegos


import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.jonkandidi.bibliotecadejuegos.databinding.FragmentInsertarEditarBinding
import com.jonkandidi.bibliotecadejuegos.entidades.Juego
import com.jonkandidi.bibliotecadejuegos.modelo.JuegoViewModel
import java.io.ByteArrayOutputStream
import java.io.InputStream

class InsertarEditarFragment : Fragment() {

    private var _binding: FragmentInsertarEditarBinding? = null
    private val binding get() = _binding!!
    private val miViewModel: JuegoViewModel by activityViewModels()

    private val args: InsertarEditarFragmentArgs by navArgs()
    private var juegoActual:Juego? = null

    private var imagenSeleccionadaUri: Uri? = null

    private val seleccionarImagenLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imagenSeleccionadaUri = it
            binding.ivImagen.setImageURI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertarEditarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val juegoId = args.juegoId

        if (juegoId != -1) {
            miViewModel.buscarJuegoPorId(juegoId).observe(viewLifecycleOwner) { juego ->
                if (juego != null) {
                    juegoActual = juego
                    binding.etTituloJuego.setText(juego.titulo)
                    binding.etAnyo.setText(juego.año.toString())
                    binding.etDesarrollador.setText(juego.desarrollador)
                    binding.etResumen.setText(juego.resumen)

                    juego.imagen?.let {
                        val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                        binding.ivImagen.setImageBitmap(bitmap)
                    } ?: run {
                        binding.ivImagen.setImageResource(R.mipmap.ic_launcher)
                    }
                }
            }
        }

        binding.bImagen.setOnClickListener {
            seleccionarImagenLauncher.launch("image/*")
        }

        binding.bGuardar.setOnClickListener {
            val titulo = binding.etTituloJuego.text.toString()
            val anyoStr = binding.etAnyo.text.toString()
            val desarrollador = binding.etDesarrollador.text.toString()
            val resumen = binding.etResumen.text.toString()

            if (titulo.isBlank() || anyoStr.isBlank() || desarrollador.isBlank()) {
                Toast.makeText(requireContext(), "Rellena todos los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val anyo = anyoStr.toIntOrNull()
            if (anyo == null) {
                Toast.makeText(requireContext(), "El año no es válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imagenBytes: ByteArray? = if (imagenSeleccionadaUri != null) {
                uriToByteArray(requireContext(), imagenSeleccionadaUri!!)
            } else {
                juegoActual?.imagen
            }

            val juegoParaGuardar = Juego(
                id = juegoActual?.id ?: 0,
                titulo = titulo,
                año = anyo,
                desarrollador = desarrollador,
                resumen = resumen,
                imagen = imagenBytes
            )

            if (juegoActual == null) {
                miViewModel.agregarJuego(juegoParaGuardar)
                miViewModel.insertar(juegoParaGuardar)
            } else {
                miViewModel.modificar(juegoParaGuardar)
            }

            Toast.makeText(requireContext(), "Juego guardado", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        }


        binding.bLimpiar.setOnClickListener {
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {
        binding.etTituloJuego.text.clear()
        binding.etAnyo.text.clear()
        binding.etDesarrollador.text.clear()
        binding.etResumen.text.clear()
        binding.ivImagen.setImageResource(R.mipmap.ic_launcher)
        imagenSeleccionadaUri = null
        juegoActual = null
    }

    fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val buffer = ByteArrayOutputStream()
            val data = ByteArray(1024)
            var nRead: Int
            while (inputStream?.read(data).also { nRead = it ?: -1 } != -1) {
                buffer.write(data, 0, nRead)
            }
            inputStream?.close()
            buffer.flush()
            buffer.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
