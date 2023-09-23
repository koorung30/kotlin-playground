import org.assertj.core.api.Assertions;
import org.assertj.core.util.Maps;
import org.assertj.core.util.introspection.CaseFormatUtils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(result).isEqualTo(List.of(1, 2, 3, 4, 5, 7, 10, 1, 2, 3, 4, 1, 2, 3, 1, 2, 2)); // 테스트 통과
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

    @Test
    void test3() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int x = 5;
        int k = 5;

        // 1. int[] -> List<Integer> 변환
        List<Integer> toList = Arrays.stream(arr).boxed().toList();
        // 2. Stream 열기
        List<Integer> result = toList.stream()
                // 3. [abs(diff), value] List 생성
                .map(i -> List.of(Math.abs(i - x), i))
                // 4. 두 List를 비교하는 Comparator로 정렬
                .sorted((l1, l2) -> {
                    // 4-1. abs(diff) 비교조건
                    int first = l1.get(0) - l2.get(0);
                    // 4-2. abs(diff)가 같을 경우 value 비교조건
                    int second = l1.get(1) - l2.get(1);
                    // abs(diff)가 같을 때 (차이가 0일때) 2번 조건으로, 아니면 1번 조건으로 정렬
                    return first == 0 ? second : first;
                })
                // 5. k개 가져오기
                .limit(k)
                // 6. List[abs(diff), value] -> List[value]로 변경
                .map(l -> l.get(1))
                // 7. 재정렬
                .sorted()
                .toList();

        System.out.println("result = " + result);   // [3, 4, 5, 6, 7]


//        Map<Integer, Integer> map = Map.of(1, 2);           // key, value 한쌍
//        Stream<Integer> stream = map.keySet().stream();             // Stream<Integer>
//        Optional<Integer> min = stream.min((a, b) -> a - b);        // min(또는 max)로 Optional<Integer>
//        Integer i = min.get();                                      // .get()으로 Optional 제거
//        System.out.println("min = " + i);                           // "min = 1"
    }
}
