package com.anthonycr.tagger

import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element

/**
 * Created by anthonycr on 12/3/17.
 */
class TagSource(private val roundEnvironment: RoundEnvironment) {

    fun elements(): Sequence<Element> =
            roundEnvironment.getElementsAnnotatedWith(Tag::class.java).asSequence()

}