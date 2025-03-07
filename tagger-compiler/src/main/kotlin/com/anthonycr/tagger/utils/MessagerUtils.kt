package com.anthonycr.tagger.utils

import javax.annotation.processing.Messager
import javax.lang.model.element.Element
import javax.tools.Diagnostic

/**
 * A utility used to communicate with the compiler.
 */
object MessagerUtils {

    lateinit var messager: Messager

    /**
     * Reports a message to the compiler, printed as a [Diagnostic.Kind.NOTE].
     *
     * @param message the message to print to the compilation log.
     */
    fun reportMessage(message: String) {
        messager.printMessage(Diagnostic.Kind.NOTE, message)
    }

    /**
     * Reports an error to the compiler, printed as a [Diagnostic.Kind.ERROR]. Note that calling
     * this function will cause compilation to abort with an error. The compiler will point to an
     * exact element causing the problem.
     *
     * @param offendingElement the element that is causing the error.
     * @param error the error message that will guide the user to successful compilation.
     */
    fun reportError(offendingElement: Element, error: String) {
        messager.printMessage(Diagnostic.Kind.ERROR, error, offendingElement)
    }

    /**
     * Reports an error to the compiler, printed as a [Diagnostic.Kind.ERROR]. Note that calling
     * this function will cause compilation to abort with an error. The compiler will not be able
     * to point to any specific offending code, so prefer [reportError] if you can.
     *
     * @param error the error message that will guide the user to successful compilation.
     */
    fun reportGenericError(error: String) {
        messager.printMessage(Diagnostic.Kind.ERROR, error)
    }

}