package tech.neo.observer;

import tech.neo.material.core.Material;

public interface MaterialObservable extends Observable {

    void notifyAboutLeftovers();

    void notifyAboutLowLeftover(Material material);

}
