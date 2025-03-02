package com.example.samplemenu;

import java.util.Hashtable;
import java.util.Map;

// Android API levels information
// https://developer.android.com/guide/topics/manifest/uses-sdk-element
// https://apilevels.com
public class AndroidAPILevelInfo {

    // information for each API level
    protected static class APILevelInfo {
        final int apiLevel; // API level
        final String platformVersionNumber; // Android version number
        final String versionCode; // developer version code (VERSION_CODE)
        final String codeName; // high level code name
        final int year;  // release year

        public APILevelInfo(int apiLevel, String platformVersionNumber,
                            String versionCode, String codeName, int year) {
            this.apiLevel = apiLevel;
            this.platformVersionNumber = platformVersionNumber;
            this.versionCode = versionCode;
            this.codeName = codeName;
            this.year = year;
        }
    }

    // API level as key
    private static final Map<Integer, APILevelInfo> apiInfoList = new Hashtable<>();

    public AndroidAPILevelInfo() {
        apiInfoList.put( 1, new APILevelInfo( 1, "1",
                "BASE", "-", 2008));
        apiInfoList.put( 2, new APILevelInfo( 2, "1.1",
                "BASE_1_1", "Petit Four", 2008));
        apiInfoList.put( 3, new APILevelInfo( 3, "1.5",
                "CUPCAKE", "Cupcake", 2008));
        apiInfoList.put( 4, new APILevelInfo( 4, "1.6",
                "DONUT", "Donut", 2008));
        apiInfoList.put( 5, new APILevelInfo( 5, "2",
                "ECLAIR", "Eclair", 2008));
        apiInfoList.put( 6, new APILevelInfo( 6, "2.0.1",
                "ECLAIR_0_1", "Eclair", 2009));
        apiInfoList.put( 7, new APILevelInfo( 7, "2.1.x",
                "ECLAIR_MR1", "Eclair", 2009));
        apiInfoList.put( 8, new APILevelInfo( 8, "2.2.x",
                "FROYO", "Froyo", 2009));
        apiInfoList.put( 9, new APILevelInfo( 9, "2.3, 2.3.1, 2.3.2",
                "GINGERBREAD", "Gingerbread", 2010));
        apiInfoList.put(10, new APILevelInfo(10, "2.3.3, 2.3.4",
                "GINGERBREAD_MR1", "Gingerbread", 2010));
        apiInfoList.put(11, new APILevelInfo(11, "3.0.x",
                "HONEYCOMB", "Honeycomb", 2010));
        apiInfoList.put(12, new APILevelInfo(12, "3.1.x",
                "HONEYCOMB_MR1", "Honeycomb", 2010));
        apiInfoList.put(13, new APILevelInfo(13, "3.2",
                "HONEYCOMB_MR2", "Honeycomb", 2010));
        apiInfoList.put(14, new APILevelInfo(14, "4.0, 4.0.1, 4.0.2",
                "ICE_CREAM_SANDWICH", "Ice Cream Sandwich", 2010));
        apiInfoList.put(15, new APILevelInfo(15, "4.0.3, 4.0.4",
                "ICE_CREAM_SANDWICH_MR1", "Ice Cream Sandwich", 2011));
        apiInfoList.put(16, new APILevelInfo(16, "4.1, 4.1.1",
                "JELLY_BEAN", "KitKat", 2011));
        apiInfoList.put(17, new APILevelInfo(17, "4.2, 4.2.2",
                "JELLY_BEAN_MR1", "KitKat", 2012));
        apiInfoList.put(18, new APILevelInfo(18, "4.3",
                "JELLY_BEAN_MR2", "KitKat", 2012));
        apiInfoList.put(19, new APILevelInfo(19, "4.4",
                "KITKAT", "KitKat", 2013));
        apiInfoList.put(20, new APILevelInfo(20, "4.4W",
                "KITKAT_WATCH", "KitKat", 2013));
        apiInfoList.put(21, new APILevelInfo(21, "5",
                "LOLLIPOP", "Lollipop", 2014));
        apiInfoList.put(22, new APILevelInfo(22, "5.1",
                "LOLLIPOP_MR1", "Lollipop", 2014));
        apiInfoList.put(23, new APILevelInfo(23, "6",
                "M", "Marshmallow", 2015));
        apiInfoList.put(24, new APILevelInfo(24, "7",
                "N", "Nougat", 2015));
        apiInfoList.put(25, new APILevelInfo(25, "7.1/7.1.1",
                "N_MR1", "Nougat", 2016));
        apiInfoList.put(26, new APILevelInfo(26, "8",
                "O", "Oreo", 2016));
        apiInfoList.put(27, new APILevelInfo(27, "8.1",
                "O_MR1", "Oreo", 2017));
        apiInfoList.put(28, new APILevelInfo(28, "9",
                "P", "Pie", 2018));
        apiInfoList.put(29, new APILevelInfo(29, "10",
                "Q", "Quince Tart", 2019));
        apiInfoList.put(30, new APILevelInfo(30, "11",
                "R", "Red Velvet Cake", 2020));
        apiInfoList.put(31, new APILevelInfo(31, "12",
                "S", "Snow Cone", 2021));
        apiInfoList.put(32, new APILevelInfo(32, "12",
                "S_V2", "Snow Cone", 2022));
        apiInfoList.put(33, new APILevelInfo(33, "13",
                "TIRAMISU", "Tiramisu 2", 2022));
        apiInfoList.put(34, new APILevelInfo(34, "14",
                "UPSIDE_DOWN_CAKE", "Upside Down Cake", 2023));
    }

    // access functions
    public int getLevel(int apiLevel) {
        APILevelInfo info = apiInfoList.getOrDefault(apiLevel, null);
        return (info != null) ? info.apiLevel : 0;
    }

    public String getPlatformVersionNumber(int apiLevel) {
        APILevelInfo info = apiInfoList.getOrDefault(apiLevel, null);
        return (info != null) ? info.platformVersionNumber : "Unknown";
    }

    public String getVersionCode(int apiLevel) {
        APILevelInfo info = apiInfoList.getOrDefault(apiLevel, null);
        return (info != null) ? info.versionCode : "Unknown";
    }

    public String getCodeName(int apiLevel) {
        APILevelInfo info = apiInfoList.getOrDefault(apiLevel, null);
        return (info != null) ? info.codeName : "Unknown";
    }

    public int getYear(int apiLevel) {
        APILevelInfo info = apiInfoList.getOrDefault(apiLevel, null);
        return (info != null) ? info.year : 0;
    }
}
