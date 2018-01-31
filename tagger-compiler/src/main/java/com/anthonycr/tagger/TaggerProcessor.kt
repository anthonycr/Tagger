package com.anthonycr.tagger

import com.anthonycr.tagger.codegen.PropertyGeneratorFunction
import com.anthonycr.tagger.codegen.TagsFileGeneratorFunction
import com.anthonycr.tagger.source.TagSource
import com.anthonycr.tagger.utils.MessagerUtils
import com.google.auto.service.AutoService
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * The main entry point for the annotation processing to begin. This class processes all annotated
 * elements and generates the code for them. This processor only supports one annotation [Tag]. The
 * one annotation processor argument supported is [OPTION_PACKAGE_NAME], which allows the user to
 * change the package name into which the source code is generated.
 */
@AutoService(Processor::class)
class TaggerProcessor : AbstractProcessor() {

    private var isProcessed = false

    override fun getSupportedOptions() = setOf(OPTION_PACKAGE_NAME)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes() = setOf(Tag::class.java.name)

    override fun init(environment: ProcessingEnvironment) {
        super.init(environment)
        MessagerUtils.messager = environment.messager
    }

    override fun process(set: Set<TypeElement>?, roundEnvironment: RoundEnvironment): Boolean {
        if (isProcessed) {
            return true
        }

        isProcessed = true

        val packageName = processingEnv.options.getOrDefault(OPTION_PACKAGE_NAME, OPTION_PACKAGE_NAME_DEFAULT)

        val sourceDir = processingEnv.options["kapt.kotlin.generated"] ?: run {
            MessagerUtils.reportGenericError("Unable to find target directory to generate Kotlin files.")
            return false
        }

        MessagerUtils.reportMessage("Starting Tagger processing")

        TagSource(roundEnvironment)
                .elements()
                .map(PropertyGeneratorFunction())
                .toList()
                .let { Pair(packageName, it) }
                .let(TagsFileGeneratorFunction())
                .writeTo(File(sourceDir))

        MessagerUtils.reportMessage("Successfully finished Tagger processing")

        return true
    }

}