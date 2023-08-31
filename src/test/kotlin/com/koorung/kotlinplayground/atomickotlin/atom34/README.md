## Atom34 : enum class

```kotlin
enum class Level {
    OVERFLOW,
    HIGH,
    MEDIUM,
    LOW,
    EMPTY
}
```
기본적인 enum class의 선언방법

<br/>

```kotlin
enum class LevelWithMember(
    val order: Int
){
    OVERFLOW(1),
    HIGH(2),
    MEDIUM(3),
    LOW(4),
    EMPTY(5);   // 추가 멤버를 선언할 때는 마지막 enum 뒤에 세미콜론을 붙여 구분짓는다.

    fun withPlusTen() = order + 10
}
```
enum class는 멤버함수와 프로퍼티를 가질 수 있다.

<br/>

```kotlin
val enums = Level.values()
assertThat(enums).hasSize(5)                        // true
assertThat(enums).isEqualTo(enumValues<Level>())    // true
```
코틀린에서는 enumValues<T> 와 같은 다양한 편의함수를 제공한다.