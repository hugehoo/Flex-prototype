```java
    // 같은날에 휴일을 등록할 수 없다.
@Test
    void Should_DayOff_Date_Different(){
            // 중복체크를 어떻게 할 것인가  -> hash 를 사용하면 그냥 덮어버린다.
            // save 할 때, 국가 지정 공휴일을 덮어버리는 경우는 엎어야 하기에, hash 는 안된다.
            // 리스트를 사용할까. contains() 메서드
            }


    @Test
    void Should_NullPointException_When_NewDayOff_Null(){
            assertThrows(NullPointerException.class,()->dayOffRepository.saveNewOffDays(null));
        // 규약으로는 nonnull 을 드러냈지만, 이걸 강제하는 기능은 없는 상태.
        // non null 이라고 규약을 드러냈을뿐, null 이 들어올 수도 있는 상태
        }
```

- 연휴 중복 체크
- 같은걸 어떻게 체크하지?
- hashmap 하면 기존의 휴일을 그냥 덮어버리기 때문에 경고같은걸 보여줄 수도 없다.
- 그러면 어떤 컬렉션을 사용해야하는가. 링크드 리스트? -> ㄴㄴ 자동으로 정렬되지 않아. 그냥 sort 치는게 낫다 . 데이터크기가 큰것도 아니므로.
