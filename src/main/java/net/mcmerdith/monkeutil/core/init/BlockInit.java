package net.mcmerdith.monkeutil.core.init;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit extends Initializer<Block>{
    public BlockInit() {
        super(ForgeRegistries.BLOCKS);
    }

    @Override
    <I extends Block> Supplier<? extends I> factory() {
        return null;
    }
}
