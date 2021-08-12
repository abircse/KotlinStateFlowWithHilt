package com.coxtunes.ponnobeponi.kotlinstateflowhiltretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coxtunes.kotlinstateflowhiltretrofit.model.CategoryBase
import com.coxtunes.kotlinstateflowhiltretrofit.repository.MainRepository
import com.coxtunes.kotlinstateflowhiltretrofit.utils.DispatcherProvider
import com.nexgen.tbl.base.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    sealed class ResponseEvent {
        class Success(val data: CategoryBase) : ResponseEvent()
        class Failure(val message: String) : ResponseEvent()
        object Loading : ResponseEvent()
        object Empty : ResponseEvent()
    }


    private val _loadCategoryEvent = MutableStateFlow<ResponseEvent>(ResponseEvent.Empty)
    val loadCategoryEvent: Flow<ResponseEvent> get() = _loadCategoryEvent

    fun loadCategory() {
        _loadCategoryEvent.value = ResponseEvent.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            when (val response = repository.loadCategory()) {
                is Resource.Error -> {
                    _loadCategoryEvent.value = ResponseEvent.Failure(response.message.toString())
                }
                is Resource.Success -> {
                    val myresponse = response.data
                    if (myresponse != null) {
                        _loadCategoryEvent.value = ResponseEvent.Success(response.data)
                    } else {
                        _loadCategoryEvent.value = ResponseEvent.Empty
                    }
                }
            }
        }
    }
}