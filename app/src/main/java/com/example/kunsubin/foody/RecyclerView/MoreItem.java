package com.example.kunsubin.foody.RecyclerView;

import com.example.kunsubin.foody.R;

/**
 * Created by kunsubin on 4/5/2017.
 */

public class MoreItem {
    String tittle;
    MoreItemCode code;
    public MoreItem(String tittle, MoreItemCode code) {
        this.tittle = tittle;
        this.code = code;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getImage() {
        int img = -1;
        if (code == null) {
            img = -1;
        } else {
            if (code.equals(MoreItemCode.NEARBY)) {
                img = R.drawable.icon_ganday;
            }
            if (code.equals(MoreItemCode.BOOK)) {
                img = R.drawable.icon_datchoudai;
            }
            if (code.equals(MoreItemCode.ECARD)) {
                img = R.drawable.icon_ecard;
            }
            if (code.equals(MoreItemCode.REVIEW)) {
                img = R.drawable.icon_binhluan;
            }
            if (code.equals(MoreItemCode.TOPMEMBER)) {
                img = R.drawable.user_topthanhvien;
            }
            if (code.equals(MoreItemCode.COUPON)) {
                img = R.drawable.icon_coupon;
            }
            if (code.equals(MoreItemCode.DELIVERY)) {
                img = R.drawable.icon_datgiaohang;
            }
            if (code.equals(MoreItemCode.GAME_FUN)) {
                img = R.drawable.icon_gamefun;
            }
            if (code.equals(MoreItemCode.BLOGS)) {
                img = R.drawable.icon_blogs;
            }
            if (code.equals(MoreItemCode.VIDEO)) {
                img = R.drawable.icon_video;
            }
        }
        return img;
    }

    public enum MoreItemCode {
        NEARBY,
        BOOK,
        ECARD,
        REVIEW,
        TOPMEMBER,
        COUPON,
        DELIVERY,
        GAME_FUN,
        BLOGS,
        VIDEO
    }
}
