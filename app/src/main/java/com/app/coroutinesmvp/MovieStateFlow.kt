package com.app.coroutinesmvp

import kotlinx.coroutines.flow.MutableStateFlow

object MovieStateFlow {
    val onClickStateFlow = MutableStateFlow<String?>(null)
}
