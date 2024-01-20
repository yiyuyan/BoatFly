package cn.ksmcbrigade.BF;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;

public class Manager {
    public static void run(Player player){
        Minecraft MC = Minecraft.getInstance();
        if(player.getVehicle()==null){
            return;
        }
        Entity vehicle = player.getVehicle();
        Vec3 velocity = vehicle.getViewVector(0);
        double motionY = MC.options.keyJump.isDown() ? BoatFly.speed : 0;
        vehicle.setDeltaMovement(velocity.x, motionY, velocity.z);
    }

    public static void runCommand(Player player,String[] args){
        if(Arrays.equals(args, new String[0])){
            player.sendMessage(Component.nullToEmpty("Speed: "+BoatFly.speed),player.getUUID());
        }
        else{
            try {
                float speed = Float.parseFloat(args[0]);
                if(speed>1F | speed<0.01F){
                    player.sendMessage(new TranslatableComponent("command.bf.num_to_big"),player.getUUID());
                }
                else{
                    BoatFly.speed = speed;
                    player.sendMessage(Component.nullToEmpty("Done."),player.getUUID());
                }
            }
            catch (Exception e){
                e.printStackTrace();
                player.sendMessage(new TranslatableComponent("command.bf.cant_to_num"),player.getUUID());
            }
        }
    }
}
