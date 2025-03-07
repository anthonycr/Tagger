package com.anthonycr.tagger

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

/**
 * The main entry point for the symbol processing to begin. This class processes all annotated
 * elements and generates the code for them. This processor only supports one annotation [Tag]. The
 * one symbol processor argument supported is [OPTION_PACKAGE_NAME], which allows the user to
 * change the package name into which the source code is generated.
 */
class TaggerSymbolProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val packageName: String
) : SymbolProcessor {

    private var isProcessed = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (isProcessed) {
            return emptyList()
        }

        isProcessed = true

        logger.info("Starting Tagger processing")

        resolver
            .getSymbolsWithAnnotation(Tag::class.qualifiedName!!)
            .filterIsInstance<KSClassDeclaration>()
            .map { declaration ->
                val funSpec = FunSpec
                    .getterBuilder()
                    .addStatement("return %S", declaration.simpleName.asString())
                    .build()

                PropertySpec
                    .builder("TAG", String::class)
                    .receiver(declaration.toClassName().copy(nullable = true))
                    .mutable(false)
                    .getter(funSpec)
                    .build()
            }
            .toList()
            .let { propertySpecs ->
                FileSpec
                    .builder(packageName, GENERATED_FILE_NAME)
                    .addProperties(propertySpecs)
                    .build()
            }
            .writeTo(codeGenerator, true)

        logger.info("Successfully finished Tagger processing")

        return emptyList()
    }

}
