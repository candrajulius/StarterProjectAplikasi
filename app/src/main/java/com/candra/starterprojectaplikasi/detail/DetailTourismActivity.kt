package com.candra.starterprojectaplikasi.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.candra.starterprojectaplikasi.R
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import com.candra.starterprojectaplikasi.databinding.ActivityDetailTourismBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTourismActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailTourismViewModel: DetailTourismViewModel by viewModels()
    private lateinit var binding: ActivityDetailTourismBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailTourism = intent.getParcelableExtra<Tourism>(EXTRA_DATA)
        showDetailTourism(detailTourism)
    }

    private fun showDetailTourism(detailTourism: Tourism?) {
        detailTourism?.let {
            supportActionBar?.title = detailTourism.name
            binding.content.tvDetailDescription.text = detailTourism.description
            Glide.with(this@DetailTourismActivity)
                .load(detailTourism.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailTourism.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailTourismViewModel.setFavoriteTourism(detailTourism, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}

/*
Kesimpulan
Constructor Injection vs @Provides vs @Binds
Walaupun Anda bisa menggunakan @Provides pada setiap Injection, namun secara best-practice lebih baik Anda menggunakan Constructor Injection bilamana bisa. Seperti yang Anda lihat pada diagram di atas, hampir semuanya menggunakan Constructor Injection.

Perhatikan untuk Objek yang paling bawah (ujung) biasanya memang menggunakan @Provides karena memang sudah tidak ada constructor lagi kecuali Context. Untuk itu Anda perlu membuat module-nya, yaitu StorageModule dan NetworkModule.

Lalu mengapa untuk TourismUseCase dan ITourismRepository perlu menggunakan @Binds?
Kita tahu bahwa ITourismRepository adalah interface, artinya ia membutuhkan concrete implementation, dalam hal ini yaitu TourismRepository. Oleh karena itulah kita perlu mendefinisikannya di sini, caranya yaitu dengan membuat abstract method dengan parameter berupa implementation class (TourismRepository) dan kembalian berupa interface (ITourismRepository).

Perhatikan juga karena fungsinya abstract maka class-nya juga harus abstract, karena itulah biasanya module untuk @Binds ini disendirikan. Jika Anda ingin tetap menggabungkannya dengan @Provides Anda harus membuat fungsi yang lain menjadi static dengan menambahkan annotation @JvmStatic pada Kotlin.

 Activity inject Dagger di dalam OnCreate sebelum keyword super.

Fragment inject Dagger di dalam OnAttach setelah keyword super.
 */