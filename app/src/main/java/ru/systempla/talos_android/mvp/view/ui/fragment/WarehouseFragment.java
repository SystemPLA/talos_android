package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @OnClick(R.id.fab)
    void onFabClick() {
        presenter.onFabClicked();
    }

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
        fab.setVisibility(View.GONE);
        loadingRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingRelativeLayout.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
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
        PopupMenu popupMenu = new PopupMenu(this.getContext(), recyclerView.getChildAt(position),
                Gravity.END);
        popupMenu.inflate(R.menu.warehouse_item_menu);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.show_item:
                    presenter.onShowMenuPressed(position);
                    return true;
                case R.id.change_item:
                    presenter.onChangeMenuPressed(position);
                    return true;
                case R.id.delete_item:
                    presenter.onDeleteMenuPressed(position);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }
}
