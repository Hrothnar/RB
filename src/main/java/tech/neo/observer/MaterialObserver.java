package tech.neo.observer;

import tech.neo.material.core.Material;
import tech.neo.warehouse.core.Warehouse;

import java.util.List;

public interface MaterialObserver extends Observer {

    void receiveLeftovers(List<Material> materials, Warehouse warehouse);

    void receiveLeftovers(Material material, Warehouse warehouse);
}
