package tech.neo.material.core;

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
        } else {
            this.icon = icon;
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount < 0 || amount > capacityLimit) {
            throw new IllegalArgumentException("Amount should be correct number");
        }
        this.amount = amount;
    }

    public int addAmountToMaterial(int amount) {
        if (amount == capacityLimit) {
            throw new RuntimeException("Storage already full"); // this and other generic exceptions could/should be changed to specific ones
        }
        if (amount <= 0 || amount > capacityLimit) {
            throw new IllegalArgumentException("Amount should be correct number");
        }
        int available = capacityLimit - this.amount;
        if (amount <= available) {
            this.amount += amount;
            return amount;
        } else {
            this.amount += available;
            return available;
        }
    }

    public int getCapacityLimit() {
        return capacityLimit;
    }

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

    @Override
    public abstract Material clone();
}
