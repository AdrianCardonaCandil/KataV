package software.ulpgc.controller;

import software.ulpgc.model.*;
import software.ulpgc.view.CurrencyDialog;
import software.ulpgc.view.MoneyDialog;
import software.ulpgc.view.MoneyDisplay;
import software.ulpgc.view.swing.*;

public class ExchangeCommand implements Command{

    private final CurrencyDialog currencyDialog;
    private final MoneyDialog moneyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog,
                           ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay) {
        this.currencyDialog = currencyDialog;
        this.moneyDialog = moneyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money baseMoney = moneyDialog.get();
        Currency quoteCurrency = currencyDialog.get();
        ExchangeRate exchangeRate = exchangeRateLoader.loadExchangeRate(baseMoney.currency(),
                quoteCurrency);
        this.moneyDisplay.display(new Money(quoteCurrency, baseMoney.amount() *
                exchangeRate.rate()));
    }
}
