package com.example.fox.reactgit.arch.ui.search.presenter

import com.example.fox.reactgit.arch.domain.search.SearchInteractor
import com.example.fox.reactgit.arch.ui.base.BasePresenter
import com.example.fox.reactgit.arch.ui.search.view.ISearchView
import com.example.fox.reactgit.utils.applySchedulers
import com.example.fox.reactgit.utils.showProgress
import com.jakewharton.rxbinding2.InitialValueObservable
import com.example.fox.reactgit.di.scopes.SearchScope as Search
import javax.inject.Inject

@Search
class SearchPresenter @Inject constructor(private val interactor: SearchInteractor) : BasePresenter<ISearchView>(), ISearchPresenter {
    override fun init() {

    }

    override fun searchGitUser(name: String) {
        interactor.searchGitUser(name)
                .applySchedulers()
                .showProgress(getMvpView()!!)
                .subscribe({ response ->
                    val items = response?.items
                    getMvpView()?.setList(items!!)
                }, { error ->
                    getMvpView()?.errorMessage(error.localizedMessage)
                })

    }

    override fun getUserRepositories(login: String) {
        interactor.getUserRepositories(login)
                .applySchedulers()
                .showProgress(getMvpView()!!)
                .subscribe({
                    getMvpView()?.replacment(it)
                }, { error ->
                    getMvpView()?.errorMessage(error.localizedMessage)
                })

    }

    override fun validateField(name: InitialValueObservable<CharSequence>) {
        interactor.validateField(name)
                .applySchedulers()
                .subscribe({
                    getMvpView()?.startSearching(it)
                }, {
                    getMvpView()?.errorMessage(it.localizedMessage)
                })
    }
}