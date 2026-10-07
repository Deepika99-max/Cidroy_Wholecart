package org.example.wholecart.tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.wholecart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private final Dotenv dotenv = Dotenv.load();

    @Test()
    public void verifyBuyerLogin() {


        String username = dotenv.get("BUYER_USERNAME");
        String password = dotenv.get("BUYER_PASSWORD");
        String url = dotenv.get("URL");

        driver.get(url);
        pages.getLoginPage().login(username, password);
        pages.getCataloguePage().verifycatalogueHeader();
        pages.getCataloguePage().clickProductLink();
        pages.getCataloguePage().enterQuantity("5");
        pages.getCataloguePage().clickAddToCart();
        pages.getCataloguePage().verifyTotalGreaterThan2000();
        pages.getCheckoutPage().clickproceedToCheckout();
        pages.getCheckoutPage().clickdeliveryDatePicker();
        pages.getCheckoutPage().enterdeliveryDate("08","10","2026");
        pages.getCheckoutPage().clickshowSlots();
        pages.getCheckoutPage().selectslotRadioBtn();
        pages.getCheckoutPage().clickplaceOrder();
        pages.getCheckoutPage().verifyOrderPLacedMsg();

    }
}