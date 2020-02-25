package ru.systempla.talos_android.mvp.view.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.ShipmentsListPresenter;
import ru.systempla.talos_android.mvp.view.list.ShipmentsItemView;


public class ShipmentsAdapter extends RecyclerView.Adapter<ShipmentsAdapter.ShipmentsViewHolder> {

    private final ShipmentsListPresenter presenter;
    private ViewGroup parentViewGroup; //для доступа к ресурсам в ShipmentsViewHolder

    public ShipmentsAdapter(ShipmentsListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ShipmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parentViewGroup = parent;
        return new ShipmentsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shipments_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShipmentsViewHolder holder, int position) {
        presenter.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }


    public class ShipmentsViewHolder extends RecyclerView.ViewHolder implements ShipmentsItemView {


        @BindView(R.id.text_view_shipments_customer)
        TextView shipmentCustomer;

        @BindView(R.id.text_view_shipments_date)
        TextView shipmentDate;

        @BindView(R.id.text_view_shipments_type)
        TextView shipmentType;

        @BindView(R.id.image_view_shipments_status)
        ImageView shipmentStatus;

        @BindDrawable(R.drawable.ic_checked)
        Drawable icChecked;

        @BindDrawable(R.drawable.ic_unchecked)
        Drawable icUnchecked;

//        @BindView(R.id.button_shipments_more)
//        Button buttonMore;

        @OnClick(R.id.button_shipments_more)
        void onClick() {
            presenter.showMore();
        }

        private ShipmentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void setCustomer(String customer) {
            shipmentCustomer.setText(customer);
        }

        @Override
        public void setDate(String date) {
            shipmentDate.setText(date);
        }

        @Override
        public void setType(String type) {
            shipmentType.setText(type);
        }

        @Override
        public void setStatus(Boolean status) {
            if (status) {
                shipmentStatus.setImageDrawable(icChecked);
            } else {
                shipmentStatus.setImageDrawable(icUnchecked);
            }
        }
    }
}
