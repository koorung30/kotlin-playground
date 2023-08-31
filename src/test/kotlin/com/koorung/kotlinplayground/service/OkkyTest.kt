package com.koorung.kotlinplayground.service

import org.junit.jupiter.api.Test

class OkkyTest {

    @Test
    fun test1() {
        val result = mutableListOf<Int>() // 결과
        val added = mutableListOf<Int>()  // 추가된 숫자리스트
        val remain = mutableListOf<Int>() // 남아있는 숫자리스트
        val list = mutableListOf(3, 7, 4, 1, 2, 1, 2, 2, 2, 3, 3, 4, 5, 2, 10, 1, 1)

        // 정렬
        list.sort()

        // list가 빌 때 까지 반복
        while(list.isNotEmpty()){
            // added에 list.first()가 존재한다면
            if(added.contains(list.first())) {
                // remain에 list.first()를 추가 && list에서 제거
                remain.add(list.removeFirst())
            } else {
                // 아닐경우 added에 list.first()를 추가 && list에서 제거
                added.add(list.removeFirst())
            }

            // list가 비어있을 경우
            // list에 remain의 요소 모두 추가
            // result에 added의 요소 모두 추가
            if(list.isEmpty()) {
                list.addAll(remain)
                result.addAll(added)
                // remain과 added 초기화
                remain.clear()
                added.clear()
            }
        }

        print(result) // [1, 2, 3, 4, 5, 7, 10, 1, 2, 3, 4, 1, 2, 3, 1, 2, 2]
    }

    @Test
    fun test2(){
        val list = mutableListOf(3, 7, 4, 1, 2, 1, 2, 2, 2, 3, 3, 4, 5, 2, 10, 1, 1)
        val result = mutableListOf<Int>()
        while(list.isNotEmpty()) {
            list.distinct().sorted().forEach {
                result.add(list.removeAt(list.indexOf(it)))
            }
        }
        print(result)
    }
}