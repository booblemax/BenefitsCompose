package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import by.akella.benefits.util.CommonError
import by.akella.benefits.util.Resource
import by.akella.shared.domain.models.BenefitModel
import by.akella.shared.domain.repos.BenefitsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BenefitsViewModel(
    private val repo: BenefitsRepository
) : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val mBenefitsState: MutableStateFlow<Resource<List<BenefitModel>>> =
        MutableStateFlow(Resource.None)
    val benefitsState: StateFlow<Resource<List<BenefitModel>>> = mBenefitsState

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