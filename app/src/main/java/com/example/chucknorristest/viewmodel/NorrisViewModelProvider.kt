package com.example.chucknorristest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorristest.model.NorrisRepository

class NorrisViewModelProvider(val repo: NorrisRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NorrisViewModel(repo) as T
    }
}