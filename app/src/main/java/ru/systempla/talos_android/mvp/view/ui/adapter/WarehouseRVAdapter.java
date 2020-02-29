package ru.systempla.talos_android.mvp.view.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.IWarehouseListPresenter;
import ru.systempla.talos_android.mvp.view.list.WarehouseItemView;

public class WarehouseRVAdapter extends RecyclerView.Adapter<WarehouseRVAdapter.ViewHolder> {

    private IWarehouseListPresenter presenter;

    public WarehouseRVAdapter(IWarehouseListPresenter presenter) {
        this.presenter = presenter;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.warehouse_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bind(holder);
        RxView.clicks(holder.itemView).map(o -> holder).subscribe(presenter.getClickSubject());
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements WarehouseItemView {

        int pos = 0;

        @BindView(R.id.item_name_value)
        TextView rvName;

        @BindView(R.id.item_status_value)
        TextView rvStatus;

        @BindView(R.id.item_count_value)
        TextView rvCount;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setName(String name) {
            rvName.setText(name);
        }

        @Override
        public void setStatus(String status) {
            if (status.equals("брак")) {
                rvStatus.setText(status);
            }
            else {
                rvStatus.setText("");
            }
        }

        @Override
        public void setCount(long count) {
            rvCount.setText(String.valueOf(count));
        }

    }
}


