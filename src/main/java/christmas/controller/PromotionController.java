package christmas.controller;

import christmas.model.Orders;
import christmas.model.VisitDate;
import christmas.repository.EventRepository;
import christmas.service.Promotion;
import christmas.service.discount.ChristmasDDayDiscount;
import christmas.service.discount.DiscountPolicy;
import christmas.service.discount.SpecialDiscount;
import christmas.service.discount.WeekdayDiscount;
import christmas.service.discount.WeekendDiscount;
import christmas.service.giveaway.GiveawayPolicy;
import christmas.service.giveaway.MenuGiveawayPolicy;
import christmas.view.OutputView;
import java.util.List;

public class PromotionController {

    private final InputController inputController;
    private final OutputView outputView;

    public PromotionController(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        VisitDate visitDate = inputController.getVisitDate();
        Orders orders = inputController.getOrders();
        outputView.printEventPreviewMessage(visitDate);
        outputView.printOrders(orders);
        outputView.printTotalOrderAmountBeforeDiscount(orders);
        EventRepository eventRepository = createRepository();
        Promotion promotion = Promotion.of(eventRepository, visitDate, orders);
        outputView.printGiveawayMenus(promotion.getGiveawayMenus());
    }

    private EventRepository createRepository() {
        List<DiscountPolicy> discountPolicies = List.of(new WeekdayDiscount(),
                new SpecialDiscount(),
                new WeekendDiscount(),
                new ChristmasDDayDiscount());
        List<GiveawayPolicy> giveawayPolicies = List.of(new MenuGiveawayPolicy());
        return new EventRepository(discountPolicies, giveawayPolicies);
    }
}
