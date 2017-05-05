package com.my.test.sdk.model;

public class Options {

    private String applicationName;
    private String limitTargetSdkVersion;
    private String replaceScreenOrientation;
    private String launchMode;
    private String replaceMainActivityIntentFilter;
    private String mainActivityAddIntentFilter;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLimitTargetSdkVersion() {
        return limitTargetSdkVersion;
    }

    public void setLimitTargetSdkVersion(String limitTargetSdkVersion) {
        this.limitTargetSdkVersion = limitTargetSdkVersion;
    }

    public String getReplaceScreenOrientation() {
        return replaceScreenOrientation;
    }

    public void setReplaceScreenOrientation(String replaceScreenOrientation) {
        this.replaceScreenOrientation = replaceScreenOrientation;
    }

    public String getLaunchMode() {
        return launchMode;
    }

    public void setLaunchMode(String launchMode) {
        this.launchMode = launchMode;
    }

    public String getMainActivityAddIntentFilter() {
        return mainActivityAddIntentFilter;
    }

    public void setMainActivityAddIntentFilter(String mainActivityAddIntentFilter) {
        this.mainActivityAddIntentFilter = mainActivityAddIntentFilter;
    }

    public String getReplaceMainActivityIntentFilter() {
        return replaceMainActivityIntentFilter;
    }

    public void setReplaceMainActivityIntentFilter(String replaceMainActivityIntentFilter) {
        this.replaceMainActivityIntentFilter = replaceMainActivityIntentFilter;
    }
}
