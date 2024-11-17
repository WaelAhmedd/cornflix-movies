package com.app.movies.features.splash

import com.app.movies.core.base.ViewEvent
import com.app.movies.core.base.ViewSideEffect
import com.app.movies.core.base.ViewState


sealed class SplashEvent : ViewEvent {
    object Start : SplashEvent()

}

sealed class SplashState : ViewState {
    object Init : SplashState()
    object Idle : SplashState()
    class CheckingUpdate(val needUpdate: Boolean) : SplashState()
}

sealed class SplashSideEffect : ViewSideEffect {
    data class ShowError(val message: String) : SplashSideEffect()
    object NavigateToHomeScreen : SplashSideEffect()
}