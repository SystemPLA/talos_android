package ru.systempla.talos_android.mvp.view.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.model.api.MyApi;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
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
    @BindView(R.id.editTextSquareMeterCost)
    EditText editTextSquareMeterCost;


    //    @BindView(R.id.calc_text_view_result)
//    TextView textViewResult;
    @BindView(R.id.editTextStairsFrame)
    EditText editTextStairsFrame;
    @BindView(R.id.editTextPassFrame)
    EditText editTextPassFrame;
    @BindView(R.id.editTextDiagonalConnection)
    EditText editTextDiagonalConnection;
    @BindView(R.id.editTextHorizontalConnection)
    EditText editTextHorizontalConnection;
    @BindView(R.id.editTextCrossbar)
    EditText editTextCrossbar;
    @BindView(R.id.editTextDeck)
    EditText editTextDeck;
    @BindView(R.id.editTextSupports)
    EditText editTextSupports;
    @BindView(R.id.editTextCostPerDay)
    EditText editTextCostPerDay;

    @BindView(R.id.editTextCredit40)
    EditText editTextCredit40;
    @BindView(R.id.editTextClient)
    EditText editTextClient;

    @OnClick(R.id.button_calculate)
    void onClick() {
        presenter.calculatorStart(editTextHeight.getText().toString(),
                editTextLenght.getText().toString(), editTextSquareMeterCost.getText().toString());
    }

    @OnClick(R.id.button_send)
    void onClickSend() {
        //Toast.makeText(getContext(), "0", Toast.LENGTH_SHORT).show();
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
// Базовая часть адреса
                .baseUrl("http://35.237.102.95:8080/")
// Конвертер, необходимый для преобразования JSON в объекты
                .addConverterFactory(GsonConverterFactory.create())
                .build();
// Создаем объект, при помощи которого будем выполнять запросы
        //Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
        MyApi myApi = retrofit.create(MyApi.class);
        //Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
        myApi.sendStorageOperation(new StorageOperation("2020-03-03", editTextClient.getText().toString(),
                "Отгрузка", false, Integer.parseInt(editTextStairsFrame.getText().toString()),
                Integer.parseInt(editTextPassFrame.getText().toString()),
                Integer.parseInt(editTextDiagonalConnection.getText().toString()),
                Integer.parseInt(editTextHorizontalConnection.getText().toString()),
                Integer.parseInt(editTextCrossbar.getText().toString()),
                Integer.parseInt(editTextDeck.getText().toString()),
                Integer.parseInt(editTextSupports.getText().toString())))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
                    }
                });


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
        /*textViewResult.setText(String.format(getString(R.string.calc_result_format),
                stairsFrameCount, passFrameCount, diagonalConnectionCount,
                horizontalConnectionCount, crossbarCount, deckCount,
                supportsCount, costPerDay));*/
        //textViewResult.setVisibility(View.VISIBLE);
        editTextStairsFrame.setText(((Integer) stairsFrameCount).toString());
        editTextPassFrame.setText(((Integer) passFrameCount).toString());
        editTextDiagonalConnection.setText(((Integer) diagonalConnectionCount).toString());
        editTextHorizontalConnection.setText(((Integer) horizontalConnectionCount).toString());
        editTextCrossbar.setText(((Integer) crossbarCount).toString());
        editTextDeck.setText(((Integer) deckCount).toString());
        editTextSupports.setText(((Integer) supportsCount).toString());
        editTextCostPerDay.setText(((Double) costPerDay).toString());
        editTextCredit40.setText(((Double) (costPerDay * 0.4)).toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
