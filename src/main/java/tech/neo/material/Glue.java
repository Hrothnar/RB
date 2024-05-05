package tech.neo.material;

import tech.neo.material.core.Material;

public class Glue extends Material {

    public Glue() {
        super(750);
    }

    @Override
    public Material clone() {
        Glue glue = new Glue();
        glue.setName(this.getName());
        glue.setDescription(this.getDescription());
        glue.setAmount(this.getAmount());
        glue.setIcon(this.getIcon());
        return glue;
    }

}
