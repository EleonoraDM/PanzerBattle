package io;

import core.interfaces.Manager;
import core.ManagerImpl;

import java.util.List;

public class CommandProcessor {
    private Manager manager;

    public CommandProcessor() {
        this.manager = new ManagerImpl();
    }

    public String executeCommand(String command, List<String> arguments) {
        String result = null;

        if (command.toUpperCase().equals(Commands.VEHICLE.name())) {
            result = this.manager.addVehicle(arguments);
        } else if (command.toUpperCase().equals(Commands.PART.name())) {
            result = this.manager.addPart(arguments);
        } else if (command.toUpperCase().equals(Commands.INSPECT.name())) {
            result = this.manager.inspect(arguments);
        } else if (command.toUpperCase().equals(Commands.BATTLE.name())) {
            result = this.manager.battle(arguments);
        } else if (command.toUpperCase().equals(Commands.TERMINATE.name())) {
            result = this.manager.terminate(arguments);
        } else {
            throw new IllegalArgumentException("Invalid Command");
        }
        return result;
    }
}
