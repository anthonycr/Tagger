package com.anthonycr.tagger.source

import com.anthonycr.tagger.Tag
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

/**
 * A class that generates a [Sequence] of [TagModel].
 */
class TagSource(private val roundEnvironment: RoundEnvironment) {

    /**
     * @return the complete [Sequence] of [TagModel] from the [Element] annotated with [Tag].
     */
    fun elements(): Sequence<TagModel> {
        return roundEnvironment
                .getElementsAnnotatedWith(Tag::class.java)
                .asSequence()
                .filterIsInstance(TypeElement::class.java)
                .map { TagModel(it, it.getAnnotation(Tag::class.java).caseStyle) }
    }

}