package tech.neo;

import tech.neo.material.Bolt;
import tech.neo.material.Cooper;
import tech.neo.material.Glue;
import tech.neo.material.Iron;
import tech.neo.material.core.Material;
import tech.neo.material.dto.MaterialDTO;
import tech.neo.util.logger.Journal;
import tech.neo.warehouse.BasicWarehouse;
import tech.neo.warehouse.core.Warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Journal.setupLogger();
        Journal.LOGGER.info("START");


//        testAddMaterials();
//        testUpdateMaterials();
//        testGetMaterials();
        testMigrateMaterials();
//        testRemoveMaterials();


    }

    private static void testRemoveMaterials() {
        Warehouse warehouse = generateWarehouse();

        System.out.println(warehouse);
        System.out.println();

        warehouse.removeMaterial("Glue");

        System.out.println(warehouse);
        System.out.println();
        warehouse.removeMaterials("Glue", "Bolt");

        System.out.println(warehouse);
        System.out.println();
        warehouse.removeMaterials(List.of("Bolt"));

        System.out.println(warehouse);
        System.out.println();
        warehouse.removeAllMaterials();

        System.out.println(warehouse);
        System.out.println();
    }

    private static void testMigrateMaterials() {
        Warehouse warehouse1 = generateWarehouse();
        Warehouse warehouse2 = generateWarehouse();

        System.out.println();
        System.out.println(warehouse1);
        System.out.println(warehouse2);
        System.out.println();


        Material bolt = warehouse2.getMaterial("Bolt").get();
        MaterialDTO boltDTO = new MaterialDTO(bolt.getName(), new Random().nextInt(1444) + 1);

        Material cooper = warehouse2.getMaterial("Cooper").get();
        MaterialDTO cooperDTO = new MaterialDTO(cooper.getName(), new Random().nextInt(888) + 1);

        warehouse1.migrateMaterials(warehouse2, List.of(boltDTO, cooperDTO));

        System.out.println(warehouse1);
        System.out.println(warehouse2);

        warehouse2.writeDownStocks();
    }

    private static void testGetMaterials() {
        Warehouse warehouse = generateWarehouse();

        Material material = warehouse.getMaterial("Iron").get();
        List<Material> materials1 = warehouse.getMaterials("Iron", "Glue", "Cooper");
        List<Material> materials2 = warehouse.getMaterials(List.of("Iron", "Bolt"));

        System.out.println(material);
        System.out.println(materials1);
        System.out.println(materials2);
    }

    private static void testUpdateMaterials() {
        Warehouse warehouse = generateWarehouse();

        System.out.println(warehouse);

        warehouse.updateMaterialAmount(generateGlue());
        warehouse.updateMaterialsAmount(generateBolt(), generateCooper());
        warehouse.updateMaterialsAmount(List.of(generateGlue()));

        System.out.println(warehouse);
    }

    private static void testAddMaterials() {

        Warehouse warehouse = generateWarehouse();

        System.out.println(warehouse);

        warehouse.addMaterial(generateGlue());
        warehouse.addMaterials(generateGlue(), generateBolt());
        warehouse.addMaterials(List.of(generateCooper()));

        System.out.println(warehouse);
    }


    private static Warehouse generateWarehouse() {
        Material iron = generateIron();
        Material cooper = generateCooper();
        Material bolt = generateBolt();
        Map<String, Material> map = new HashMap<>(Map.of(iron.getName(), iron, cooper.getName(), cooper, bolt.getName(), bolt));
        return new BasicWarehouse(map);
    }

    private static Material generateIron() {
        Material iron = new Iron();
        iron.setName(Iron.class.getSimpleName());
        iron.setDescription("Iron description");
        iron.setIcon("Some icon");
        iron.setAmount(new Random().nextInt(5000) + 1);
        return iron;
    }

    public static Material generateCooper() {
        Material cooper = new Cooper();
        cooper.setName(Cooper.class.getSimpleName());
        cooper.setDescription("Cooper description");
        cooper.setIcon("Some icon");
        cooper.setAmount(new Random().nextInt(4000) + 1);
        return cooper;
    }

    public static Material generateBolt() {
        Material bolt = new Bolt();
        bolt.setName(Bolt.class.getSimpleName());
        bolt.setDescription("Bolt description");
        bolt.setIcon("Some icon");
        bolt.setAmount(new Random().nextInt(6000) + 1);
        return bolt;
    }

    public static Material generateGlue() {
        Material glue = new Glue();
        glue.setName(Glue.class.getSimpleName());
        glue.setDescription("Glue description");
        glue.setIcon("Some icon");
        glue.setAmount(new Random().nextInt(750) + 1);
        return glue;
    }

}