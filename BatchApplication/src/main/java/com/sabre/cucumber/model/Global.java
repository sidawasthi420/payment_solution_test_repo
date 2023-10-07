package com.sabre.cucumber.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Global {
    @SerializedName("dataMap")
    public Map<String, String> dataMap;
    @SerializedName("featureData")
    public LinkedHashMap<String, ArrayList<String>> featureData;
    @SerializedName("dataContainer")
    public Map<String, Object> dataContainer;
    @SerializedName("scenarioPath")
    public String scenarioPath;

    public Global() {
        this.dataMap = new HashMap<>();
        this.featureData = new LinkedHashMap<>();
        this.dataContainer = new HashMap<>();
    }

    public LinkedHashMap<String, ArrayList<String>> getFeatureData() {
        return featureData;
    }
}
