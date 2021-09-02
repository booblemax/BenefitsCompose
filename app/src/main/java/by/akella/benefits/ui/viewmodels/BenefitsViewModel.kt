package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import by.akella.benefits.domain.BenefitsRepository
import by.akella.benefits.domain.models.BenefitModel
import by.akella.benefits.ui.CommonError
import by.akella.benefits.ui.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BenefitsViewModel(
    private val repo: BenefitsRepository
) : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val mBenefitsState: MutableStateFlow<Resource<List<BenefitModel>>> =
        MutableStateFlow(Resource.None)
    val benefitsState: StateFlow<Resource<List<BenefitModel>>> get() = mBenefitsState

    fun loadData() {
        scope.launch {
            repo.getBenefitList(true)
                .flowOn(Dispatchers.IO)
                .onStart { mBenefitsState.emit(Resource.Loading) }
                .onEach { mBenefitsState.emit(Resource.Success(it)) }
                .catch {
                    mBenefitsState.emit(
                        Resource.Error(CommonError("error while loading benefits", it))
                    )
                }
                .launchIn(scope)
        }
    }
}