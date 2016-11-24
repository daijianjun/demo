package demo.dai.com.retrofitdemoforid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.dai.com.retrofitdemoforid.model.IdData;
import demo.dai.com.retrofitdemoforid.retrofitinterface.IdCheckService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IdCheckActivity extends AppCompatActivity {

    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.btn_check)
    Button btnCheck;
    @Bind(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_check);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_check)
    public void onClick() {
        String id = etInput.getText().toString();
        if (!TextUtils.isEmpty(id)) {
            String url = "http://apis.baidu.com";
            String apikey = "8e13586b86e4b7f3758ba3bd6c9c9135";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IdCheckService idCheckService = retrofit.create(IdCheckService.class);

            Call<IdData> call = idCheckService.getIdResponse(apikey, id);
            call.enqueue(new Callback<IdData>() {
                @Override
                public void onResponse(Call<IdData> call, Response<IdData> response) {
                    tvShow.setText(response.body().getRetData().getAddress());
                }

                @Override
                public void onFailure(Call<IdData> call, Throwable t) {
                    tvShow.setText(t.getMessage());
                }
            });
        }
    }
}
