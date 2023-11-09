package christmas.view;

import christmas.model.Order;
import christmas.model.VisitDate;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);

    void printOrderMenu(Order order);
}
