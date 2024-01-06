package software.ulpgc.controller;

import software.ulpgc.controller.Command;
import software.ulpgc.controller.ExchangeCommand;
import software.ulpgc.model.FixedAPIExchangeRateLoader;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    public void loadCommand(String name, Command command) {
        this.commands.put(name, command);
    }

    public Command getCommand(String name) {
        return this.commands.get(name);
    }
}
