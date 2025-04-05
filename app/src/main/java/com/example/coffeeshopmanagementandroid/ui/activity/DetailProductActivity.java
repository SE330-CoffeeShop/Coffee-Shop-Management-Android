package com.example.coffeeshopmanagementandroid.ui.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.ui.adapter.VariantProductAdapter;

import java.util.ArrayList;
import java.util.List;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

public class DetailProductActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private ImageButton backButton;
    private ImageButton favoriteButton;
    private TextView productNameTextView;
    private TextView categoryTextView;
    private ImageView productImage;

    private boolean isExpanded = false;
    private static final String READ_MORE = "Read more";
    private static final String COLLAPSE = "Collapse";
    private static final String ELLIPSIS = "... ";
    private static final int MAX_LINES = 3;
    private final int expandCollapseColor = Color.parseColor("#4CAF50"); // Green color
    private final String fullDescription = "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85 ml of fresh milk the fo A cappuccino cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85 ml of fresh milk the fo A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85 ml of fresh milk the fo";

    private VariantProductAdapter variantProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        initializeViews();
        setupExpandableText(fullDescription);

        List<String> variantsProduct = new ArrayList<>();
        variantsProduct.add("M");
        variantsProduct.add("S");
        variantsProduct.add("L");
        variantsProduct.add("XL");

        variantProductAdapter = new VariantProductAdapter(variantsProduct);
        RecyclerView variantProductRecyclerView = findViewById(R.id.variantProductRecyclerView);

        // Cấu hình FlexboxLayoutManager
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        variantProductRecyclerView.setLayoutManager(flexboxLayoutManager);

        // Thêm khoảng cách giữa các hàng
        int verticalSpacing = getResources().getDimensionPixelSize(R.dimen.vertical_spacing);
        variantProductRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(verticalSpacing));

        variantProductRecyclerView.setAdapter(variantProductAdapter);
    }

    private void initializeViews() {

        descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
        descriptionTextView.setHighlightColor(Color.TRANSPARENT);

        backButton = findViewById(R.id.backButtonDetailProduct);
        backButton.setOnClickListener(v -> {
            finish();
        });

        favoriteButton = findViewById(R.id.favoriteButtonDetailProduct);
        favoriteButton.setOnClickListener(v -> {
            Toast.makeText(this, "Favorite button clicked", Toast.LENGTH_SHORT).show();
        });
    }

    // Lớp ItemDecoration để thêm khoảng cách dọc
    public static class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalSpaceHeight;
            }
        }
    }

    private void setupExpandableText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        descriptionTextView.setMaxLines(isExpanded ? Integer.MAX_VALUE : MAX_LINES);

        if (isExpanded) {
            // Hiển thị toàn bộ text với tùy chọn thu gọn
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            builder.append("\n");

            SpannableString collapseSpannable = new SpannableString(COLLAPSE);

            // Tạo clickable span cho "Collapse"
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    isExpanded = false;
                    setupExpandableText(text);
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(expandCollapseColor);
                    ds.setUnderlineText(false);
                }
            };

            // Thêm style bold cho text "Collapse"
            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

            // Áp dụng cả 2 span cho text
            collapseSpannable.setSpan(clickableSpan, 0, COLLAPSE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            collapseSpannable.setSpan(boldSpan, 0, COLLAPSE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            builder.append(collapseSpannable);
            descriptionTextView.setText(builder);

            // Log để debug
            Log.d("ExpandableText", "Text expanded, showing collapse option");
        } else {
            // Ban đầu đặt text đầy đủ
            descriptionTextView.setText(text);

            // Post để đo lường text sau khi layout hoàn thành
            descriptionTextView.post(() -> {
                // Kiểm tra xem text có cần mở rộng không
                if (descriptionTextView.getLineCount() > MAX_LINES) {
                    // Log để debug
                    Log.d("ExpandableText", "Text needs expanding, line count: " + descriptionTextView.getLineCount());

                    Layout layout = descriptionTextView.getLayout();
                    if (layout == null) {
                        Log.e("ExpandableText", "Layout is null");
                        return;
                    }

                    int lineEndIndex;
                    try {
                        lineEndIndex = layout.getLineEnd(MAX_LINES - 1);
                        // Giảm một chút để đảm bảo có đủ chỗ cho ELLIPSIS và READ_MORE
                        int reserve = ELLIPSIS.length() + READ_MORE.length() + 10;
                        if (lineEndIndex - reserve < 0) {
                            lineEndIndex = text.length();
                        } else {
                            lineEndIndex = lineEndIndex - reserve;
                        }
                    } catch (Exception e) {
                        Log.e("ExpandableText", "Error getting line end: " + e.getMessage());
                        return;
                    }

                    // Tính toán vị trí cắt text
                    String truncatedText = text.substring(0, lineEndIndex);

                    // Log để debug
                    Log.d("ExpandableText", "Truncated at index: " + lineEndIndex);
                    Log.d("ExpandableText", "Truncated text: " + truncatedText);

                    SpannableStringBuilder builder = new SpannableStringBuilder(truncatedText);
                    builder.append(ELLIPSIS);

                    SpannableString readMoreSpannable = new SpannableString(READ_MORE);

                    // Tạo clickable span cho "Read more"
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            Log.d("ExpandableText", "Read more clicked");
                            isExpanded = true;
                            setupExpandableText(text);
                        }

                        @Override
                        public void updateDrawState(@NonNull TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(expandCollapseColor);
                            ds.setUnderlineText(false);
                        }
                    };

                    // Thêm style bold cho text "Read more"
                    StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

                    // Áp dụng cả 2 span cho text
                    readMoreSpannable.setSpan(clickableSpan, 0, READ_MORE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    readMoreSpannable.setSpan(boldSpan, 0, READ_MORE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    builder.append(readMoreSpannable);
                    descriptionTextView.setText(builder);
                } else {
                    // Text không cần mở rộng, chỉ hiển thị nguyên bản
                    Log.d("ExpandableText", "Text doesn't need expanding");
                    descriptionTextView.setText(text);
                }
            });
        }
    }
}