package nl.nijenhuis.cococraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import nl.nijenhuis.cococraft.CocoCraft;

public class BlockOre extends Block {

    public BlockOre() {
        super(Material.iron);
        setHardness(3F);
        setCreativeTab(CocoCraft.tabCocoCraft);
    }
}
