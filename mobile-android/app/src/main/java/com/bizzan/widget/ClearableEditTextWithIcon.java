package com.bizzan.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.bizzan.R;


/**
 * 带有图标和删除符号的可编辑输入框，用户可以自定义传入的显示图标
 *
 * @author
 */
public class ClearableEditTextWithIcon extends AppCompatEditText implements OnTouchListener, TextWatcher {

    // 删除符号
    Drawable deleteImage = getResources().getDrawable(R.drawable.comm_icon_del_grey);


    private View.OnClickListener onDelBtnClick;

    public ClearableEditTextWithIcon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ClearableEditTextWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearableEditTextWithIcon(Context context) {
        super(context);
        init();
    }

    private void init() {
        ClearableEditTextWithIcon.this.setOnTouchListener(this);
        ClearableEditTextWithIcon.this.addTextChangedListener(this);
        deleteImage.setBounds(0, 0, deleteImage.getIntrinsicWidth(), deleteImage.getIntrinsicHeight());
        manageClearButton();
    }

    public void setOnDelBtnClickListener(View.OnClickListener onDelBtnClick) {
        this.onDelBtnClick = onDelBtnClick;
    }


    /**
     * 传入删除图标资源id
     *
     * @param id
     */
    public void setDeleteImage(int id) {
        deleteImage = getResources().getDrawable(id);
        deleteImage.setBounds(0, 0, deleteImage.getIntrinsicWidth(), deleteImage.getIntrinsicHeight());
        manageClearButton();
    }

    void manageClearButton() {
        if ("".equals(this.getText().toString())) {
            removeClearButton();
            if (onDelBtnClick != null) {
                onDelBtnClick.onClick(this);
            }
        } else
            addClearButton();
    }

    void removeClearButton() {
        this.setCompoundDrawables(getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
    }

    void addClearButton() {
        this.setCompoundDrawables(getCompoundDrawables()[0], this.getCompoundDrawables()[1], deleteImage,
                this.getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ClearableEditTextWithIcon et = ClearableEditTextWithIcon.this;

        if (et.getCompoundDrawables()[2] == null)
            return false;
        if (event.getAction() != MotionEvent.ACTION_UP)
            return false;
        if (event.getX() > et.getWidth() - et.getPaddingRight() - deleteImage.getIntrinsicWidth()) {
            et.setText("");
            ClearableEditTextWithIcon.this.removeClearButton();
        }
        return false;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ClearableEditTextWithIcon.this.manageClearButton();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
