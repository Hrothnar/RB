package tech.neo.material.dto;

public class MaterialDTO {
    public String name;
    public int amountForMigration;

    public MaterialDTO(String name, int amountForMigration) {
        this.name = name;
        this.amountForMigration = amountForMigration;
    }
}
