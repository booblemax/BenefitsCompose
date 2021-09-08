package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import by.akella.benefits.domain.repos.BenefitsRepository
import by.akella.benefits.domain.models.BenefitModel
import util.CommonError
import util.Resource
import util.Resource.None
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repo: BenefitsRepository
) : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val mModelState: MutableStateFlow<Resource<BenefitModel>> =
        MutableStateFlow(None)
    val modelState: StateFlow<Resource<BenefitModel>> get() = mModelState

    fun loadBenefit(id: String) {
        scope.launch {
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