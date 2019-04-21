package com.example.ast.ui.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onError(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onProgress(show: Boolean, text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun succesRegistration()

}