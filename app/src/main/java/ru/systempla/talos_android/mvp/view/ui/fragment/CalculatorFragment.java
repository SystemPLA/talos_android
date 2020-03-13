package ru.systempla.talos_android.mvp.view.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.presenter.CalculatorPresenter;
import ru.systempla.talos_android.mvp.view.CalculatorView;
import ru.systempla.talos_android.mvp.view.ui.DatePicker.MyDatePicker;


/**
 * A simple {@link Fragment} subclass.
 */

public class CalculatorFragment extends MvpAppCompatFragment implements CalculatorView {

    @InjectPresenter
    CalculatorPresenter presenter;

    private Unbinder unbinder;
    private ArrayList<TextInputEditText> sendFields;
    private ArrayList<TextInputEditText> calcFields;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.calc_edit_height)
    TextInputEditText editHeight;

    @BindView(R.id.calc_edit_length)
    TextInputEditText editLength;
    @BindView(R.id.calc_edit_cost_per_meter)
    TextInputEditText editSquareMeterCost;


    //    @BindView(R.id.calc_text_view_result)
//    TextView textViewResult;
    @BindView(R.id.calc_edit_stairs_frame)
    TextInputEditText editStairsFrame;
    @BindView(R.id.calc_edit_pass_frame)
    TextInputEditText editPassFrame;
    @BindView(R.id.calc_edit_diagonal_connection)
    TextInputEditText editDiagonalConnection;
    @BindView(R.id.calc_edit_horizontal_connection)
    TextInputEditText editHorizontalConnection;
    @BindView(R.id.calc_edit_crossbar)
    TextInputEditText editCrossbar;
    @BindView(R.id.calc_edit_deck)
    TextInputEditText editDeck;
    @BindView(R.id.calc_edit_supports)
    TextInputEditText editSupports;
    @BindView(R.id.calc_edit_cost_per_day)
    TextInputEditText editCostPerDay;

    @BindView(R.id.calc_edit_credit)
    TextInputEditText editCredit;
    @BindView(R.id.calc_edit_selling)
    TextInputEditText editSelling;
    @BindView(R.id.calc_edit_client)
    TextInputEditText editClient;
    @BindView(R.id.calc_edit_date)
    TextInputEditText editDate;

    @BindView(R.id.calc_loading_layout)
    FrameLayout loadingLayout;

    @BindView(R.id.calc_spinner)
    Spinner spinner;

    @OnItemSelected(R.id.calc_spinner)
    public void spinnerItemSelected(Spinner spinner, int position) {
        if (!spinner.getSelectedItem().toString().equals(getResources().getStringArray(R.array.check_client)[0])){
        editClient.setText(spinner.getSelectedItem().toString());}
    }


    @OnClick(R.id.button_calculate)
    void onClick() {
        presenter.calculatorStart(editHeight.getText().toString(),
                editLength.getText().toString(), editSquareMeterCost.getText().toString());
        if (checkFields(calcFields)) {
            checkFields(sendFields);
        }

    }

    @OnClick(R.id.button_send)
    void onClickSend() {
        if (!checkFields(sendFields)) return;


        presenter.clickSend(getDateText(), getClientText(), getStairsFrameText(), getPassFrameText(), getDiagonalConnectionText(), getHorizontalConnectionText(),
                getCrossbarText(), getDeckText(), getSupportsText());


    }

    @OnClick(R.id.calc_edit_date)
    void onClickDate() {
        new MyDatePicker(getContext(), editDate);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        unbinder = ButterKnife.bind(this, root);
        makeSendFieldsList();
        makeSpinnerClients();


        return root;
    }

    @Override
    public void showResult(int stairsFrameCount, int passFrameCount, int diagonalConnectionCount,
                           int horizontalConnectionCount, int crossbarCount, int deckCount,
                           int supportsCount, double costPerDay, double costPerMonth, double credit, double sellPriceNew, double sellPriceUsed) {
        /*textViewResult.setText(String.format(getString(R.string.calc_result_format),
                stairsFrameCount, passFrameCount, diagonalConnectionCount,
                horizontalConnectionCount, crossbarCount, deckCount,
                supportsCount, costPerDay));*/
        //textViewResult.setVisibility(View.VISIBLE);

        editStairsFrame.setText(((Integer) stairsFrameCount).toString());
        editPassFrame.setText(((Integer) passFrameCount).toString());
        editDiagonalConnection.setText(((Integer) diagonalConnectionCount).toString());
        editHorizontalConnection.setText(((Integer) horizontalConnectionCount).toString());
        editCrossbar.setText(((Integer) crossbarCount).toString());
        editDeck.setText(((Integer) deckCount).toString());
        editSupports.setText(((Integer) supportsCount).toString());
        editCostPerDay.setText(String.format("%.1f", costPerMonth) + "/" + String.format("%.1f", costPerDay));
        editCredit.setText((String.format("%.1f", credit)));
        editSelling.setText(String.format("%.1f", sellPriceNew) + "/" + String.format("%.1f", sellPriceUsed));
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getContext(), "Успешно!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showFailure() {
        Toast.makeText(getContext(), "Ошибка, попробуйте еще раз!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showLoading() {

        loadingLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingLayout.setVisibility(View.GONE);
            }
        }, 1000);

    }


    private boolean checkFields(ArrayList<TextInputEditText> fieldsToCheck) {
        boolean result = true;
        for (int i = 0; i < fieldsToCheck.size(); i++) {
            if (fieldsToCheck.get(i).getText().toString().equals("")) {
                fieldsToCheck.get(i).setError("Не может быть пустым");
                result = false;
            } else {
                fieldsToCheck.get(i).setError(null);
            }
        }
        return result;
    }

    private void makeSendFieldsList() {
        sendFields = new ArrayList<>();
        sendFields.add(editStairsFrame);
        sendFields.add(editPassFrame);
        sendFields.add(editDiagonalConnection);
        sendFields.add(editHorizontalConnection);
        sendFields.add(editCrossbar);
        sendFields.add(editDeck);
        sendFields.add(editSupports);
        sendFields.add(editClient);
        sendFields.add(editDate);

        calcFields = new ArrayList<>();
        calcFields.add(editLength);
        calcFields.add(editHeight);
        calcFields.add(editSquareMeterCost);


    }

    private void  makeSpinnerClients(){
        ArrayList<String> clientsList = new ArrayList<>();
        clientsList.add(getResources().getStringArray(R.array.check_client)[0]);
        clientsList.addAll(presenter.getClients());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, clientsList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String getStairsFrameText() {
        return editStairsFrame.getText().toString();
    }

    private String getPassFrameText() {
        return editPassFrame.getText().toString();
    }

    private String getDiagonalConnectionText() {
        return editDiagonalConnection.getText().toString();
    }

    private String getHorizontalConnectionText() {
        return editHorizontalConnection.getText().toString();
    }

    private String getCrossbarText() {
        return editCrossbar.getText().toString();
    }

    private String getDeckText() {
        return editDeck.getText().toString();
    }

    private String getSupportsText() {
        return editSupports.getText().toString();
    }

    private String getCostPerDayText() {
        return editCostPerDay.getText().toString();
    }

    private String getCreditText() {
        return editCredit.getText().toString();
    }

    private String getClientText() {
        return editClient.getText().toString();
    }

    private String getDateText() {
        return editDate.getText().toString();
      
    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
      
    }
}
