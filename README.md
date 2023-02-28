# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰
- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 기능요구사항

## Model
player, players, card, cards, Result 

- [ ] 랜덤으로 카드를 가져온다.
- [ ] A는 1또는 11로 계산할 수 있다.
  - [ ] A를 11로 취급했는데, 새 카드를 뽑아서 21이 넘으면 A를 1로 취급한다.
- [ ] King, Queen, Jack은 각각 10으로 계산한다.
- [ ] 딜러는 처음에 받은 2장의 합계가 16이하이면 1장의 카드를 추가로 받아야한다.
  - [ ] 17 이상이면 추가로 받을 수 없다.

## View
### IN
- [ ] 게임에 참여하는 플레이어의 이름을 입력받는다. 
- [ ] 플레이어에게 카드를 나눠준다.

### OUT
- [ ] 중간 게임 결과를 출력한다.
- [ ] 최종 게임 결과를 출력한다.

## Controller
