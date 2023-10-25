package net.xsapi.panat.xsgift.handlers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.xsapi.panat.xsgift.utils.xsutils_itemstack;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;

public class XSGiftModelTypeAdapter extends TypeAdapter<xsgift_model> {

    @Override
    public void write(JsonWriter out, xsgift_model giftModel) throws IOException {
        out.beginObject();
        out.name("sender").value(giftModel.getSender());
        out.name("permission");
        out.beginArray();
        for (String permission : giftModel.getPermission()) {
            out.value(permission);
        }
        out.endArray();

        out.name("worldName");
        out.beginArray();
        for (String world : giftModel.getWorldName()) {
            out.value(world);
        }
        out.endArray();

        out.name("expectedUser");
        out.beginArray();
        for (String users : giftModel.getExpectedUser()) {
            out.value(users);
        }
        out.endArray();

        out.name("includedUser");
        out.beginArray();
        for (String users : giftModel.getIncludedUser()) {
            out.value(users);
        }
        out.endArray();


        out.name("itemsToSend");
        out.beginArray();
        for (ItemStack item : giftModel.getItemsToSend()) {
            out.value(xsutils_itemstack.itemStackToBase64(item));
        }
        out.endArray();

        out.endObject();
    }

    @Override
    public xsgift_model read(JsonReader in) throws IOException {
        String sender = null;
        ArrayList<ItemStack> itemsToSend = null;
        ArrayList<String> permission = null;
        ArrayList<String> worldName = null;
        ArrayList<String> expectedUser = null;
        ArrayList<String> includedUser = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "sender":
                    sender = in.nextString();
                    break;
                case "itemsToSend":
                    itemsToSend = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        itemsToSend.add(xsutils_itemstack.itemStackFromBase64(in.nextString()));
                    }
                    in.endArray();
                    break;
                case "permission":
                    permission = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        permission.add(in.nextString());
                    }
                    in.endArray();
                    break;
                case "worldName":
                    worldName = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        worldName.add(in.nextString());
                    }
                    in.endArray();
                    break;
                case "expectedUser":
                    expectedUser = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        expectedUser.add(in.nextString());
                    }
                    in.endArray();
                    break;
                case "includedUser":
                    includedUser = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        includedUser.add(in.nextString());
                    }
                    in.endArray();
                    break;
            }
        }
        in.endObject();

        return new xsgift_model(sender, itemsToSend, permission, worldName, expectedUser, includedUser);
    }
}
