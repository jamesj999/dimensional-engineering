package uk.co.dimensionalengineering.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class PrismaticBlock extends Block {
    public static final String ID = "prismaticblock";

    public PrismaticBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    public static Properties generateProperties() {
        Properties properties = Properties.create(Material.ROCK);
        properties.harvestTool(ToolType.PICKAXE);
        properties.hardnessAndResistance(1.0f, 6.0f);
        properties.harvestLevel(2);

        return properties;
    }
}
