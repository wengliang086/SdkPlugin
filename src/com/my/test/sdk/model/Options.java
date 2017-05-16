package com.my.test.sdk.model;

import java.util.List;
import java.util.Map;

public class Options {

    private String applicationName;
    private int limitTargetSdkVersion;
    private boolean replaceScreenOrientation;
    private String launchMode;
    private List<String[]> replaceMainActivityIntentFilter;
    private String mainActivityAddIntentFilter;
    private Map<String, String> customReplaceMap;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getLimitTargetSdkVersion() {
        return limitTargetSdkVersion;
    }

    public void setLimitTargetSdkVersion(int limitTargetSdkVersion) {
        this.limitTargetSdkVersion = limitTargetSdkVersion;
    }

    public boolean isReplaceScreenOrientation() {
        return replaceScreenOrientation;
    }

    public void setReplaceScreenOrientation(boolean replaceScreenOrientation) {
        this.replaceScreenOrientation = replaceScreenOrientation;
    }

    public String getLaunchMode() {
        return launchMode;
    }

    public void setLaunchMode(String launchMode) {
        this.launchMode = launchMode;
    }

    public List<String[]> getReplaceMainActivityIntentFilter() {
        return replaceMainActivityIntentFilter;
    }

    public void setReplaceMainActivityIntentFilter(List<String[]> replaceMainActivityIntentFilter) {
        this.replaceMainActivityIntentFilter = replaceMainActivityIntentFilter;
    }

    public String getMainActivityAddIntentFilter() {
        return mainActivityAddIntentFilter;
    }

    public void setMainActivityAddIntentFilter(String mainActivityAddIntentFilter) {
        this.mainActivityAddIntentFilter = mainActivityAddIntentFilter;
    }

    public Map<String, String> getCustomReplaceMap() {
        return customReplaceMap;
    }

    public void setCustomReplaceMap(Map<String, String> customReplaceMap) {
        this.customReplaceMap = customReplaceMap;
    }
}
