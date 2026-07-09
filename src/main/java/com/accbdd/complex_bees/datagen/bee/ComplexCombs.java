package com.accbdd.complex_bees.datagen.bee;

import com.accbdd.complex_bees.datagen.ItemTagGenerator;
import com.accbdd.complicated_bees.bees.Comb;
import com.accbdd.complicated_bees.registry.CombRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.JsonCodecProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.accbdd.complex_bees.ComplexBees.MODID;

public class ComplexCombs extends JsonCodecProvider<Comb> {
    public static final List<CombEntry> COMBS = new ArrayList<>();

    //public static final CombEntry FLUORITE = comb("tin", 0xafad9c, 0xeebefa);
    public static final CombEntry LEAD = comb("lead", 0xafad9c, 0x8a7e9a, ItemTagGenerator.RAW_LEAD);
    public static final CombEntry OSMIUM = comb("osmium", 0xafad9c, 0xcce0ee, ItemTagGenerator.RAW_OSMIUM);
    public static final CombEntry SILVER = comb("silver", 0xafad9c, 0xafc4ca, ItemTagGenerator.RAW_SILVER);
    public static final CombEntry TIN = comb("tin", 0xafad9c, 0xabbebd, ItemTagGenerator.RAW_TIN);
    public static final CombEntry URANIUM = comb("uranium", 0xafad9c, 0x81c04e, ItemTagGenerator.RAW_URANIUM);
    public static final CombEntry ZINC = comb("zinc", 0xafad9c, 0xbbefc1, ItemTagGenerator.RAW_ZINC);

    public ComplexCombs(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, PackOutput.Target.DATA_PACK, "complicated_bees/comb", PackType.SERVER_DATA, Comb.CODEC, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void gather() {
        for (CombEntry entry : COMBS) {
            unconditional(entry.key.location(), entry.comb);
            //conditionally(entry.key.location(), builder -> builder.withCarrier(entry.comb).addCondition(new TagEmptyCondition(entry.tag)));
        }
    }

    protected static CombEntry comb(String path, int outer, int inner, TagKey<Item> tag) {
        CombEntry comb = new CombEntry(path, outer, inner, tag);
        COMBS.add(comb);
        return comb;
    }

    public record CombEntry(ResourceKey<Comb> key, Comb comb, TagKey<Item> tag) {
        public CombEntry(String path, int outerColor, int innerColor, TagKey<Item> tag) {
            this(ResourceKey.create(CombRegistration.COMB_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(MODID, path)), new Comb(outerColor, innerColor), tag);
        }
    }
}
