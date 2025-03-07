package com.anthonycr.tagger

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class TaggerSymbolProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        TaggerSymbolProcessor(
            environment.codeGenerator,
            environment.logger,
            environment.options.getOrDefault(OPTION_PACKAGE_NAME, OPTION_PACKAGE_NAME_DEFAULT)
        )
}
