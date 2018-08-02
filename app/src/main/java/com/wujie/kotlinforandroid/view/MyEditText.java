package com.wujie.kotlinforandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Created by wujie on 2018/8/2/002.
 */

public class MyEditText extends android.support.v7.widget.AppCompatTextView {

    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void setEditTextNoSoftInput(EditText editText) {

        Class editClass = editText.getClass().getSuperclass();
        Class textClass = editClass.getSuperclass();
        try {
            Field editorField = textClass.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            Object editorObject = editorField.get(editText);
            Class editorClass = editorObject.getClass();
            if (!"Editor".equals(editorClass.getSimpleName())) {
                editorClass = editorClass.getSuperclass(); // 防止类似于华为使用的自身的HwEditor
            }
            Field mShowInput = editorClass.getDeclaredField("mShowSoftInputOnFocus");
            mShowInput.setAccessible(true);
            mShowInput.set(editorObject, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

