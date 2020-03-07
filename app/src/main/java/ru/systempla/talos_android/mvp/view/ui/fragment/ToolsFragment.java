package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

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
import ru.systempla.talos_android.mvp.presenter.ToolsPresenter;
import ru.systempla.talos_android.mvp.view.MainView;
import ru.systempla.talos_android.mvp.view.ToolsView;

public class ToolsFragment extends MvpAppCompatFragment implements ToolsView {

    public static ToolsFragment newInstance() {
        return new ToolsFragment();
    }

    @InjectPresenter
    ToolsPresenter presenter;

    private Unbinder unbinder;

    @OnClick(R.id.button_start_calculator)
    void onStartClick() {
        presenter.startCalculator();
    }

    @OnClick(R.id.button_accept_refund)
    void onRefundClick() {
        presenter.startRefund();
    }

    @OnClick(R.id.button_arriving_of_goods)
    void onArriveClick() {
        presenter.startArriving();
    }

    @ProvidePresenter
    public ToolsPresenter providePresenter() {
        ToolsPresenter presenter = new ToolsPresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        App.getInstance().getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainView)getActivity()).showBottomNavigation();
    }


    @Override
    public void init() {
        //Саня, привет)
    }

    @Override
    public void startCalculator() {

        //TODO это я сделаю потом через cicerone. UPD:хаха, делать мне нечего)
        CalculatorFragment calculatorFragment = new CalculatorFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, calculatorFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        ((MainView)getActivity()).hideBottomNavigation();
    }

    @Override
    public void startRefund() {
        RefundFragment refundFragment = new RefundFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, refundFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        ((MainView)getActivity()).hideBottomNavigation();
    }

    @Override
    public void startArriving() {
        ArrivingFragment arrivingFragment = new ArrivingFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, arrivingFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        ((MainView)getActivity()).hideBottomNavigation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
