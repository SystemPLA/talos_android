package ru.systempla.talos_android.mvp.view.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.CalculatorPresenter;
import ru.systempla.talos_android.mvp.view.CalculatorView;

/**
 * A simple {@link Fragment} subclass.
 */

public class CalculatorFragment extends MvpAppCompatFragment implements CalculatorView {

    @InjectPresenter
    CalculatorPresenter presenter;

    private Unbinder unbinder;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.calc_edit_text_height)
    EditText editTextHeight;

    @BindView(R.id.calc_edit_text_lenght)
    EditText editTextLenght;

    @BindView(R.id.calc_text_view_result)
    TextView textViewResult;

    @OnClick(R.id.button_calculate)
    void onClick() {
        presenter.calculatorStart(editTextHeight.getText().toString(),
                editTextLenght.getText().toString());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void showResult(int stairsFrameCount, int passFrameCount, int diagonalConnectionCount,
                           int horizontalConnectionCount, int crossbarCount, int deckCount,
                           int supportsCount, double costPerDay) {
        textViewResult.setText(String.format(getString(R.string.calc_result_format),
                stairsFrameCount, passFrameCount, diagonalConnectionCount,
                horizontalConnectionCount, crossbarCount, deckCount,
                supportsCount, costPerDay));
        textViewResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
