package core.interfaces;

import models.vehicles.Vehicle;

public interface BattleOperator {
    // Performs a turn-based battle between 2 Vehicles
    String battle(Vehicle attacker, Vehicle target);
}
