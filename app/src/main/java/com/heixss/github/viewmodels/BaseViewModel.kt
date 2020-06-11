package com.heixss.github.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class BaseViewModel : ViewModel() {
    val refreshSubject = BehaviorSubject.createDefault(Progress.HIDE)
    val errorSubject = PublishSubject.create<String>()
}