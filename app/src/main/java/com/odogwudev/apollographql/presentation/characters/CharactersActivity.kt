package com.odogwudev.apollographql.presentation.characters

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.api.Error
import com.odogwudev.apollographql.data.common.onError
import com.odogwudev.apollographql.data.common.onLoading
import com.odogwudev.apollographql.data.common.onSuccess
import com.odogwudev.apollographql.databinding.ActivityCharactersBinding
import com.odogwudev.apollographql.domain.models.CharactersModel
import com.odogwudev.apollographql.domain.models.SingleCharacterModel
import com.odogwudev.apollographql.presentation.details.DetailsActivity
import com.odogwudev.apollographql.utils.CHARACTER_EXTRA
import com.odogwudev.apollographql.utils.hide
import com.odogwudev.apollographql.utils.show
import com.odogwudev.apollographql.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        viewModel.getListCharacters(1)
    }

    private fun initObserver() {
        viewModel.resultListCharacters.observe(this) {
            it.onSuccess { list ->
                binding.progressCircular.hide()
                setListCharacters(list)
            }.onError { error ->
                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> toast(errorMessage.message) }
                    }
                }
            }.onLoading {
                binding.progressCircular.show()
            }
        }
    }

    private fun setListCharacters(list: CharactersModel) {
        with(binding.rvRickAndMorty) {
            adapter = CharactersAdapter(list.results) { goToDetailsActivity(it) }
        }
    }

    private fun goToDetailsActivity(singleCharacterModel: SingleCharacterModel) {
        Intent(this, DetailsActivity::class.java).apply {
            putExtra(CHARACTER_EXTRA, singleCharacterModel)
            startActivity(this)
        }
    }
}