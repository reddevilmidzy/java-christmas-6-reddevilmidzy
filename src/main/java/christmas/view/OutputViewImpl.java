package christmas.view;

import christmas.model.VisitDate;

public class OutputViewImpl implements OutputView {

    @Override
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void printPreviewEventBenefits(VisitDate date) {
        //TODO: 12월 하드 코딩 제거하기
        System.out.printf("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date.getDate());
    }
}
