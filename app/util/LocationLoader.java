package util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.AreaInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationLoader {
    private static String path = "public/country.json";
    private StringBuffer jsonBuffer = new StringBuffer();
    public List<AreaInfo> areaTree = new ArrayList<>();
    public void loadJson(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String jsonLine;
            while ((jsonLine = br.readLine()) != null){
                jsonBuffer.append(jsonLine);
            }
            br.close();
            getLocationNode();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getLocationNode(){
        String jsonStr = jsonBuffer.toString();
        jsonStr = jsonStr.replace("\t","");
        jsonStr = jsonStr.replace(" ","");
        jsonStr = jsonStr.trim();
        JsonArray obj = new JsonParser().parse(jsonStr).getAsJsonArray();
        for (JsonElement ele : obj){
            setAreaTree(ele.getAsJsonObject(), "", "");
        }
        AreaInfo.saveAreas(areaTree);
    }

    public void setAreaTree(JsonObject obj, String parentId, String parentIndex){
        AreaInfo areaNode = new AreaInfo();
        String areaId = obj.get("area_id").getAsString();
        String areaEn = obj.get("area_en").getAsString();
        String areaImage = obj.get("area_image").getAsString();
        String areaName = obj.get("area_name").getAsString();
        String areaPin = obj.get("area_pin").getAsString();
        String areaLevel = obj.get("area_level").getAsString();
        String areaPX = obj.get("area_px").getAsString();
        String areaPY = obj.get("area_py").getAsString();
        String areaCurrency = obj.get("area_currency").getAsString();
        String areaTimeZone = obj.get("area_timezone").getAsString();
        String index = areaName + "-" + areaEn;
        if (!parentIndex.equals("")){
            index = parentIndex + "-" + index;
        }
        JsonArray childArea = obj.getAsJsonArray("child");

        areaNode.setAreaId(areaId);
        areaNode.setAreaName(areaName);
        areaNode.setAreaPin(areaPin);
        areaNode.setAreaEn(areaEn);
        areaNode.setAreaLevel(areaLevel);
        areaNode.setAreaImage(areaImage);
        areaNode.setAreaCurrency(areaCurrency);
        areaNode.setAreaPX(areaPX);
        areaNode.setAreaPY(areaPY);
        areaNode.setAreaTimeZone(areaTimeZone);
        areaNode.setAreaParent(parentId);
        areaNode.setAreaIndex(index);


        areaTree.add(areaNode);
        if (childArea != null && childArea.size() > 0){
            for (JsonElement ele : childArea){
                setAreaTree(ele.getAsJsonObject(), areaId, index);
            }
        }
    }
}
