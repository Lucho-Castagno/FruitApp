package com.example.fruitapp.presentation.feature

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.fruitapp.BR

class FruitFormViewState() : BaseObservable() {
    @get:Bindable
    var fruitName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitName)
        }

    @get:Bindable
    var fruitFamily: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitFamily)
        }

    @get:Bindable
    var fruitGenus: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitGenus)
        }

    @get:Bindable
    var fruitOrder: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitOrder)
        }

    @get:Bindable
    var fruitCarbohydrates: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitCarbohydrates)
        }

    @get:Bindable
    var fruitProtein: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitProtein)
        }

    @get:Bindable
    var fruitFat: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitFat)
        }

    @get:Bindable
    var fruitCalories: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitCalories)
        }

    @get:Bindable
    var fruitSugar: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.fruitSugar)
        }
}
