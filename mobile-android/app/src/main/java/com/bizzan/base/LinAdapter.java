package com.bizzan.base;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 提取的Adapter的公共方法，简化操作
 *

 */
public abstract class LinAdapter<T> extends BaseAdapter {

    protected List<T> beans;
    protected Activity context;


    /**
     * LinAdapter通用的构造方法
     *
     * @param context 传入的上下文
     * @param beans   要显示的数据源封装好的列表
     */
    public LinAdapter(Activity context, List<T> beans) {
        this.context = context;
        this.beans = beans;

    }


    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return LgetView(position, convertView, parent);
    }

    /**
     * Adapter中唯一需要实现的方法
     *
     * @param convertView 循环使用的view
     * @param parent      附加的view
     * @param position    调用的条目的位置
     * @return 返回要显示在position位置的View
     */
    protected abstract View LgetView(int position, View convertView, ViewGroup parent);



    public static class ViewHolders {

        private ViewHolders() {

        }

        @SuppressWarnings("unchecked")
        public static <K extends View> K get(View view, int id) {
            SparseArray<View> viewholder = (SparseArray<View>) view.getTag();

            if (viewholder == null) {
                viewholder = new SparseArray<View>();
                view.setTag(viewholder);
            }
            View childView = viewholder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewholder.put(id, childView);
            }

            return (K) childView;

        }
    }
}
