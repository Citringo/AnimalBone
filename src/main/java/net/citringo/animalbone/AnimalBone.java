package net.citringo.animalbone;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(
        modid = AnimalBone.MOD_ID,
        name = AnimalBone.MOD_NAME,
        version = AnimalBone.VERSION
)
public class AnimalBone {

    public static final String MOD_ID = "animalbone";
    public static final String MOD_NAME = "AnimalBone";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static AnimalBone INSTANCE;

    private Logger logger;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent e) {
        if (e.getEntity() instanceof EntityAnimal) {
            World w = e.getEntity().getEntityWorld();
            int amount = w.rand.nextInt(3);
            BlockPos p = e.getEntity().getPosition();
            if (amount > 0) {
                ItemStack item = new ItemStack(Items.BONE, amount, 0);

                EntityItem ei = new EntityItem(w, p.getX(), p.getY(), p.getZ(), item);
                w.spawnEntity(ei);
            }
        }
    }


}
