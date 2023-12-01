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
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import school.sptech.zup.R
import school.sptech.zup.data.model.FeedResponse
import school.sptech.zup.data.model.PerfilUsuarioResponse
import school.sptech.zup.databinding.ActivityPerfilUsuarioInfluencerBinding
import school.sptech.zup.domain.model.DadosEnvioTelaGptRequest
import school.sptech.zup.domain.model.DadosEnvioTelaInfluencer
import school.sptech.zup.ui.Gpt
import school.sptech.zup.ui.PerfilUsuarioInfluencer
import school.sptech.zup.ui.PerfilUsuarioSemFormulario

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

        holder.nomeUsuario.text = post.nome
        holder.linkInstagram.text = post.linkInstagram
        holder.linkYoutube.text = post.linkYoutube
        holder.linkTikTok.text = post.linkTikTok
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
               val intent = Intent(itemView.context, PerfilUsuarioInfluencer::class.java)
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
                   linkYoutube = linkYoutube,
                   linkInstagram = linkInstagram,
                   linkTikTok = linkTikTok,
                   descPerfil = descPerfil,
                   foto = foto
               )
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