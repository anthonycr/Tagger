package com.anthonycr.tagger.codegen

import com.anthonycr.tagger.CaseStyle
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.ksp.toClassName
import java.util.Locale
import javax.lang.model.element.Element

/**
 * A function that generates a [PropertySpec] from a single [Element]. The property outputted by
 * this function for an annotated element with the name `HelloWorld` is as follows:
 * ```
 * val HelloWorld.TAG: String
 *   get() = "HelloWorld"
 * ```
 */
class PropertyGeneratorFunction : (KSClassDeclaration) -> PropertySpec {

    override fun invoke(tagModel: KSClassDeclaration): PropertySpec {
        val returnValue = tagModel.simpleName.asString()

        val getterSpec = FunSpec
                .getterBuilder()
                .addStatement("return %S", returnValue)
                .build()

        return PropertySpec
                .builder("TAG", String::class)
                .receiver(tagModel.toClassName().copy(nullable = true))
                .mutable(false)
                .getter(getterSpec)
                .build()
    }

}
