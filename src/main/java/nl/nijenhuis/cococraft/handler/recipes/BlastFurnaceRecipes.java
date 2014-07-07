package nl.nijenhuis.cococraft.handler.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BlastFurnaceRecipes {

    private static final BlastFurnaceRecipes smeltingBase = new BlastFurnaceRecipes();

    private Map smeltingList = new HashMap();

    public static BlastFurnaceRecipes smelting() {
        return smeltingBase;
    }

    public void addSmelting(ItemStack input, ItemStack output) {
        smeltingList.put(input, output);
    }

    public void addSmelting(Block input, ItemStack output) {
        addSmelting(Item.getItemFromBlock(input), output);
    }
    public void addSmelting(Item input, ItemStack output) {
        addSmelting(new ItemStack(input, 1, 32768), output);
    }

    public ItemStack getSmeltingResult(ItemStack stack) {
        Iterator iterator = smeltingList.entrySet().iterator();
        Map.Entry entry;

        do {
            if(!iterator.hasNext()) {
                return null;
            }
            entry = (Map.Entry)iterator.next();
        } while(!metadata(stack, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private boolean metadata(ItemStack stack, ItemStack stack2) {
        return stack2.getItem() == stack.getItem() && (stack2.getItemDamage() == 32767 || stack2.getItemDamage() == stack.getItemDamage());
    }

}
