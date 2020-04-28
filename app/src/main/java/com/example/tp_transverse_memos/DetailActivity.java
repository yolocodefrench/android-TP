package com.example.tp_transverse_memos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // init :
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // fragment :
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.EXTRA_MEMO, getIntent().getStringExtra(DetailFragment.EXTRA_MEMO));
        fragment.setArguments(bundle);

        // ajout / remplacement fragment :
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_detail, fragment).commit();
    }

}
