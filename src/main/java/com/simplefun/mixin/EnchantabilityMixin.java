package com.simplefun.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EnchantableComponent;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Settings.class)
public class EnchantabilityMixin {

    @Inject(method = "getValidatedComponents", at = @At("RETURN"), cancellable = true)
    private void addEnchantabilityToSpecificItems(Text name, Identifier modelId, CallbackInfoReturnable<ComponentMap> cir) {
        // Wir prüfen anhand der ID (modelId), ob es sich um eine Feder oder einen Stock handelt
        if (modelId.equals(Identifier.ofVanilla("feather")) || modelId.equals(Identifier.ofVanilla("stick"))) {
            ComponentMap originalMap = cir.getReturnValue();

            // Wir erstellen eine neue Map basierend auf der alten und fügen die Verzauberbarkeit hinzu
            ComponentMap newMap = ComponentMap.builder()
                    .addAll(originalMap)
                    .add(DataComponentTypes.ENCHANTABLE, new EnchantableComponent(10)) // Wert 10 (ähnlich wie Holz)
                    .build();

            cir.setReturnValue(newMap);
        }
    }
}