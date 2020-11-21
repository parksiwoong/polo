package popor.com.utility;


import popor.com.enums.productbasket.GetResult;
import popor.com.vos.basket.BasketVo;

import java.util.ArrayList;

public class BasketGetResultContainer {
    private final GetResult getResult;
    private final ArrayList<BasketVo> baskets;

    public BasketGetResultContainer(GetResult getResult) {
        this(getResult, null);
    }

    public BasketGetResultContainer(GetResult getResult, ArrayList<BasketVo> baskets) {
        this.getResult = getResult;
        this.baskets = baskets;
    }

    public GetResult getGetResult() {
        return getResult;
    }

    public ArrayList<BasketVo> getBaskets() {
        return baskets;
    }
}
