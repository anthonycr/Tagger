package com.anthonycr.tagger

import com.anthonycr.tagger.codegen.PropertyGeneratorFunction
import com.anthonycr.tagger.codegen.TagsFileGeneratorFunction
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ksp.writeTo

/**
 * The main entry point for the annotation processing to begin. This class processes all annotated
 * elements and generates the code for them. This processor only supports one annotation [Tag]. The
 * one annotation processor argument supported is [OPTION_PACKAGE_NAME], which allows the user to
 * change the package name into which the source code is generated.
 */
class TaggerSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger
) : SymbolProcessor {

    private var isProcessed = false

    //override fun getSupportedOptions() = setOf(OPTION_PACKAGE_NAME)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (isProcessed) {
            return emptyList()
        }

        isProcessed = true

        val packageName =
            "com.anthonycr.sample"//processingEnv.options.getOrDefault(OPTION_PACKAGE_NAME, OPTION_PACKAGE_NAME_DEFAULT)

        logger.info("Starting Tagger processing")

        resolver
            .getSymbolsWithAnnotation(Tag::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .map(PropertyGeneratorFunction())
            .toList()
            .let { Pair(packageName, it) }
            .let(TagsFileGeneratorFunction())
            .writeTo(codeGenerator, true)

        logger.info("Successfully finished Tagger processing")

        return emptyList()
    }

}
