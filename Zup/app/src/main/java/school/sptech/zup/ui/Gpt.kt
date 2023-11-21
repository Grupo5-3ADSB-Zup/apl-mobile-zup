package school.sptech.zup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import school.sptech.zup.R
import school.sptech.zup.databinding.ActivityGptBinding
import school.sptech.zup.databinding.ActivityLoginEmailBinding

class Gpt : AppCompatActivity() {

    private val binding by lazy {
        ActivityGptBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}