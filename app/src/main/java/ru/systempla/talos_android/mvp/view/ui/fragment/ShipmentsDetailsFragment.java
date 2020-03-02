package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.presenter.ShipmentsDetailsPresenter;
import ru.systempla.talos_android.mvp.presenter.WarehouseDetailsPresenter;
import ru.systempla.talos_android.mvp.view.ShipmentDetailsView;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;

public class ShipmentsDetailsFragment extends MvpAppCompatFragment implements ShipmentDetailsView {

    public static ShipmentsDetailsFragment newInstance(StorageOperation storageOperation) {
        ShipmentsDetailsFragment fragment = new ShipmentsDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("storageOperation", storageOperation);
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    ShipmentsDetailsPresenter presenter;

    @BindDrawable(R.drawable.ic_checked)
    Drawable icChecked;

    @BindDrawable(R.drawable.ic_unchecked)
    Drawable icUnchecked;

    @BindView(R.id.text_id_shipment)
    TextView shipmentId;

    @BindView(R.id.text_status_shipment)
    ImageView shipmentStatus;

    @BindView(R.id.text_date_shipment)
    TextView shipmentDate;

    @BindView(R.id.text_client_shipment)
    TextView shipmentClient;

    @BindView(R.id.text_client_type_shipment)
    TextView shipmentType;

    @BindView(R.id.text_stairs_frame)
    TextView shipmentStairsFrameCount;

    @BindView(R.id.text_stairs_frame_bad)
    TextView shipmentBadStairsFrameCount;

    @BindView(R.id.text_pass_frame)
    TextView shipmentPassFrameCount;

    @BindView(R.id.text_pass_frame_bad)
    TextView shipmentBadPassFrameCount;

    @BindView(R.id.text_diagonal_connection)
    TextView shipmentDiagonalConnectionCount;

    @BindView(R.id.text_diagonal_connection_bad)
    TextView shipmentBadDiagonalConnectionCount;

    @BindView(R.id.text_horizontal_connection)
    TextView shipmentHorizontalConnectionCount;

    @BindView(R.id.text_horizontal_connection_bad)
    TextView shipmentBadHorizontalConnectionCount;

    @BindView(R.id.text_crossbar)
    TextView shipmentCrossbar;

    @BindView(R.id.text_crossbar_bad)
    TextView shipmentBadCrossbar;

    @BindView(R.id.text_deck)
    TextView shipmentDeckCount;

    @BindView(R.id.text_deck_bad)
    TextView shipmentBadDeckCount;

    @BindView(R.id.text_support)
    TextView shipmentSupportsCount;

    @BindView(R.id.text_support_bad)
    TextView shipmentBadSupportsCount;


    @ProvidePresenter
    public ShipmentsDetailsPresenter providePresenter() {

        ShipmentsDetailsPresenter presenter = new ShipmentsDetailsPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), (StorageOperation) getArguments().getSerializable("storageOperation"));
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_shipment_item_fragment, container, false);
        ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }


    @Override
    public void onResume() {
        presenter.onFragmentResume();
        super.onResume();
    }


    @Override
    public void setToolbarTitle(String title) {
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void setShipmentId(String id) {
        shipmentId.setText(id);
    }

    @Override
    public void setShipmentDate(String date) {
        shipmentDate.setText(date);
    }

    @Override
    public void setShipmentType(String type) {
        shipmentType.setText(type);
    }

    @Override
    public void setShipmentClient(String client) {
        shipmentClient.setText(client);
    }

    @Override
    public void setShipmentStairsFrameCount(String count) {
        shipmentStairsFrameCount.setText(count);
    }

    @Override
    public void setShipmentBadStairsFrameCount(String count) {
        shipmentBadStairsFrameCount.setText(count);
    }

    @Override
    public void setShipmentPassFrameCount(String count) {
        shipmentPassFrameCount.setText(count);
    }

    @Override
    public void setShipmentBadPassFrameCount(String count) {
        shipmentBadPassFrameCount.setText(count);
    }

    @Override
    public void setShipmentDiagonalConnectionCount(String count) {
        shipmentDiagonalConnectionCount.setText(count);
    }

    @Override
    public void setShipmentBadDiagonalConnectionCount(String count) {
        shipmentBadDiagonalConnectionCount.setText(count);
    }

    @Override
    public void setShipmentHorizontalConnectionCount(String count) {
        shipmentHorizontalConnectionCount.setText(count);
    }

    @Override
    public void setShipmentBadHorizontalConnectionCount(String count) {
        shipmentBadHorizontalConnectionCount.setText(count);
    }

    @Override
    public void setShipmentCrossbarCount(String count) {
        shipmentCrossbar.setText(count);
    }

    @Override
    public void setShipmentBadCrossbarCount(String count) {
        shipmentBadCrossbar.setText(count);
    }

    @Override
    public void setShipmentDeckCount(String count) {
        shipmentDeckCount.setText(count);
    }

    @Override
    public void setShipmentBadDeckCount(String count) {
        shipmentBadDeckCount.setText(count);
    }

    @Override
    public void setShipmentSupportsCount(String count) {
        shipmentSupportsCount.setText(count);
    }

    @Override
    public void setShipmentBadSupportsCount(String count) {
        shipmentBadSupportsCount.setText(count);
    }

    @Override
    public void setPerformedTo(Boolean status) {
        if (status) {
            shipmentStatus.setImageDrawable(icChecked);
        } else {
            shipmentStatus.setImageDrawable(icUnchecked);
        }
    }
}