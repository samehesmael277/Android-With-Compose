package com.sameh.androidwithcomposefromaz.whats_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class ChatIntent {
    data class onClickItem(val name: String, val id: Int) : ChatIntent()
}

sealed class ChatsEffect {
    data class NavtoDetails(val name: String, val id: Int) : ChatsEffect()
}

class WhatsAppViewModel : ViewModel() {

    val channel = Channel<ChatIntent>(Channel.UNLIMITED)
    private val _effect = MutableSharedFlow<ChatsEffect>()

    val effect: SharedFlow<ChatsEffect> = _effect.asSharedFlow()
    private val _state = MutableStateFlow(ChatState())
    val state: StateFlow<ChatState> = _state.asStateFlow()

    init {
        process()
    }

    private fun process() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ChatIntent.onClickItem -> onClickItem(intent.name, intent.id)
                }
            }
        }
    }

    private fun onClickItem(name: String, id: Int) {
        viewModelScope.launch {
            _effect.emit(ChatsEffect.NavtoDetails(name, id))
        }
    }
}