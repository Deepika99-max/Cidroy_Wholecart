package org.example.wholecart.pages;

public class PageManager {

    private LoginPage loginPage;
    private CataloguePage cataloguePage;
    private CheckoutPage checkoutPage;

    public LoginPage getLoginPage() {

        if (loginPage == null) {
            loginPage = new LoginPage();
        }

        return loginPage;
    }

    public CataloguePage getCataloguePage() {

        if (cataloguePage == null) {
            cataloguePage = new CataloguePage();
        }

        return cataloguePage;
    }

    public CheckoutPage getCheckoutPage() {

        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage();
        }

        return checkoutPage;
    }
}