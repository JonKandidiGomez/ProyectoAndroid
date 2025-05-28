import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jonkandidi.bibliotecadejuegos.entidades.Juego


class Adaptador(val lista:MutableList<Juego>): RecyclerView.Adapter<Adaptador.ViewHolder>(){

    //El ViewHolder es la clase de cada uno de los contenedores
    inner class ViewHolder (val binding: RecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root){
    }

    //captura la vista que hemos creado (recyclerview_item) y crea una instancia del viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //cargamos los datos en cada una de las instancias del ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rviNombre.text=lista[position].nombre
    }

    //retorna el número de elementos que vamos a querer que tenga el contenedor padre
    override fun getItemCount(): Int {
        return lista.count()
    }
}