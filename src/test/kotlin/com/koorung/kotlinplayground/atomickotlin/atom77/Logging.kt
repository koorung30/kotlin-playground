package com.koorung.kotlinplayground.atomickotlin.atom77

import io.klogging.Level
import io.klogging.NoCoLogging
import io.klogging.config.DEFAULT_CONSOLE
import io.klogging.config.loggingConfiguration
import org.junit.jupiter.api.Test

/**
 * 로깅
 * Java에서는 @Slf4j 라이브러리를 통해 간단하게 로깅할 수 있으나 코틀린은 Lombok을 제대로 지원하지 않기 때문에 어려움 (지금도 그런지 파악필요...)
 * 코틀린에서는 KLogging 이라는 로깅 라이브러리가 존재함
 * Klogging 인터페이스를 상속한 후 logger를 사용하면 된다.
 *
 * (상세 내용은 https://klogging.io/docs/get-started 참고할 것..)
 */
class Logging : NoCoLogging {   // Logging 인터페이스는 코루틴 전용이므로 상속하면 suspend 함수에서만 사용가능하다.

    init {
        loggingConfiguration {
            DEFAULT_CONSOLE()
            minDirectLogLevel(Level.INFO)
        }
    }
    @Test
    fun `KLogging 테스트`() {
        val msg = "This is message"
        logger.info("LOGGER ::::: $msg", msg)
    }
}