package com.al.utils.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by ZhangLong on 2017/3/16.
 */

public class MenuBean {
    private
    @DrawableRes
    int icon;
    private String menuId;
    private String menuName;

    public MenuBean(@DrawableRes int icon, String menuId, String menuName) {
        this.icon = icon;
        this.menuId = menuId;
        this.menuName = menuName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
