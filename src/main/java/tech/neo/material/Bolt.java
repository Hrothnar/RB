package tech.neo.material;

import tech.neo.material.core.Material;

public class Bolt extends Material {

    public Bolt() {
        super(6000);
    }

    @Override
    public Material clone() {
        Bolt bolt = new Bolt();
        bolt.setName(this.getName());
        bolt.setDescription(this.getDescription());
        bolt.setAmount(this.getAmount());
        bolt.setIcon(this.getIcon());
        return bolt;
    }
}
