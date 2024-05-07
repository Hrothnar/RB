package tech.neo.material.core;

import tech.neo.exception.FullStorageEx;
import tech.neo.exception.NotValidArgumentEx;
import tech.neo.util.logger.Journal;

import java.util.Objects;

public abstract class Material implements Cloneable {
    private final int capacityLimit;
    private String name;
    private String description;
    private String icon;
    private int amount;

    public Material(int capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            this.name = "Default Material Name";
            Journal.LOGGER.info("Material name has been set as default value");
        } else {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (Objects.isNull(description) || description.isBlank()) {
            this.description = "Default Material Description";
            Journal.LOGGER.info("Material description has been set as default value");
        } else {
            this.description = description;
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        if (Objects.isNull(icon) || icon.isBlank()) {
            this.icon = "Default Material Icon";
            Journal.LOGGER.info("Material icon has been set as default value");
        } else {
            this.icon = icon;
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0 || amount > capacityLimit) {
            try {
                throw new NotValidArgumentEx("Amount should be correct number");
            } catch (NotValidArgumentEx ex) {
                Journal.LOGGER.warning(ex.getMessage());
            }
        }
        this.amount = amount;
    }

    /**
     * Adds to material 'amount' passed number until it reach capacity limit
     *
     * @param amount
     * @return amount of added materials
     */
    public int addAmountToMaterial(int amount) {
        int added = 0;
        try {
            if (amount == capacityLimit) {
                throw new FullStorageEx("Storage is already full");
            }
            if (amount <= 0 || amount > capacityLimit) {
                throw new NotValidArgumentEx("Amount should be correct number");
            }
            int available = capacityLimit - this.amount;
            if (amount <= available) {
                this.amount += amount;
                added = amount;
            } else {
                this.amount += available;
                added = available;
            }
        } catch (FullStorageEx | NotValidArgumentEx ex) {
            Journal.LOGGER.warning(ex.getMessage());
        }
        return added;
    }

    public int getCapacityLimit() {
        return capacityLimit;
    }

    @Override
    public abstract Material clone();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return amount == material.amount && capacityLimit == material.capacityLimit && Objects.equals(name, material.name) && Objects.equals(description, material.description) && Objects.equals(icon, material.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, icon, amount, capacityLimit);
    }

    @Override
    public String toString() {
        return "Material{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", icon='" + icon + '\'' +
                ", amount=" + amount +
                ", capacityLimit=" + capacityLimit +
                '}';
    }


}
