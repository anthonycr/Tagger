package com.anthonycr.tagger

/**
 * An annotation to tag classes which need a tag generated.
 *
 * @param caseStyle the capitalization style that should be used when generating the tag.
 * @see CaseStyle
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Tag(
        val caseStyle: CaseStyle = CaseStyle.NORMAL
)