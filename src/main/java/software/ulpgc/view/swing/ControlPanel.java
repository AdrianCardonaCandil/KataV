package software.ulpgc.view.swing;

import software.ulpgc.model.Currency;
import software.ulpgc.model.FixerAPICurrencyLoader;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ControlPanel extends JPanel {

    private final Set<Currency> currencies = new FixerAPICurrencyLoader().loadCurrencies();
    private final SwingMoneyDialog moneyDialog = new SwingMoneyDialog();
    private final SwingCurrencyDialog quoteCurrencyDialog = new SwingCurrencyDialog();
    private final JLabel label = new JLabel("Now, select the target currency");

    public ControlPanel() {
        this.initSetup();
        this.loadComponents();
    }

    public void addAndSetupComponents() {
        this.setSize(this.getParent().getWidth() / 2, this.getParent().getHeight());
        this.add(moneyDialog);
        this.add(quoteCurrencyDialog);
        this.add(label);
        this.setComponentsBounds();
    }

    private void setComponentsBounds() {
        this.moneyDialog.setBounds(0, 0, this.getWidth(), this.getHeight() / 2);
        this.moneyDialog.getTextLabel().setBounds(75, 55, 250, 20);
        this.moneyDialog.getCurrencyDialog().setBounds(25, 80, 220, 20);
        this.moneyDialog.getTextField().setBounds(245, 79, 75, 20);
        this.label.setBounds(75, (this.getHeight() / 2) + 50, 250, 20);
        this.quoteCurrencyDialog.setBounds(25, (this.getHeight() / 2) + 75, 300, 20);
    }

    public void loadComponents() {
        this.moneyDialog.setCurrencies(currencies);
        this.quoteCurrencyDialog.setCurrencies(currencies);
    }

    private void initSetup() {
        this.setLayout(null);
        this.setBackground(Color.darkGray);
        this.label.setForeground(Color.white);
    }

    public SwingMoneyDialog getMoneyDialog() {
        return moneyDialog;
    }
    public SwingCurrencyDialog getQuoteCurrencyDialog() {
        return quoteCurrencyDialog;
    }
}
