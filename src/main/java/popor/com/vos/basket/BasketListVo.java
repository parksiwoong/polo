package popor.com.vos.basket;

import popor.com.vos.item.ItemVo;

import java.util.Date;

public class BasketListVo {

    private Integer index;
    private Date datetime;
    private Integer count;
    private ItemVo itemVo;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ItemVo getItemVo() {
        return itemVo;
    }

    public void setItemVo(ItemVo itemVo) {
        this.itemVo = itemVo;
    }

}
