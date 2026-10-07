package org.example.wholecart.pages;

import org.example.wholecart.actions.CommonActions;
import org.example.wholecart.utils.LocatorReader;
import org.openqa.selenium.By;
import java.time.LocalDate;

public class CheckoutPage {
    private static final String LOCATOR_FILE = "checkout.json";
    private final CommonActions actions;

    private final By deliveryDatePicker = LocatorReader.get(LOCATOR_FILE, "deliveryDatePicker");
    private final By proceedToCheckout = LocatorReader.get(LOCATOR_FILE, "proceedToCheckout");
    private final By showSlots = LocatorReader.get(LOCATOR_FILE, "showSlots");
    private final By slotRadioBtn = LocatorReader.get(LOCATOR_FILE, "slotRadioBtn");
    private final By placeOrder = LocatorReader.get(LOCATOR_FILE, "placeOrder");
    private final By orderPLacedMsg = LocatorReader.get(LOCATOR_FILE, "orderPLacedMsg");

    public CheckoutPage() {
        this.actions = new CommonActions();
    }

    public void clickdeliveryDatePicker() {
        actions.click(deliveryDatePicker);
    }

    public void clickproceedToCheckout() {
        actions.click(proceedToCheckout);
    }

    public void clickshowSlots() {
        actions.click(showSlots);
    }

    public void selectslotRadioBtn() {
        actions.click(slotRadioBtn);
    }

    public void clickplaceOrder() {
        actions.click(placeOrder);
    }

    public void verifyOrderPLacedMsg() {
        actions.isDisplayed(orderPLacedMsg);
    }

    public void enterDeliveryDate(int daysToAdd) {
        String deliveryDate = LocalDate.now().plusDays(daysToAdd).toString();
        actions.setDate(deliveryDatePicker, deliveryDate);
    }

}
