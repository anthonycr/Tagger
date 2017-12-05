package com.anthonycr.tagger

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec

/**
 * Created by anthonycr on 12/3/17.
 */
object TagsFileGeneratorFunction : (Pair<String, List<PropertySpec>>) -> FileSpec {

    override fun invoke(p1: Pair<String, List<PropertySpec>>): FileSpec {
        val builder = FileSpec.builder(p1.first, "Tags")

        p1.second.forEach {
            builder.addProperty(it)
        }

        return builder.build()
    }

}