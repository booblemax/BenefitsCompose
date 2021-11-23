package by.akella.shared.di

import com.azharkova.di.container.DIContainer
import com.azharkova.di.scope.ScopeType
import kotlin.reflect.KClass

class DIManager {

    val appContainer: DIContainer by lazy { DIContainer() }

    fun <T: Any> resolve(type: KClass<T>): Any? {
        return appContainer.resolve(type)
    }

    fun <T: Any> addToScope(type: KClass<T>, scope: ScopeType = ScopeType.Graph, fabric: () -> T?) {
        appContainer.register(type, scope, fabric)
    }
}