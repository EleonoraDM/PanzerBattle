package core;

import core.interfaces.BattleOperator;
import core.interfaces.Manager;
import common.OutputMessages;
import models.parts.ArsenalPart;
import models.parts.EndurancePart;
import models.parts.Part;
import models.parts.ShellPart;
import models.vehicles.Revenger;
import models.vehicles.Vanguard;
import models.vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerImpl implements Manager {
    private List<Vehicle> vehicles;
    private List<Vehicle> defeatedVehicles;
    private BattleOperator battleOperator;

    public ManagerImpl() {
        this.vehicles = new LinkedList<>();
        this.defeatedVehicles = new LinkedList<>();
        this.battleOperator = new PanzerBattleOperator();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        String vehicleType = arguments.get(0);
        String model = arguments.get(1);
        double weight = Double.parseDouble(arguments.get(2));
        BigDecimal price = new BigDecimal(arguments.get(3));
        int attack = Integer.parseInt(arguments.get(4));
        int defense = Integer.parseInt(arguments.get(5));
        int hitPoints = Integer.parseInt(arguments.get(6));

        Vehicle vehicle = null;

        if (vehicleType.equals("Vanguard")) {
            vehicle = new Vanguard(model, weight, price, attack, defense, hitPoints);
        } else if (vehicleType.equals("Revenger")) {
            vehicle = new Revenger(model, weight, price, attack, defense, hitPoints);
        }
        this.vehicles.add(vehicle);

        return String.format(OutputMessages.VEHICLE_CREATED, vehicleType, model);
    }

    @Override
    public String addPart(List<String> arguments) {
        String vehicleModel = arguments.get(0);
        String partType = arguments.get(1);
        String model = arguments.get(2);
        double weight = Double.parseDouble(arguments.get(3));
        BigDecimal price = new BigDecimal(arguments.get(4));
        int additionalParameter = Integer.parseInt(arguments.get(5));

        this.vehicles.
                stream().
                filter(vehicle -> vehicle.getModel().equals(vehicleModel)).
                findFirst().
                ifPresent(searchedVehicle -> addPartToGivenVehicle
                        (partType, model, weight, price, additionalParameter, searchedVehicle));

        return String.format(OutputMessages.PART_ADDED, partType, model, vehicleModel);
    }

    private void addPartToGivenVehicle(String partType, String model, double weight, BigDecimal price, int additionalParameter, Vehicle searchedVehicle) {
        Part part = null;

        if (partType.equals("Arsenal")) {
            part = new ArsenalPart(model, weight, price, additionalParameter);
            searchedVehicle.addArsenalPart(part);
        } else if (partType.equals("Shell")) {
            part = new ShellPart(model, weight, price, additionalParameter);
            searchedVehicle.addShellPart(part);
        } else if (partType.equals("Endurance")) {
            part = new EndurancePart(model, weight, price, additionalParameter);
            searchedVehicle.addEndurancePart(part);
        }
    }

    @Override
    public String inspect(List<String> arguments) {
        String model = arguments.get(0);
        String result = null;

        Vehicle searchedVehicle = this.vehicles.
                stream().
                filter(vehicle -> vehicle.getModel().equals(model)).
                findFirst().orElse(null);

        if (searchedVehicle != null) {
            result = searchedVehicle.toString();
        } else {
            result = searchPartWithTheGivenModel(model);
        }
        return result;
    }

    private String searchPartWithTheGivenModel(String model) {
        Part searchedPart = null;
        String result = null;

        for (Vehicle vehicle : vehicles) {
            Part currPart = vehicle.getParts().iterator().next();
            if (currPart.getModel().equals(model)) {
                searchedPart = currPart;
                break;
            }
        }
        if (searchedPart != null) {
            result = searchedPart.toString();
        }
        return result;
    }

    @Override
    public String battle(List<String> arguments) {
        String vehicle1Model = arguments.get(0);
        String vehicle2Model = arguments.get(1);

        Vehicle attacker = this.vehicles.stream().
                filter(vehicle -> vehicle.getModel().equals(vehicle1Model)).
                findFirst().orElse(null);

        Vehicle defender = this.vehicles.stream().
                filter(vehicle -> vehicle.getModel().equals(vehicle2Model)).
                findFirst().orElse(null);

        String winnerModel = this.battleOperator.battle(attacker, defender);

        removeLoser(attacker, defender, winnerModel);

        return String.format(OutputMessages.BATTLE_RESULT, vehicle1Model, vehicle2Model, winnerModel);
    }

    private void removeLoser(Vehicle attacker, Vehicle defender, String winnerModel) {

        if (attacker != null) {
            if (winnerModel.equals(attacker.getModel())) {
                this.vehicles.remove(defender);
                this.defeatedVehicles.add(defender);
            } else {
                this.vehicles.remove(attacker);
                this.defeatedVehicles.add(attacker);
            }
        }
    }

    @Override
    public String terminate(List<String> arguments) {
        StringBuilder sb = new StringBuilder();

        sb.append("Remaining Vehicles: ");
        sb.append(this.vehicles.isEmpty()
                ? "None"
                : this.vehicles.stream().map(Vehicle::getModel).collect(Collectors.joining(", "))).
                append(System.lineSeparator());

        sb.append("Defeated Vehicles: ");
        sb.append(this.defeatedVehicles.isEmpty()
                ? "None"
                : this.defeatedVehicles.stream().map(Vehicle::getModel).collect(Collectors.joining(", "))).
                append(System.lineSeparator());

        int usedParts = 0;
        for (Vehicle vehicle : vehicles) {
            usedParts += ((Collection<?>) vehicle.getParts()).size();
        }
        sb.append(String.format("Currently Used Parts: %d", usedParts));

        return sb.toString();
    }
}
