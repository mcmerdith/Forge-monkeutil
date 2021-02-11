package net.mcmerdith.monkeutil.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WirelessLampBlock extends BasePowerableBlock {
    public WirelessLampBlock() {
        super(AbstractBlock.Properties.create(Material.REDSTONE_LIGHT).setLightLevel(illumination()).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid());
    }
}
