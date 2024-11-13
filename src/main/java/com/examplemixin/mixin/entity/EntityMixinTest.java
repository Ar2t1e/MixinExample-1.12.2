package com.examplemixin.mixin.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Тестовый метод, который убирает - спавн партиклов, при смерти у Entity.
@Mixin(EntityLivingBase.class)
public abstract class EntityMixinTest {
    @Shadow
    protected int recentlyHit;

    @Shadow
    protected EntityPlayer attackingPlayer;

    @Inject(method = "onDeathUpdate", at = @At("HEAD"), cancellable = true)
    private void onDeathUpdate(CallbackInfo ci) {
        ci.cancel();

        EntityLivingBase entity = (EntityLivingBase) (Object) this;

        ++entity.deathTime;

        if (entity.deathTime == 20) {
            if (!entity.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && entity.world.getGameRules().getBoolean("doMobLoot"))) {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(entity, this.attackingPlayer, i);
                while (i > 0) {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    entity.world.spawnEntity(new EntityXPOrb(entity.world, entity.posX, entity.posY, entity.posZ, j));
                }
            }

            entity.setDead();
        }
    }

    @Shadow
    public abstract boolean canDropLoot();

    @Shadow
    public abstract int getExperiencePoints(EntityPlayer player);

    @Shadow
    public abstract boolean isPlayer();
}
