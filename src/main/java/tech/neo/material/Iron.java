package tech.neo.material;

import tech.neo.material.core.Material;

public class Iron extends Material {

    public Iron() {
        super(5000);
    }

    @Override
    public Material clone() {
        Iron iron = new Iron();
        iron.setName(this.getName());
        iron.setDescription(this.getDescription());
        iron.setAmount(this.getAmount());
        iron.setIcon(this.getIcon());
        return iron;
    }

}
