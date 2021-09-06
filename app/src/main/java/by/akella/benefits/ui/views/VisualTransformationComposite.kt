package by.akella.benefits.ui.views

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class VisualTransformationComposite : VisualTransformation {

    private val transformations = mutableListOf<VisualTransformation>()

    override fun filter(text: AnnotatedString): TransformedText {
        var changingText = TransformedText(text, OffsetMapping.Identity)
        transformations.forEach {
            changingText = it.filter(changingText.text)
        }
        return changingText
    }

    fun add(visualTransformation: VisualTransformation): VisualTransformationComposite {
        transformations.add(visualTransformation)
        return this
    }
}