package tech.neo.observer.imp;

import tech.neo.material.core.Material;
import tech.neo.observer.MaterialObserver;
import tech.neo.warehouse.core.Warehouse;

import java.util.List;

public class StockManager implements MaterialObserver {

    @Override
    public void receiveLeftovers(List<Material> materials, Warehouse warehouse) {
        // proper logic
        System.out.println(this.getClass().getSimpleName() + " received material list and now is doing something useful with it");
    }

    @Override
    public void receiveLeftovers(Material material, Warehouse warehouse) {
        // proper logic
        System.out.println(this.getClass().getSimpleName() + " received material leftover and now is doing something useful with it");
        System.out.println("Current amount: " + material.getAmount() + " Max amount: " + material.getCapacityLimit() + " Warehouse: " + warehouse.getClass().getSimpleName());
    }
}
