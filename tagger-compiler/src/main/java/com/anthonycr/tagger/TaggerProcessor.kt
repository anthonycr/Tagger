package com.anthonycr.tagger

import com.google.auto.service.AutoService
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * Created by anthonycr on 12/3/17.
 */
@AutoService(Processor::class)
class TaggerProcessor : AbstractProcessor() {

    private var isProcessed = false

    override fun getSupportedOptions() = mutableSetOf(OPTION_PACKAGE_NAME)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes() = mutableSetOf(Tag::class.java.name)

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment): Boolean {
        if (isProcessed) {
            return true
        }
        isProcessed = true

        val packageName = processingEnv.options.getOrDefault(OPTION_PACKAGE_NAME, OPTION_PACKAGE_NAME_DEFAULT)
        val sourceDir = processingEnv.options["kapt.kotlin.generated"]
        messager = processingEnv.messager

        val source = TagSource(roundEnvironment)

        source
                .elements()
                .map(PropertyGeneratorFunction)
                .toList()
                .let { Pair(packageName, it) }
                .let(TagsFileGeneratorFunction)
                .writeTo(File(sourceDir))

        return true
    }

    companion object {
        lateinit var messager: Messager

        fun report(stuff: String) {
            messager.printMessage(Diagnostic.Kind.NOTE, stuff)
        }
    }

}