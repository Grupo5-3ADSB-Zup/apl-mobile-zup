package school.sptech.zup.presenter.list.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import school.sptech.zup.R
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.domain.model.DadosEnvioTelaInfluencer
import school.sptech.zup.ui.PerfilUsuarioInfluencer

@Suppress("DEPRECATION")
class ItemPerfilUsuarioAdapter(private var perfisItems: List<PerfilUsuarioResponse>) : RecyclerView.Adapter<ItemPerfilUsuarioAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_perfil_usuario, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = perfisItems[position]

        //val (linkImagem, descricaoSemImagem) = tratarDescricao(post.descricao)

        //holder.postDescription.text = if (linkImagem == null) post.descricao else descricaoSemImagem

        //val imageView: ImageView = holder.fotoUsuario
        //val decodedBytes: ByteArray = Base64.decode(post.foto, Base64.DEFAULT)
        //val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        //imageView.setImageBitmap(bitmap)

        //post.foto?.let {
        //    Glide.with(holder.itemView.context) // Use o contexto do itemView
        //        .load(it)
        //        .into(holder.fotoUsuario)
        //    Log.d("FeedAdapter", "Loading image at position ${post?.idPerfil}. Image URL: $it")
        //}

        if(post.foto != null){
            colocarFotoUsuario(post.foto, holder)
        }

        holder.nomeUsuario.text = post.nome
        //holder.linkInstagram.text = post.linkInstagram
        //holder.linkYoutube.text = post.linkYoutube
        //holder.linkTikTok.text = post.linkTikTok
    }

    private fun colocarFotoUsuario(foto: String, holder: ViewHolder) {
        val imageView: ImageView = holder.fotoUsuario

        val decodedBytes: ByteArray = Base64.decode(foto, Base64.DEFAULT)
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        imageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return perfisItems.size
    }

    // Função para atualizar os dados do adaptador
    fun updateData(newData: List<PerfilUsuarioResponse>) {
        perfisItems = newData
        notifyDataSetChanged()
    }
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val fotoUsuario : ImageView = itemView.findViewById(R.id.foto_usuario)
        val nomeUsuario: TextView = itemView.findViewById(R.id.nome_usuario)
        val linkInstagram: TextView = itemView.findViewById(R.id.link_instagram)
        val linkYoutube: TextView = itemView.findViewById(R.id.link_youtube)
        val linkTikTok: TextView = itemView.findViewById(R.id.link_tiktok)
        //var barraNoticia: BottomNavigationView = itemView.findViewById(R.id.nav_item_noticia)


       init {
           fotoUsuario.setOnClickListener {
               val nome = perfisItems[adapterPosition].nome
               val idPerfil = perfisItems[adapterPosition].idPerfil
               val linkYoutube = perfisItems[adapterPosition].linkYoutube
               val linkInstagram = perfisItems[adapterPosition].linkInstagram
               val linkTikTok = perfisItems[adapterPosition].linkTikTok
               val descPerfil = perfisItems[adapterPosition].descPerfil
               val foto = perfisItems[adapterPosition].foto

               val dados = DadosEnvioTelaInfluencer(
                   nome = nome,
                   idPerfil = idPerfil,
                   linkYoutube = if (linkYoutube == null) "" else linkYoutube,
                   linkInstagram = if(linkInstagram == null) "" else linkInstagram,
                   linkTikTok = if(linkTikTok == null) "" else linkTikTok,
                   descPerfil = descPerfil,
                   foto = foto
               )
               val intent = Intent(itemView.context, PerfilUsuarioInfluencer::class.java)
               intent.putExtra("dados", dados)
               itemView.context.startActivity(intent)
           }
       }


        //init {
       //     barraNoticia.setOnNavigationItemSelectedListener { menuItem ->
       //         when (menuItem.itemId) {
       //             R.id.botao_gpt -> {
                      //  val id = perfisItems[adapterPosition].id
                      //  val titulo = perfisItems[adapterPosition].titulo

                      //  val  dados = DadosEnvioTelaGptRequest(
                      //      id = id,
                      //      titulo = titulo
                      //  )

                      //  val intent = Intent(itemView.context, Gpt::class.java)
                      //  intent.putExtra("dados", dados)
                      //  itemView.context.startActivity(intent)
       //                 true
       //             }
       //             else -> false
       //         }
       //     }
       // }
    }
}