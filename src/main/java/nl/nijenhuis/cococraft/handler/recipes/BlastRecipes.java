package nl.nijenhuis.cococraft.handler.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BlastRecipes {

    private static final BlastRecipes smeltingBase = new BlastRecipes();

    private Map smeltingList = new HashMap();

    public static BlastRecipes smelting() {
        return smeltingBase;
    }

    public void addSmelting(Block input, ItemStack output) {
        addSmelting(Item.getItemFromBlock(input), output);
    }

    public void addSmelting(Item input, ItemStack output) {
        addSmelting(new ItemStack(input, 1, 32767), output);
    }

    public void addSmelting(ItemStack input, ItemStack output) {
        smeltingList.put(input, output);
    }

    public ItemStack getSmeltingResult(ItemStack itemStack) {
        Iterator iterator = smeltingList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }
            entry = (Map.Entry) iterator.next();
        } while (!metadata(itemStack, (ItemStack) entry.getKey()));
        return (ItemStack) entry.getValue();
    }

    private boolean metadata(ItemStack itemStack, ItemStack itemStack2) {
        return itemStack2.getItem() == itemStack.getItem() && (itemStack2.getItemDamage() == 32767 || itemStack2.getItemDamage() == itemStack.getItemDamage());
    }


}
