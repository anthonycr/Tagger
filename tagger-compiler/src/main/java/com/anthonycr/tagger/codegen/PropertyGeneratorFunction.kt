package com.anthonycr.tagger.codegen

import com.anthonycr.tagger.CaseStyle
import com.anthonycr.tagger.source.TagModel
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import javax.lang.model.element.Element

/**
 * A function that generates a [PropertySpec] from a single [Element]. The property outputted by
 * this function for an annotated element with the name `HelloWorld` is as follows:
 * ```
 * val HelloWorld.TAG: String
 *   get() = "HelloWorld"
 * ```
 */
class PropertyGeneratorFunction : (TagModel) -> PropertySpec {

    override fun invoke(tagModel: TagModel): PropertySpec {
        val returnValue = when (tagModel.caseStyle) {
            CaseStyle.ALL_CAPS -> tagModel.element.simpleName.toString()
            CaseStyle.NORMAL -> tagModel.element.simpleName.toString().toUpperCase()
        }

        val getterSpec = FunSpec
                .getterBuilder()
                .addStatement("return %S", returnValue)
                .build()

        return PropertySpec
                .builder("${tagModel.element.qualifiedName}?.TAG", String::class)
                .mutable(false)
                .getter(getterSpec)
                .build()
    }

}