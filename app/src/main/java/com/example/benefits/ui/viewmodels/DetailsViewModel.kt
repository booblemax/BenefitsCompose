package com.example.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.benefits.data.BenefitsRepositoryImpl
import com.example.benefits.di.DbManager
import com.example.benefits.domain.BenefitsRepository
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.CommonError
import com.example.benefits.ui.Resource
import com.example.benefits.ui.Resource.None
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repo: BenefitsRepository = BenefitsRepositoryImpl(DbManager.benefitsDao)

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val mModelState: MutableStateFlow<Resource<BenefitModel>> =
        MutableStateFlow(None)
    val modelState: StateFlow<Resource<BenefitModel>> get() = mModelState

    fun loadBenefit(id: String) {
        scope.launch(Dispatchers.IO) {
            mModelState.emit(Resource.Loading)
            delay(1500L)
            repo.getBenefit(id)
                .catch {
                    mModelState.emit(Resource.Error(CommonError("error while loading benefit", it)))
                }.collect {
                    mModelState.emit(Resource.Success(it))
                }
        }
    }
}