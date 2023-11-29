package school.sptech.zup.presenter.list.adapter

import android.content.Intent
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
import school.sptech.zup.domain.model.DadosEnvioTelaGptRequest
import school.sptech.zup.ui.Gpt
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
        //holder.postTitulo.text = post.titulo

        //linkImagem?.let {
        //    Glide.with(holder.itemView.context) // Use o contexto do itemView
        //        .load(it)
        //        .into(holder.postImage)
        //    Log.d("FeedAdapter", "Loading image at position $position. Image URL: $it")
        //}

        //    val menuItemComentario = holder.barraNoticia.findItem(R.id.botao_comentario)
        //    val menuItemGPT = holder.barraNoticia.findItem(R.id.botao_gpt)

        //    menuItemGPT.setOnMenuItemClickListener{
        //        val intent = Intent(holder.itemView.context, Gpt::class.java)
        //        holder.itemView.context.startActivity(intent)
        //        true
        //    }
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
        var barraNoticia: BottomNavigationView = itemView.findViewById(R.id.nav_item_noticia)


       init {
           fotoUsuario.setOnClickListener {
               val intent = Intent(itemView.context, PerfilUsuarioSemFormulario::class.java)
               // intent.putExtra("dados", dados)
               itemView.context.startActivity(intent)
           }
       }


        init {
            barraNoticia.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.botao_gpt -> {
                      //  val id = perfisItems[adapterPosition].id
                      //  val titulo = perfisItems[adapterPosition].titulo

                      //  val  dados = DadosEnvioTelaGptRequest(
                      //      id = id,
                      //      titulo = titulo
                      //  )

                      //  val intent = Intent(itemView.context, Gpt::class.java)
                      //  intent.putExtra("dados", dados)
                      //  itemView.context.startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
    }
}