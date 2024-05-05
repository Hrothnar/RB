package tech.neo.warehouse.core;

import tech.neo.material.core.Material;
import tech.neo.material.dto.MaterialDTO;

import java.util.List;
import java.util.Optional;

public interface Warehouse {

    public void addMaterial(Material material);

    public void addMaterials(Material... materials);

    public void addMaterials(List<Material> materials);


    public void updateMaterialAmount(Material material);

    public void updateMaterialsAmount(Material... materials);

    public void updateMaterialsAmount(List<Material> materials);


    public Optional<Material> getMaterial(String name);

    public List<Material> getMaterials(String... names);

    public List<Material> getMaterials(List<String> names);


    public boolean removeMaterial(String name);

    public void removeMaterials(String... names);

    public void removeMaterials(List<String> names);

    public void removeAllMaterials();


    public void migrateMaterials(Warehouse from, MaterialDTO... materials);

    public void migrateMaterials(Warehouse from, List<MaterialDTO> materials);


    public void writeDownStocks();


}
