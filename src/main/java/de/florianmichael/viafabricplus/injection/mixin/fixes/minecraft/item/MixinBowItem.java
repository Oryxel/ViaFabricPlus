/*
 * This file is part of ViaFabricPlus - https://github.com/FlorianMichael/ViaFabricPlus
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD
 * Copyright (C) 2023      RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.viafabricplus.injection.mixin.fixes.minecraft.item;

import de.florianmichael.viafabricplus.protocolhack.ProtocolHack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.raphimc.vialoader.util.VersionEnum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public abstract class MixinBowItem {

    @Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
    private void makeInstantUsable(CallbackInfoReturnable<Integer> cir) {
        if (ProtocolHack.getTargetVersion().isOlderThanOrEqualTo(VersionEnum.b1_7tob1_7_3)) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
    private void makeInstantUsable2(CallbackInfoReturnable<UseAction> cir) {
        if (ProtocolHack.getTargetVersion().isOlderThanOrEqualTo(VersionEnum.b1_7tob1_7_3)) {
            cir.setReturnValue(UseAction.NONE);
        }
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void makeInstantUsable(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (ProtocolHack.getTargetVersion().isOlderThanOrEqualTo(VersionEnum.b1_7tob1_7_3)) {
            final ItemStack stack = user.getStackInHand(hand);
            final ItemStack arrowStack = user.getProjectileType(stack);
            if (arrowStack.isEmpty()) {
                cir.setReturnValue(TypedActionResult.fail(stack));
            } else {
                arrowStack.decrement(1);
                cir.setReturnValue(TypedActionResult.pass(stack));
            }
        }
    }

}