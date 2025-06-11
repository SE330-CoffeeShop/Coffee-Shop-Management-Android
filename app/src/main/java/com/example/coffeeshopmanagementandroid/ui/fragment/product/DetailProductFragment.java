package com.example.coffeeshopmanagementandroid.ui.fragment.product;

import android.content.Context;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeeshopmanagementandroid.R;
import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;
import com.example.coffeeshopmanagementandroid.ui.adapter.VariantProductAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class DetailProductFragment extends Fragment {

    private TextView descriptionTextView;
    private ImageButton backButton;
    private ImageButton favoriteButton;
    private TextView productNameTextView;
    private TextView categoryTextView;
    private ImageView productImage;
    private NavController navController;

    private boolean isExpanded = false;
    private static final String READ_MORE = "Read more";
    private static final String COLLAPSE = "Collapse";
    private static final String ELLIPSIS = "... ";
    private static final int MAX_LINES = 3;
    private final int expandCollapseColor = Color.parseColor("#4CAF50");

    private VariantProductAdapter variantProductAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews();
        navController = NavHostFragment.findNavController(this);

        // Lấy dữ liệu sản phẩm từ arguments
        Bundle args = getArguments();
        ProductModel product = args != null ? args.getParcelable("product") : null;

        if (product != null) {
            productNameTextView.setText(product.getProductName());
            descriptionTextView.setText(product.getProductDescription());
            categoryTextView.setText(product.getProductCategoryId());
            loadProductImage(product.getProductThumb());
            setupExpandableText(product.getProductDescription());
//            setupVariants(product.getVariants() != null ? product.getVariants() : new ArrayList<>());
        } else {
            Toast.makeText(requireContext(), "Product data not found", Toast.LENGTH_SHORT).show();
            // Không dùng finish(), thay bằng navigateUp hoặc popBackStack
            if (navController != null) {
                navController.popBackStack();
            }
        }
    }

    private void initializeViews() {
        descriptionTextView = requireView().findViewById(R.id.descriptionTextView);
        if (descriptionTextView != null) {
            descriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
            descriptionTextView.setHighlightColor(Color.TRANSPARENT);
        }

        backButton = requireView().findViewById(R.id.backButtonDetailProduct);
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                if (navController != null) {
                    navController.popBackStack();
                }
            });
        }

        favoriteButton = requireView().findViewById(R.id.favoriteButtonDetailProduct);
        if (favoriteButton != null) {
            favoriteButton.setOnClickListener(v -> Toast.makeText(requireContext(), "Favorite button clicked", Toast.LENGTH_SHORT).show());
        }

        productNameTextView = requireView().findViewById(R.id.productNameTextView);
        categoryTextView = requireView().findViewById(R.id.categoryTextView);
        productImage = requireView().findViewById(R.id.productImage);
    }

    private void loadProductImage(String imageUrl) {
        if (productImage != null && imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(requireContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(productImage);
        } else {
            productImage.setImageResource(R.drawable.placeholder_image);
        }
    }

    private void setupVariants(List<String> variants) {
        variantProductAdapter = new VariantProductAdapter(variants);
        RecyclerView variantProductRecyclerView = requireView().findViewById(R.id.variantProductRecyclerView);
        if (variantProductRecyclerView != null) {
            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(requireContext());
            flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
            flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
            flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
            variantProductRecyclerView.setLayoutManager(flexboxLayoutManager);
            int verticalSpacing = getResources().getDimensionPixelSize(R.dimen.vertical_spacing);
            variantProductRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(verticalSpacing));
            variantProductRecyclerView.setAdapter(variantProductAdapter);
        }
    }

    private void setupExpandableText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }

        descriptionTextView.setMaxLines(isExpanded ? Integer.MAX_VALUE : MAX_LINES);

        if (isExpanded) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            builder.append("\n");

            SpannableString collapseSpannable = new SpannableString(COLLAPSE);

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

            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

            collapseSpannable.setSpan(clickableSpan, 0, COLLAPSE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            collapseSpannable.setSpan(boldSpan, 0, COLLAPSE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            builder.append(collapseSpannable);
            descriptionTextView.setText(builder);

            Log.d("ExpandableText", "Text expanded, showing collapse option");
        } else {
            descriptionTextView.setText(text);

            descriptionTextView.post(() -> {
                if (descriptionTextView.getLineCount() > MAX_LINES) {
                    Log.d("ExpandableText", "Text needs expanding, line count: " + descriptionTextView.getLineCount());

                    Layout layout = descriptionTextView.getLayout();
                    if (layout == null) {
                        Log.e("ExpandableText", "Layout is null");
                        return;
                    }

                    int lineEndIndex;
                    try {
                        lineEndIndex = layout.getLineEnd(MAX_LINES - 1);
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

                    String truncatedText = text.substring(0, lineEndIndex);

                    Log.d("ExpandableText", "Truncated at index: " + lineEndIndex);
                    Log.d("ExpandableText", "Truncated text: " + truncatedText);

                    SpannableStringBuilder builder = new SpannableStringBuilder(truncatedText);
                    builder.append(ELLIPSIS);

                    SpannableString readMoreSpannable = new SpannableString(READ_MORE);

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

                    StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

                    readMoreSpannable.setSpan(clickableSpan, 0, READ_MORE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    readMoreSpannable.setSpan(boldSpan, 0, READ_MORE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    builder.append(readMoreSpannable);
                    descriptionTextView.setText(builder);
                } else {
                    Log.d("ExpandableText", "Text doesn't need expanding");
                    descriptionTextView.setText(text);
                }
            });
        }
    }

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
}