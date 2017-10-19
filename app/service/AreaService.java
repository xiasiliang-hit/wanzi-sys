package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.AreaInfo;

import java.util.ArrayList;
import java.util.List;

public class AreaService {

    public void insertLocations(String jsonStr) {
        List<AreaInfo> areaInfos = new ArrayList<>();
        JsonArray locationArray = new JsonParser().parse(jsonStr).getAsJsonArray();
        JsonArrayToLocList(areaInfos, locationArray, null);

    }

    private void JsonArrayToLocList(List<AreaInfo> locs, JsonArray locArray, String parent) {

        AreaInfo areaInfo = new AreaInfo();
        for (int i = 0; i < locArray.size(); i++) {
            JsonObject locationJson = locArray.get(i).getAsJsonObject();
            String areaId = locationJson.get("area_id").getAsString();
            String areaEn = locationJson.get("area_en").getAsString();
            String areaImage = locationJson.get("area_image").getAsString();
            String areaName = locationJson.get("area_name").getAsString();
            String areaPin = locationJson.get("area_pin").getAsString();
            String areaLevel = locationJson.get("area_level").getAsString();
            String areaPX = locationJson.get("area_px").getAsString();
            String areaPY = locationJson.get("area_py").getAsString();
            String areaCurrency = locationJson.get("area_currency").getAsString();
            String areaTimeZone = locationJson.get("area_timezone").getAsString();
            JsonArray childArea = locationJson.getAsJsonArray("child");

            areaInfo.setAreaId(areaId);
            areaInfo.setAreaName(areaName);
            areaInfo.setAreaPin(areaPin);
            areaInfo.setAreaEn(areaEn);
            areaInfo.setAreaLevel(areaLevel);
            areaInfo.setAreaImage(areaImage);
            areaInfo.setAreaCurrency(areaCurrency);
            areaInfo.setAreaPX(areaPX);
            areaInfo.setAreaPY(areaPY);
            areaInfo.setAreaTimeZone(areaTimeZone);
            areaInfo.setAreaParent(parent);
            locs.add(areaInfo);
            if (childArea != null && childArea.size() > 0) {
                JsonArrayToLocList(locs, childArea, areaId);
            }
        }
    }
}
