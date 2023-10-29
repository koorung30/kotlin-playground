package com.koorung.kotlinplayground.atomickotlin.atom67

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

sealed interface BeverageContainer {
    fun open(): String
    fun pour(): String
}

class Can : BeverageContainer {
    override fun open(): String = "Pop Top"
    override fun pour(): String = "Can: Pour"
}

// 상속 깊이가 여러단계라면 중간단계 클래스(또는 인터페이스)에도 sealed 키워드를 붙여줘야 의도한대로 동작한다.
sealed class Bottle : BeverageContainer {
    override fun open(): String = "Remove Cap"
    override fun pour(): String = "Bottle: Pour"
}

class GlassBottle : Bottle()
class PlasticBottle : Bottle()
class RubberBottle : Bottle()

// 인터페이스의 확장함수로 정의
fun BeverageContainer.recycle() = when (this) {
    is Can -> "Recycle Can"
    is GlassBottle -> "Recycle Glass"
    is PlasticBottle, is RubberBottle -> "Landfill" // 1. sealed 클래스일 경우 컴파일러단에서 하위클래스 정보를 알고 있으므로 branches를 추가해야함을 알려준다.
//    else -> "Landfill"  // 2. sealed가 아닐 경우 코틀린 컴파일러가 하위클래스 정보를 알 수 없기 때문에 else가 강제된다.

}

// 파라미터인 KClass<T>는 코틀린의 리플렉션을 이용하여 가져온 클래스의 정보를 담고 있다.
// isSealed, sealedSubclasses, simpleName 등의 다양한 프로퍼티가 존재...
fun <T : Any> printSubClassName(info: KClass<T>) {
    println(info.simpleName)
    if (info.isSealed) {
        info.sealedSubclasses.forEach {
            printSubClassName(it)
        }
    }
}

class Sealed {

    @Test
    fun `sealed로 선언할 경우 하위 클래스를 쉽게 이터레이션 할 수 있다`() {
        printSubClassName(BeverageContainer::class)
    }

    // 일반적으로 타입체킹코드는 안티패턴임
    // sealed를 이용하여 하위클래스 정보를 컴파일러가 알게 하는 것이 좋다.
    @Test
    fun `sealed를 이용하면 타입체크코드를 더 안전하게 구현할 수 있다`() {
        val containers = listOf(Can(), GlassBottle(), PlasticBottle())
        assertThat(containers.map { it.open() }).hasSize(3)
        assertThat(containers.map { it.open() }).isEqualTo(listOf("Pop Top", "Remove Cap", "Remove Cap"))
    }
}