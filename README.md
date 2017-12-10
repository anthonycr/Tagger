# Tagger
An annotation processor example, written in Kotlin, generating Kotlin

## Abstract

This annotation processor acts as an example of how to generate Kotlin code using the annotation processing framework. Using Kapt, we can generate idiomatic Kotlin code, which means annotation processors become more powerful with access to features like extension functions and properties. This example generates tag properties which are commonly used for logging, and unlike if they were generated from a Java specific annotation processor, this example is able to generate the tag properties as extensions of the annotated class.

## Gradle

```groovy
apply plugin: 'kotlin-kapt'

kapt project(':tagger-compiler')
compile project(':tagger')
```

## Usage

Apply the `@Tag` annotation to a class, and Tagger will auto-generate a log tag for the class.
```kotlin
@Tag
class HelloWorld {

  fun hello() {
    println("$TAG - is the class name")
  }

}
```
The annotation processor generates an extension property for the annotated class.
```kotlin
val HelloWorld.TAG: String
  get() = "HelloWorld"
```

## License
`tagger` is available under the MIT license. See the [LICENSE](LICENSE) file for more information.
