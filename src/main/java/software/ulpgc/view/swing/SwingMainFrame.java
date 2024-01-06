package software.ulpgc.view.swing;

import software.ulpgc.controller.*;
import software.ulpgc.model.FixedAPIExchangeRateLoader;
import software.ulpgc.controller.CommandManager;

import javax.swing.*;
import java.awt.*;

public class SwingMainFrame extends JFrame {

    private final OperationalPanel operationalPanel = new OperationalPanel();
    private final DisplayPanel displayPanel = new DisplayPanel();
    private final CommandManager commandManager = new CommandManager();

    public static void main(String[] args) {
        SwingMainFrame mainFrame = new SwingMainFrame();
        mainFrame.setVisible(true);
    }

    public SwingMainFrame() throws HeadlessException {
        this.initSetup();
        this.addAndSetupComponents();
        this.addCommands();
        this.setListeners();
    }

    private void setListeners() {
        this.displayPanel.getButton().addActionListener(e -> {
            this.displayPanel.getDisplay().setText(null);
            this.commandManager.getCommand("exchange").execute();
        });
    }

    private void addCommands() {
        this.commandManager.loadCommand("exchange", new ExchangeCommand(this.operationalPanel.getControlPanel().getMoneyDialog(),
                this.operationalPanel.getControlPanel().getQuoteCurrencyDialog(), new FixedAPIExchangeRateLoader(),
                this.displayPanel.getDisplay()));
    }

    private void setupSubPanels() {
        this.operationalPanel.addAndSetupComponents();
        this.displayPanel.addAndSetupComponents();
    }

    private void addAndSetupComponents() {
        this.add(operationalPanel);
        this.add(displayPanel);
        this.setupSubPanels();
    }

    private void initSetup() {
        this.setTitle("Currency Exchange");
        this.setSize(700, 730);
        this.getContentPane().setSize(700, 730);
        this.setLayout(new GridLayout(2, 1));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
