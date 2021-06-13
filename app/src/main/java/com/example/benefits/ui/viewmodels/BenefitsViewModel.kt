package com.example.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.benefits.data.BenefitsRepositoryImpl
import com.example.benefits.di.DbManager
import com.example.benefits.domain.BenefitsRepository
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.CommonError
import com.example.benefits.ui.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BenefitsViewModel : ViewModel() {

    private val repo: BenefitsRepository = BenefitsRepositoryImpl(DbManager.benefitsDao)

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val mBenefitsState: MutableStateFlow<Resource<List<BenefitModel>>> =
        MutableStateFlow(Resource.None)
    val benefitsState: StateFlow<Resource<List<BenefitModel>>> get() = mBenefitsState

    fun loadData() {
        scope.launch(Dispatchers.IO) {
            mBenefitsState.emit(Resource.Loading)
            delay(2000L)
            repo.getBenefitList()
                .catch {
                    mBenefitsState.emit(
                        Resource.Error(CommonError("error while loading benefits", it))
                    )
                }.collect {
                    mBenefitsState.emit(Resource.Success(it))
                }
        }
    }
}