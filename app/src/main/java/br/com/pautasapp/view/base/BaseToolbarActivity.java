package br.com.pautasapp.view.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import br.com.pautasapp.R;
import butterknife.BindView;

public abstract class BaseToolbarActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}