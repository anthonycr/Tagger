package com.anthonycr.tagger.source

import com.anthonycr.tagger.CaseStyle
import javax.lang.model.element.TypeElement

/**
 * A model which describes an annotated element.
 */
data class TagModel(
    val element: TypeElement,
    val caseStyle: CaseStyle
)
