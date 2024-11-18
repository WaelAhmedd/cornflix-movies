package com.app.movies.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : ViewState, Event : ViewEvent, Effect : ViewSideEffect> :
    ViewModel() {

//    @Inject
//    lateinit var serializationService: ISerializationService

//    @Inject
//    lateinit var loggingService: ILoggingService

//    @Inject
//    lateinit var sharedPrefManager: ISharedPrefManager
//
//    @Inject
//    lateinit var connectivityService: IConnectivityService

    abstract fun setInitialState(): UiState
    abstract fun handleEvents(event: Event)

    private val initialState: UiState by lazy { setInitialState() }

    //Compose runtime MutableState object for receiving continuous updates with initial value.
    private val _viewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val viewState: StateFlow<UiState> = _viewState.asStateFlow()

    //In the absence of a subscriber, any posted event will be immediately dropped.
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    //Each event is delivered to a single subscriber. An attempt to post an event without subscribers will suspend as soon as the channel buffer becomes full, waiting for a subscriber to appear.
    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

//    val retryTrigger = RetryTrigger()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

//    fun getLocalization(): MutableMap<String, MutableList<LocalizationModel>> {
//        val screenMapJson = sharedPrefManager.getString("localizationMap")
//        val gson = Gson()
//        val type =
//            object : TypeToken<MutableMap<String, MutableList<LocalizationModel>>>() {}.type
//        return gson.fromJson(screenMapJson, type)
//    }

    fun setEventWithSideEffect(event: Event, builder: () -> Effect) {
        setEvent(event)
        setEffect(builder)
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    protected fun getState() = _viewState.value

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun isNetworkAvailable() = true

//    fun <T> launchAndCollectResult(
//        flow: Flow<T>,
//        resultGQLSuccess: (T) -> Unit = {},
//        resultGQLFailure: (NetworkResult.Failure) -> Unit = {},
//        onError: (ErrorResponse) -> Unit = {},
//        onStart: () -> Unit = {},
//        onComplete: (Throwable?) -> Unit = {},
//        skipAfterInitial: Boolean = true,
//        bypassNetworkCheck: Boolean = false
//    ): Job? {
//        return if (initialState == _viewState && skipAfterInitial) {
//            null
//        } else if (isNetworkAvailable() || bypassNetworkCheck) {
//            viewModelScope.launch {
//                flow.flowOn(
//                    Dispatchers.IO
//                ).onStart {
//                    onStart()
//                }.onCompletion { cause ->
//                    onComplete(cause)
//                }.catch { exception ->
//                    //     loggingService.stackTrace(exception)
//                    onError(
//                        ErrorResponse(
//                            body = ErrorBody(
//                                message = exception.message,
//                                errors = null
//                            ), code = null
//                        )
//                    )
//                }.safeCollect { result ->
//                    if (result is NetworkResult.Failure) resultGQLFailure(result)
//                    else resultGQLSuccess(result)
//                }
//            }
//        } else {
////            onError(ErrorResponse(networkError = NoInternetThrowable))
//            onComplete(null)
//            null
//        }
//    }

}