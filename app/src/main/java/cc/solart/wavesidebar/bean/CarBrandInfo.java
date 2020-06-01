package cc.solart.wavesidebar.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import static cc.solart.wavesidebar.CarBrandDataKt.CAR_BRAND_DATA_JSON;

public class CarBrandInfo implements Serializable {

    /**
     * id : 272
     * brandName : ARCFOX
     * alphabetCode : A
     * logo : http://images.cheegu.com/img/brand/272_3B1A94BA6723C689A5792E778865FAEC.png
     * lastUpdatedStamp : null
     * createdStamp : 1554998400000
     * isEnable : 1
     * resourceFrom : 0
     */

    public long id;
    public String brandName;
    public String alphabetCode;
    public String logo;
    public long lastUpdatedStamp;
    public long createdStamp;
    public int isEnable;
    public int resourceFrom;
    /**
     * 0: default data, 1: PinnedHolder
     */
    public int dataType;

    private static final class Response {
        public List<CarBrandInfo> data;
    }

    public static List<CarBrandInfo> getDatas() {
        return new Gson().fromJson(CAR_BRAND_DATA_JSON, Response.class).data;
    }
}
