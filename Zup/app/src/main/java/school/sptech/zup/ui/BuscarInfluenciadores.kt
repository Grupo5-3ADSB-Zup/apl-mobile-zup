package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.databinding.ActivityBuscarInfluenciadoresBinding

class BuscarInfluenciadores : AppCompatActivity() {

    val binding by lazy {

        ActivityBuscarInfluenciadoresBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //val get = ServiceApi.buscarInfluenciadores()
    }


}
