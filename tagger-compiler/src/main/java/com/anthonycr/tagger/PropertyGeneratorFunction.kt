package com.anthonycr.tagger

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import javax.lang.model.element.Element

/**
 * Created by anthonycr on 12/3/17.
 */
object PropertyGeneratorFunction : (Element) -> PropertySpec {

    override fun invoke(p1: Element): PropertySpec {
        return PropertySpec.builder("${p1.simpleName}.TAG", String::class)
                .mutable(false)
                .getter(FunSpec.getterBuilder().addStatement("return %S", p1.simpleName).build())
                .build()
    }

}