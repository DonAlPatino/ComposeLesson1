package com.example.composelesson

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SocialNetworksListViewModel : ViewModel() {

    data class SocialNetworksListState(
        val allSocialNetworks: List<SocialNetwork> = emptyList(),
        val favourite: SocialNetwork? = null,
    )

    private val _state = MutableStateFlow(
        SocialNetworksListState(
            allSocialNetworks = listOf(
                SocialNetwork("Facebook", "https://facebook.com", 0xFF4267B2L),
                SocialNetwork("WhatsApp", "https://whatsapp.com/", 0xFF25D366L),
                SocialNetwork("Instagram", "https://instagram.com/", 0xFFE1306CL),
                SocialNetwork("Twitter", "https://twitter.com/", 0xFF1DA1F2L),
                SocialNetwork("VK", "https://vk.com/", 0xFF4C75A3L),
                SocialNetwork("Telegram", "https://telegram.org/", 0xFF0088CCL),
            ),
            favourite = SocialNetwork("Telegram", "https://telegram.org/", 0xFF0088CCL)
        ),
    )

    val state: StateFlow<SocialNetworksListState> = _state.asStateFlow()

    fun setFavourite(socialNetwork: SocialNetwork) {
        _state.tryEmit(
            _state.value.copy(
                favourite = socialNetwork
            )
        )
    }
}