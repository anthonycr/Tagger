package com.anthonycr.tagger

/**
 * An annotation to tag classes which need a tag generated.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Tag
