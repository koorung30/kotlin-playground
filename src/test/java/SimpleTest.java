import org.assertj.core.api.Assertions;
import org.assertj.core.util.introspection.CaseFormatUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SimpleTest {

    @Test
    void test() {
        // 원본 리스트
        List<Integer> integers = new ArrayList<>(List.of(3, 7, 4, 1, 2, 1, 2, 2, 2, 3, 3, 4, 5, 2, 10, 1, 1));
        // 결과
        List<Integer> result = new ArrayList<>();

        // 원본리스트가 빌때까지 반복
        while (!integers.isEmpty()) {
            integers.stream()       // Stream
                    .distinct()     // 1. 중복제거
                    .sorted()       // 2. 정렬
                    .forEach(n -> { // 1, 2에서 중복제거 및 정렬된 비교군이 만들어지고 이것을 기준으로 forEach를 돌면서 원본리스트에서 요소 제거 && 결과 리스트에 추가
                        result.add(integers.remove(integers.indexOf(n)));
                    });
        }
        // 검증
        Assertions.assertThat(result).isEqualTo(List.of(1, 2, 3, 4, 5, 7, 10, 1, 2, 3, 4, 1, 2, 3, 1, 2, 2)); // 테스트 통과
    }


    @Test
    void test2() {

        List<Map<String, Integer>> result = new ArrayList<>(List.of(
                Map.of("A", 1, "B", 2, "C", 3, "D", 4, "E", 5),
                Map.of("A", 10, "B", 20, "C", 30, "D", 40, "E", 50)));

        System.out.println("before :::: " + result);

        // result의 키값 교체
        List<Map<String, Integer>> newResult = new ArrayList<>();

        for (Map<String, Integer> map : result) {
            Map<String, Integer> newMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey() + "_IX";
                Integer value = entry.getValue();
                newMap.put(key, value);
            }
            newResult.add(newMap);
        }

        System.out.println(CaseFormatUtils.toCamelCase("\'A\'_IX"));
        System.out.println("after :::: " + newResult);
    }
}
