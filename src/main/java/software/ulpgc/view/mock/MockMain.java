package software.ulpgc.view.mock;

import software.ulpgc.controller.CommandManager;
import software.ulpgc.controller.ExchangeCommand;
import software.ulpgc.model.Currency;
import software.ulpgc.model.FixedAPIExchangeRateLoader;
import software.ulpgc.model.FixerAPICurrencyLoader;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.MoneyDisplay;

import java.util.Set;

public class MockMain {

    private static Set<Currency> currencies;
    private static MoneyDialog moneyDialog;
    private static CurrencyDialog currencyDialog;
    private static MoneyDisplay moneyDisplay;
    private static final CommandManager commandManager = new CommandManager();

    public static void main(String[] args) {
        loadCurrencies();
        createComponents();
        loadComponents();
        loadCommands();
        makeExchange();
    }

    private static void makeExchange() {
        commandManager.getCommand("exchange").execute();
    }

    private static void loadCommands() {
        commandManager.loadCommand("exchange", new ExchangeCommand(moneyDialog, currencyDialog,
                new FixedAPIExchangeRateLoader(), moneyDisplay));
    }

    private static void loadComponents() {
        moneyDialog.setCurrencies(currencies);
        currencyDialog.setCurrencies(currencies);
    }

    private static void createComponents() {
        moneyDialog = new MockMoneyDialog("ARS", 80);
        currencyDialog = new MockCurrencyDialog("EUR");
        moneyDisplay = new MockMoneyDisplay();
    }

    private static void loadCurrencies() {
        currencies = new FixerAPICurrencyLoader().loadCurrencies();
    }
}
