package school.sptech.zup.presenter.list.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import school.sptech.zup.R
import school.sptech.zup.data.model.ComentarioResponse

@Suppress("DEPRECATION")
class ComentarioAdapter(private var comentariosItems: List<ComentarioResponse>) : RecyclerView.Adapter<ComentarioAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = comentariosItems[position]

        holder.comentarioUsuario.text = post.descricao.toString()
        holder.comentarioNome.text = post.usuario.nome

        if (post.usuario.foto != null){
            colocarFotoUsuario(post.usuario.foto, holder)
        }
    }

    private fun colocarFotoUsuario(foto: String, holder: ViewHolder) {
        val imageView: ImageView = holder.comentarioFotoUsuario

        val decodedBytes: ByteArray = Base64.decode(foto, Base64.DEFAULT)
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        imageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return comentariosItems.size
    }

    fun updateData(newData: List<ComentarioResponse>) {
        comentariosItems = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comentarioNome : TextView = itemView.findViewById(R.id.nome_usuario)
        val comentarioUsuario : TextView = itemView.findViewById(R.id.comentario_usuario)
        var comentarioFotoUsuario: ImageView = itemView.findViewById(R.id.foto_usuario)
    }

}