package com.al.utils.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.al.utils.bean.MenuBean;
import com.al.utils.core.CorePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangLong on 2017/3/16.
 */

public class PopupMenu extends PopupWindow {
    private ItemClickLinstener itemLinstener;
    private List<View> headView;
    private List<View> footView;
    private int lineNumber = 3;
    private ArrayList<MenuBean> menuList;
    private int count;
    private int backgroundColor = 0x33000000;
    private int textColor = 0xff000000;
    private float textSize = 15;
    private int vpTopPadding = 0;
    private int vpBottomPadding = 0;
    private int vpRightPadding = 0;
    private int vpLeftPadding = 0;
    private int decreaseWidth;
    private int decreaseHeight;
    private int scrollViewPadding;
    private boolean outsideTouchDissmiss;
    private Context mContext;
    private int vpBackgroundResource;
    private boolean isShowDots;
    private int dotSize = 10;
    private int dotBackgroundResource = com.al.utils.R.drawable.rb_selector;

    /**
     * @param context
     * @param menuList
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public PopupMenu(Context context, ArrayList<MenuBean> menuList) {
        super(context);
        this.menuList = menuList;
        this.mContext = context;
        if (menuList.size() % 3 == 0) {
            lineNumber = menuList.size() / 3;
        } else {
            lineNumber = menuList.size() / 3 + 1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initMenu(Context context, ArrayList<MenuBean> menuList, int lineNumber, List<View> headView, List<View> footView) {
        count = 0;
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        setHeight(context.getResources().getDisplayMetrics().heightPixels);
        if (lineNumber < 1) lineNumber = 1;
        LinearLayout parent = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        parent.setLayoutParams(layoutParams);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setGravity(Gravity.CENTER);
        parent.setBackgroundColor(backgroundColor);
        RelativeLayout rl = new RelativeLayout(context);
        ViewPager viewPager = new ViewPager(context);
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(-1, -1);
        rllp.width = (context.getResources().getDisplayMetrics().widthPixels / 10) * 9;
        rllp.height = rllp.width;
        rl.setLayoutParams(rllp);
        rl.setBackgroundResource(vpBackgroundResource);
        rl.setPadding(vpLeftPadding, vpTopPadding, vpRightPadding, vpBottomPadding);
        RelativeLayout.LayoutParams vplp = new RelativeLayout.LayoutParams(-1, -1);
        vplp.width = (context.getResources().getDisplayMetrics().widthPixels / 10) * 9;
        vplp.height = vplp.width;
        viewPager.setLayoutParams(vplp);
        CorePagerAdapter corePagerAdapter = new CorePagerAdapter() {
        };
        viewPager.setAdapter(corePagerAdapter);
        viewPager.setBackgroundColor(0x00000000);
        List<CheckedTextView> menuViewList = new ArrayList<>();
        for (int i = 0; i < menuList.size(); i++) {
            CheckedTextView checkedTextView = new CheckedTextView(context);
            LinearLayout.LayoutParams clp = new LinearLayout.LayoutParams(10, -2, 1);
            checkedTextView.setLayoutParams(clp);
            Drawable drawable = context.getResources().getDrawable(menuList.get(i).getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            checkedTextView.setCompoundDrawables(null, drawable, null, null);
            checkedTextView.setText(menuList.get(i).getMenuName());
            checkedTextView.setTag(menuList.get(i));
            checkedTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            checkedTextView.setGravity(Gravity.CENTER);
            checkedTextView.setTextColor(textColor);
            checkedTextView.setTextSize(textSize);
            checkedTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemLinstener != null)
                        itemLinstener.click((MenuBean) v.getTag());
                }
            });
            menuViewList.add(checkedTextView);
        }
        List<View> views = new ArrayList<>();
        int size = 0;
        if (menuList.size() % (3 * lineNumber) == 0) {
            size = menuList.size() / (3 * lineNumber);
        } else {
            size = menuList.size() / (3 * lineNumber) + 1;
        }
        for (int i = 0; i < size; i++) {
            LinearLayout childParent = new LinearLayout(context);
            LinearLayout linearLayout = new LinearLayout(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -1);
            linearLayout.setLayoutParams(lp);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            childParent.addView(linearLayout);
            ScrollView scrollView = new ScrollView(context);
            scrollView.addView(childParent);
            scrollView.setPadding(scrollViewPadding, 0, scrollViewPadding, 0);
            LinearLayout.LayoutParams svlp = new LinearLayout.LayoutParams(-1, -1);
            scrollView.setLayoutParams(svlp);
            scrollView.setVerticalScrollBarEnabled(false);
            for (int k = 0; k < lineNumber; k++) {
                LinearLayout linearLayout1 = new LinearLayout(context);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(-1, -2);
                if (lineNumber < 4) {
                    lp1.height = ((viewPager.getLayoutParams().height - vpTopPadding - vpBottomPadding) - decreaseHeight) / lineNumber;
                    linearLayout.getLayoutParams().height = viewPager.getLayoutParams().height - vpTopPadding - vpBottomPadding;
                } else {

                }
                lp1.width = scrollView.getLayoutParams().width - decreaseWidth;
                linearLayout1.setLayoutParams(lp1);
                linearLayout1.setGravity(Gravity.CENTER);
                linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                for (int j = 0; j < 3; j++) {
                    if (count < menuList.size()) {
                        linearLayout1.addView(menuViewList.get(count++));
                    } else {
                        linearLayout1.addView(addNullView(context));
                    }
                }
                linearLayout.addView(linearLayout1);
            }
            views.add(scrollView);
        }
        corePagerAdapter.setData(views);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (headView != null) {
            for (View view : headView) {
                parent.addView(view);
            }
        }
        rl.addView(viewPager);
        if (isShowDots) {
            dotInit(rl, viewPager);
        }
        parent.addView(rl);
        if (footView != null) {
            for (View view : footView) {
                parent.addView(view);
            }
        }
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outsideTouchDissmiss) {
                    dismiss();
                }
            }
        });
        setContentView(parent);
    }

    private LinearLayout dotsView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void dotInit(RelativeLayout rl, final ViewPager vp) {
        dotsView = new LinearLayout(mContext);
        LinearLayout.LayoutParams dotslp = new LinearLayout.LayoutParams(-2, -2);
        dotslp.height = 20;
        dotsView.setLayoutParams(dotslp);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        for (int i = 0; i < vp.getAdapter().getCount(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(dotBackgroundResource);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(-2, -2);
            params3.height = dotSize;
            params3.width = params3.height;
            params3.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params3);
            imageView.setEnabled(false);
            dotsView.addView(imageView);
        }
        dotsView.getChildAt(0).setEnabled(true);
        vp.setTag(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int tag = (int) vp.getTag();
                dotsView.getChildAt(tag).setEnabled(false);
                dotsView.getChildAt(position).setEnabled(true);
                vp.setTag(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rl.addView(dotsView, layoutParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View addNullView(Context context) {
        View view = new View(context);
        LinearLayout.LayoutParams clp = new LinearLayout.LayoutParams(10, -2, 1);
        view.setLayoutParams(clp);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        initMenu(mContext, menuList, lineNumber, headView, footView);
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        initMenu(mContext, menuList, lineNumber, headView, footView);
        super.showAtLocation(parent, gravity, x, y);
    }

    public int getDecreaseWidth() {
        return decreaseWidth;
    }

    public PopupMenu setDecreaseWidth(int decreaseWidth) {
        this.decreaseWidth = decreaseWidth;
        return this;
    }

    public int getScrollViewPadding() {
        return scrollViewPadding;
    }

    public int getVpBackgroundResource() {
        return vpBackgroundResource;
    }

    public PopupMenu setVpBackgroundResource(@DrawableRes int vpBackgroundResource) {
        this.vpBackgroundResource = vpBackgroundResource;
        return this;
    }

    public PopupMenu setScrollViewPadding(int scrollViewPadding) {
        this.scrollViewPadding = scrollViewPadding;
        return this;
    }

    public boolean isOutsideTouchDissmiss() {
        return outsideTouchDissmiss;
    }

    public PopupMenu setOutsideTouchDissmiss(boolean outsideTouchDissmiss) {
        this.outsideTouchDissmiss = outsideTouchDissmiss;
        return this;
    }

    public int getDecreaseHeight() {
        return decreaseHeight;
    }

    public PopupMenu setDecreaseHeight(int decreaseHeight) {
        this.decreaseHeight = decreaseHeight;
        return this;
    }

    public ItemClickLinstener getItemLinstener() {
        return itemLinstener;
    }

    public ArrayList<MenuBean> getMenuList() {
        return menuList;
    }

    public PopupMenu setMenuList(ArrayList<MenuBean> menuList) {
        this.menuList = menuList;
        return this;
    }

    public PopupMenu setItemLinstener(ItemClickLinstener itemLinstener) {
        this.itemLinstener = itemLinstener;
        return this;
    }

    public List<View> getHeadView() {
        return headView;
    }

    public PopupMenu setHeadView(List<View> headView) {
        this.headView = headView;
        return this;
    }

    public List<View> getFootView() {
        return footView;
    }

    public PopupMenu setFootView(List<View> footView) {
        this.footView = footView;
        return this;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public PopupMenu setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public int getCount() {
        return count;
    }

    public PopupMenu setCount(int count) {
        this.count = count;
        return this;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public PopupMenu setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public PopupMenu setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public float getTextSize() {
        return textSize;
    }

    public PopupMenu setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getVpTopPadding() {
        return vpTopPadding;
    }

    public PopupMenu setVpTopPadding(int vpTopPadding) {
        this.vpTopPadding = vpTopPadding;
        return this;
    }

    public int getVpBottomPadding() {
        return vpBottomPadding;
    }

    public PopupMenu setVpBottomPadding(int vpBottomPadding) {
        this.vpBottomPadding = vpBottomPadding;
        return this;
    }

    public int getVpRightPadding() {
        return vpRightPadding;
    }

    public PopupMenu setVpRightPadding(int vpRightPadding) {
        this.vpRightPadding = vpRightPadding;
        return this;
    }

    public int getVpLeftPadding() {
        return vpLeftPadding;
    }

    public PopupMenu setVpLeftPadding(int vpLeftPadding) {
        this.vpLeftPadding = vpLeftPadding;
        return this;
    }

    public boolean isShowDots() {
        return isShowDots;
    }

    public PopupMenu setShowDots(boolean showDots) {
        isShowDots = showDots;
        return this;
    }

    public int getDotSize() {
        return dotSize;
    }

    public int getDotBackgroundResource() {
        return dotBackgroundResource;
    }

    public PopupMenu setDotBackgroundResource(@DrawableRes int dotBackgroundResource) {
        this.dotBackgroundResource = dotBackgroundResource;
        return this;
    }

    public PopupMenu setDotSize(int dotSize) {
        this.dotSize = dotSize;
        return this;
    }

    public PopupMenu setClickItemLinstener(ItemClickLinstener itemLinstener) {
        this.itemLinstener = itemLinstener;
        return this;
    }

    public interface ItemClickLinstener {
        void click(MenuBean menu);
    }
}
