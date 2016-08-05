package com.hpedrorodrigues.researcher.ui.component;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpedrorodrigues.researcher.R;
import com.hpedrorodrigues.researcher.api.entity.Image;
import com.hpedrorodrigues.researcher.constant.BundleKey;
import com.hpedrorodrigues.researcher.util.general.ApiUtil;
import com.koushikdutta.ion.Ion;

import javax.inject.Inject;

public class ImageDetailDialog extends DialogFragment {

    @Inject
    public Context context;

    @Inject
    public ApiUtil apiUtil;

    private Image image;
    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;
    private TextView sizeView;
    private TextView apiView;

    public static ImageDetailDialog create(Image image) {
        ImageDetailDialog dialog = new ImageDetailDialog();

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKey.IMAGE, image);

        dialog.setArguments(bundle);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        image = (Image) getArguments().getSerializable(BundleKey.IMAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_detail, container, false);

        onView(view);
        fillView();

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

    private void onView(View view) {
        imageView = (ImageView) view.findViewById(R.id.image);
        titleView = (TextView) view.findViewById(R.id.title);
        descriptionView = (TextView) view.findViewById(R.id.description);
        sizeView = (TextView) view.findViewById(R.id.size);
        apiView = (TextView) view.findViewById(R.id.api);
    }

    private void fillView() {
        Ion.with(context).load(image.getThumbnailUrl()).intoImageView(imageView);

        titleView.setText(image.getTitle());
        descriptionView.setText(image.getDescription());

        sizeView.setText(String.format("%s x %s", image.getWidth(), image.getHeight()));

        String providerName = context.getString(apiUtil.getProviderNameByApi(image.getApi()));
        apiView.setText(providerName);
    }
}