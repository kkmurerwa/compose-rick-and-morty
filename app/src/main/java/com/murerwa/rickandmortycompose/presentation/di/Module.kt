package com.murerwa.rickandmortycompose.presentation.di

import com.murerwa.rickandmortycompose.presentation.screens.characterdetails.CharacterDetailsViewModel
import com.murerwa.rickandmortycompose.presentation.screens.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val presentationModules = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
}