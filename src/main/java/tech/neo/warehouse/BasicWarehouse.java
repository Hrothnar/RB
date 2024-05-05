package tech.neo.warehouse;

import tech.neo.material.core.Material;
import tech.neo.material.dto.MaterialDTO;
import tech.neo.warehouse.core.Warehouse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicWarehouse implements Warehouse {
    private Map<String, Material> materials;

    public BasicWarehouse(Map<String, Material> materials) {
        this.materials = materials;
    }

    @Override
    public void addMaterial(Material material) {
        materials.put(material.getName(), material);
    }

    @Override
    public void addMaterials(Material... materials) {
        Arrays.stream(materials).forEach((material -> this.materials.put(material.getName(), material)));
    }

    @Override
    public void addMaterials(List<Material> materials) {
        materials.forEach((material -> this.materials.put(material.getName(), material)));
    }

    @Override
    public void updateMaterialAmount(Material material) {
        Optional<Material> optional = Optional.ofNullable(materials.get(material.getName()));
        optional.ifPresent((element) -> element.setAmount(material.getAmount()));
    }

    @Override
    public void updateMaterialsAmount(Material... materials) {
        Arrays.stream(materials).forEach((material -> {
            Optional<Material> optional = Optional.ofNullable(this.materials.get(material.getName()));
            optional.ifPresent((element) -> element.setAmount(material.getAmount()));
        }));
    }

    @Override
    public void updateMaterialsAmount(List<Material> materials) {
        materials.forEach((material -> {
            Optional<Material> optional = Optional.ofNullable(this.materials.get(material.getName()));
            optional.ifPresent((element) -> element.setAmount(material.getAmount()));
        }));
    }

    @Override
    public Optional<Material> getMaterial(String name) {
        return Optional.ofNullable(materials.get(name));
    }

    @Override
    public List<Material> getMaterials(String... names) {
        return Arrays.stream(names)
                .map((name) -> materials.get(name))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Material> getMaterials(List<String> names) {
        return names.stream()
                .map((name) -> materials.get(name))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeMaterial(String name) {
        Material removed = materials.remove(name);
        return Objects.nonNull(removed);
    }

    @Override
    public void removeMaterials(String... names) {
        Arrays.stream(names).forEach((name) -> materials.remove(name));
    }

    @Override
    public void removeMaterials(List<String> names) {
        names.forEach((name) -> materials.remove(name));
    }

    @Override
    public void removeAllMaterials() {
        materials = new HashMap<>();
    }


    @Override
    public void migrateMaterials(Warehouse from, MaterialDTO... materials) {
        migrate(from, Stream.of(materials));
    }

    @Override
    public void migrateMaterials(Warehouse from, List<MaterialDTO> materials) {
        migrate(from, materials.stream());
    }

    private void migrate(Warehouse from, Stream<MaterialDTO> stream) {
        try {
            stream.forEach((materialDTO -> {
                Optional<Material> fromOptional = from.getMaterial(materialDTO.name);
                Material fromMaterial = fromOptional.orElseThrow(() -> new RuntimeException("No such material in warehouse"));
                Material toMaterial = getMaterial(materialDTO.name).or(() -> {
                    Material clonedMaterial = fromMaterial.clone();
                    clonedMaterial.setAmount(0);
                    addMaterial(clonedMaterial);
                    return getMaterial(clonedMaterial.getName());
                }).get();
                if (toMaterial.getAmount() < toMaterial.getCapacityLimit() && materialDTO.amountForMigration <= fromMaterial.getAmount()) {
                    int added = toMaterial.addAmountToMaterial(materialDTO.amountForMigration);
                    fromMaterial.setAmount(fromMaterial.getAmount() - added);
                }
            }));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void writeDownStocks() {
        try {
            Files.write(Path.of("./src/main/java/tech/neo/util/stock.log"), this.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "BasicWarehouse{" +
                "materials=" + materials +
                '}';
    }
}
