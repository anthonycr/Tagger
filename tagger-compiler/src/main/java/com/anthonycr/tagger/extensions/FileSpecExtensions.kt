package com.anthonycr.tagger.extensions

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec

/**
 * Adds the provided [properties] to the specification.
 */
fun FileSpec.Builder.addProperties(properties: List<PropertySpec>): FileSpec.Builder {
    properties.forEach { addProperty(it) }

    return this
}