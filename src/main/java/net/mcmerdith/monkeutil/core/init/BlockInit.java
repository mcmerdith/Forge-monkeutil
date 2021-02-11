package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.common.blocks.WirelessLampBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit extends Initializer<Block, AbstractBlock.Properties> {
    public final RegistryObject<WirelessLampBlock> WIRELESS_LAMP;

    public BlockInit() {
        super(ForgeRegistries.BLOCKS);

        WIRELESS_LAMP = registerSpecial("wireless_lamp", WirelessLampBlock::new);
    }

    @Override
    protected Supplier<Block> factory(AbstractBlock.Properties arg) {
        return () -> new Block(arg);
    }
}
