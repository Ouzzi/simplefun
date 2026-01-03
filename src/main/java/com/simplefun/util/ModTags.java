package com.simplefun.util;

import com.simplefun.Simplefun;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        // Dieser Tag beinhaltet alles, was Knockback & No Damage bekommen darf
        // Also: Federn, Sticks UND normale Waffen (damit wir Knockback für Schwerter nicht kaputt machen)
        public static final TagKey<Item> KNOCKBACK_ALLOWED = createTag("knockback_allowed");
        
        // Nur unsere speziellen Items
        public static final TagKey<Item> SIMPLE_FUN_ITEMS = createTag("simple_fun_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Simplefun.MOD_ID, name));
        }
    }
}