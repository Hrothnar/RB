package tech.neo.material;

import tech.neo.material.core.Material;

public class Cooper extends Material {

    public Cooper() {
        super(4000);
    }

    @Override
    public Material clone() {
        Cooper cooper = new Cooper();
        cooper.setName(this.getName());
        cooper.setDescription(this.getDescription());
        cooper.setAmount(this.getAmount());
        cooper.setIcon(this.getIcon());
        return cooper;
    }

}
