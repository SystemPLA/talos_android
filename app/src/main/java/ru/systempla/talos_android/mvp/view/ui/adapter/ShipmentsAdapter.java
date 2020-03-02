package ru.systempla.talos_android.mvp.view.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.IShipmentsListPresenter;
import ru.systempla.talos_android.mvp.view.list.ShipmentsItemView;


public class ShipmentsAdapter extends RecyclerView.Adapter<ShipmentsAdapter.ShipmentsViewHolder> {

    private final IShipmentsListPresenter presenter;
    private ViewGroup parentViewGroup; //для доступа к ресурсам в ShipmentsViewHolder

    public ShipmentsAdapter(IShipmentsListPresenter presenter) {
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
        holder.pos = position;
        presenter.bind(holder);
        RxView.clicks(holder.itemView).map(o -> holder).subscribe(presenter.getClickSubject());
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }


    public class ShipmentsViewHolder extends RecyclerView.ViewHolder implements ShipmentsItemView {

        int pos = 0;

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

        private ShipmentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getPos() {
            return pos;
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
