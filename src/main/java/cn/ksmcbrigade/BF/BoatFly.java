package cn.ksmcbrigade.BF;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod("bf")
public class BoatFly {

    public static float speed = 0.3F;

    public BoatFly() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        init();
    }

    public static void init() throws IOException {

        final Path path = Paths.get("config/bf-config.json");
        if(!new File("config/bf-config.json").exists()){
            JsonObject json = new JsonObject();
            json.addProperty("speed",0.3F);
            Files.write(path,json.toString().getBytes());
        }
        speed = JsonParser.parseString(Files.readString(path)).getAsJsonObject().get("speed").getAsFloat();

        if(!new File("config/vm/mods").exists()){
            new File("config/vm/mods").mkdirs();
        }
        if(!new File("config/ccm/mods").exists()){
            new File("config/ccm/mods").mkdirs();
        }

        if(!new File("config/vm/mods/BoatFly.json").exists()){
            JsonObject object = new JsonObject();
            object.addProperty("name","hack.bf.name");
            object.addProperty("id","bf");
            object.addProperty("main","cn.ksmcbrigade.BF.Manager");
            object.addProperty("function","NONE");
            object.addProperty("function_2","run");
            object.addProperty("gui_main","NONE");
            object.addProperty("gui_function","NONE");
            Files.write(Paths.get("config/vm/mods/BoatFly.json"),object.toString().getBytes());
        }

        if(!new File("config/ccm/mods/BoatFly.json").exists()){
            JsonObject object = new JsonObject();
            object.addProperty("name","bs");
            object.addProperty("id","bf");
            object.addProperty("main","cn.ksmcbrigade.BF.Manager");
            object.addProperty("function","runCommand");
            Files.write(Paths.get("config/ccm/mods/BoatFly.json"),object.toString().getBytes());
        }
    }
}
