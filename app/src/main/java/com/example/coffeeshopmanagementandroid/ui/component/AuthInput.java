// AuthInput.java
package com.example.coffeeshopmanagementandroid.ui.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coffeeshopmanagementandroid.R;

public class AuthInput extends LinearLayout {

    private TextView inputLabel;
    private EditText inputField;

    public AuthInput(Context context) {
        super(context);
        init(context, null);
    }

    public AuthInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.auth_input, this, true); // Thay thế your_layout_file_name bằng tên tệp XML bố cục thực tế của bạn.

        setOrientation(VERTICAL);

        inputLabel = findViewById(R.id.labelTtext);
        inputField = findViewById(R.id.hintText);

        // Xử lý thuộc tính (nếu cần)
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AuthInput); // Tạo styleable trong attrs.xml
            String labelText = typedArray.getString(R.styleable.AuthInput_labelText);
            String hintText = typedArray.getString(R.styleable.AuthInput_hintText);

            inputLabel.setText(labelText != null ? labelText : "Label");
            inputField.setHint(hintText != null ? hintText : "Label");

            typedArray.recycle();
        }
    }

    public void setLabel(String label) {
        inputLabel.setText(label);
    }

    public String getLabel() {
        return inputLabel.getText().toString();
    }

    public void setText(String text) {
        inputField.setText(text);
    }

    public String getText() {
        return inputField.getText().toString();
    }

    public void setHint(String hint){
        inputField.setHint(hint);
    }

    public EditText getEditText(){
        return inputField;
    }
}