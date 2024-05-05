package tech.neo.material.dto;

public class MaterialDTO {
    public String name;
    public int amountForMigration;
    public int migratedAmount;

    public MaterialDTO(String name, int amountForMigration) {
        this.name = name;
        this.amountForMigration = amountForMigration;
    }

    @Override
    public String toString() {
        return "MaterialDTO{" +
                "name='" + name + '\'' +
                ", amountForMigration=" + amountForMigration +
                ", migratedAmount=" + migratedAmount +
                '}';
    }
}
