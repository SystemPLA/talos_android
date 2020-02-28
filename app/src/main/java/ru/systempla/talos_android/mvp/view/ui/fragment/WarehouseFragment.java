package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.presenter.WarehousePresenter;
import ru.systempla.talos_android.mvp.view.WarehouseView;
import ru.systempla.talos_android.mvp.view.ui.adapter.WarehouseRVAdapter;

public class WarehouseFragment extends MvpAppCompatFragment implements WarehouseView {

    public static WarehouseFragment newInstance(){
        return new WarehouseFragment();
    }

    private WarehouseRVAdapter adapter;
    private Unbinder unbinder;

    @InjectPresenter
    WarehousePresenter presenter;

    @BindView(R.id.warehouse_rv)
    RecyclerView recyclerView;

    @BindView(R.id.rl_loading)
    RelativeLayout loadingRelativeLayout;

    @ProvidePresenter
    public WarehousePresenter providePresenter(){
        WarehousePresenter presenter = new WarehousePresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.warehouse_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadWarehouseData();
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WarehouseRVAdapter(presenter.getWarehouseListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void inflateSubmenu(int position) {
        PopupMenu popupMenu = new PopupMenu(this.getContext(), recyclerView.getChildAt(position));
        popupMenu.inflate(R.menu.warehouse_item_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.change_item:

                    return true;
                case R.id.delete_item:

                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }
}
