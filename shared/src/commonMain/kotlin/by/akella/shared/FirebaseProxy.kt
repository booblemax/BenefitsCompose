package by.akella.shared

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

fun initFirebase(context: Any?) {
    Firebase.initialize(context)
}