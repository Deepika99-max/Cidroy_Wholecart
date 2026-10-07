package org.example.wholecart.pages;

import org.example.wholecart.actions.CommonActions;
import org.example.wholecart.utils.LocatorReader;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CataloguePage {

    private static final String LOCATOR_FILE = "catalogue.json";
    private final CommonActions actions;

    private final By headerCatalogue = LocatorReader.get(LOCATOR_FILE, "catalogueHdr");
    private final By productLink = LocatorReader.get(LOCATOR_FILE, "productLink");
    private final By quantity = LocatorReader.get(LOCATOR_FILE, "quantityBox");
    private final By addToCart = LocatorReader.get(LOCATOR_FILE, "addToCart");
    private final By netTotal = LocatorReader.get(LOCATOR_FILE, "netTotal");

    public CataloguePage() {
        this.actions = new CommonActions();
    }

    public void verifycatalogueHeader() {
        actions.isDisplayed(headerCatalogue);
    }

    public void clickProductLink(){
        actions.click(productLink);
    }

    public void enterQuantity(String value){
        actions.enterText(quantity, value);
    }

    public void clickAddToCart(){
        actions.click(addToCart);
    }

    public void verifyTotalGreaterThan2000() {
        String totalText = actions.getText(netTotal);
        double netTotal = Double.parseDouble(totalText.replace("Net total (excl. tax): ₹", "").trim());
        Assert.assertTrue(netTotal > 2000, "Net total should be greater than ₹2000, but was ₹" + netTotal);
    }


}
