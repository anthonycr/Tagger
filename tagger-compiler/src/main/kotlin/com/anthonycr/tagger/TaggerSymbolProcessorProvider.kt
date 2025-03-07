package com.anthonycr.tagger

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

/**
 * This provider abstracts away the creation of the [SymbolProcessor] allowing us to do proper
 * dependency injection.
 */
class TaggerSymbolProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor =
        TaggerSymbolProcessor(
            environment.codeGenerator,
            environment.logger,
            environment.options.getOrDefault(OPTION_PACKAGE_NAME, OPTION_PACKAGE_NAME_DEFAULT)
        )
}
