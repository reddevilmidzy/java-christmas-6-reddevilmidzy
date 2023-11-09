package christmas.view;

import christmas.model.VisitDate;

public interface OutputView {
    void printWelcomeMessage();

    void printPreviewEventBenefits(VisitDate date);
}
