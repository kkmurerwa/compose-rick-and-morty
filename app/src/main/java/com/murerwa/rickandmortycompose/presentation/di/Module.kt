package com.murerwa.rickandmortycompose.presentation.di

import com.murerwa.rickandmortycompose.presentation.viewmodels.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val presentationModules = module {
    viewModel { CharactersViewModel(get()) }
}

val loadSettingsModules by lazy {
    val modules = listOf(presentationModules)
    loadKoinModules(modules)
}