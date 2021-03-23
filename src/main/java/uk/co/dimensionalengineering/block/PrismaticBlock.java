package uk.co.dimensionalengineering.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class PrismaticBlock extends Block {
    public static final String ID = "prismaticblock";

    public PrismaticBlock() {
        super(Properties.create(Material.IRON).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).hardnessAndResistance(1.0f, 6.0f).harvestLevel(2));
    }
}
