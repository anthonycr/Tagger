package com.anthonycr.tagger.codegen

import com.anthonycr.tagger.GENERATED_FILE_NAME
import com.anthonycr.tagger.extensions.addProperties
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec

/**
 * Generates a class with the name [GENERATED_FILE_NAME] that contains all the [PropertySpec]
 * provided to the function. The package name will be the [String] provided to this function.
 */
class TagsFileGeneratorFunction : (Pair<String, List<PropertySpec>>) -> FileSpec {

    override fun invoke(pair: Pair<String, List<PropertySpec>>): FileSpec {
        val (packageName, properties) = pair

        return FileSpec
                .builder(packageName, GENERATED_FILE_NAME)
                .addProperties(properties)
                .build()
    }

}