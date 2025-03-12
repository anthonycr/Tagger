# Tagger
A Kotlin symbol processor example

## Abstract

This Kotlin symbol processor acts as an example of how to generate Kotlin code using the Kotlin Symbol Processing (KSP) framework. Using KSP, we can generate idiomatic Kotlin code. This example generates tag properties which are commonly used for logging, as extensions of the annotated class.

## Gradle

```kotlin
plugins {
  id("com.google.devtools.ksp")
}

dependencies {
  ksp(project(":tagger-compiler"))
  implementation(project(":tagger")
}
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
The symbol processor generates an extension property for the annotated class.
```kotlin
val HelloWorld.TAG: String
  get() = "HelloWorld"
```

## License
`tagger` is available under the MIT license. See the [LICENSE](LICENSE) file for more information.
