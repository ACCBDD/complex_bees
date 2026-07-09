package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complicated_bees.bees.mutation.condition.BlockTagUnderCondition;
import com.accbdd.complicated_bees.datagen.builtin.BuiltInSpecies;

import static com.accbdd.complex_bees.datagen.DataGenerators.blockTag;
import static com.accbdd.complex_bees.datagen.bee.ComplexBeeDataProvider.mutation;

public class ComplexMutations {

    public static void generateMutations() {
        mutation("metallic/tin",
                BuiltInSpecies.FERROUS.getKey(),
                BuiltInSpecies.CUPROUS.getKey(),
                ComplexSpecies.STANNUM.getKey(),
                0.10f,
                new BlockTagUnderCondition(blockTag("c", "storage_blocks/tin")));
    }
}
