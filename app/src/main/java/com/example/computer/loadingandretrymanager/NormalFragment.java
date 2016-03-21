package com.example.computer.loadingandretrymanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lib.loadingandretrymanager.Listener.OnLoadingAndRetryListener;
import com.lib.loadingandretrymanager.base.LoadingAndRetryManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * Created by zhy on 15/8/27.
 */
public class NormalFragment extends Fragment {
    LoadingAndRetryManager mLoadingAndRetryManager;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button4)
    Button button4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                NormalFragment.this.setRetryEvent(retryView);
            }
        });

        mLoadingAndRetryManager.showLoading();
    }


    public void setRetryEvent(View retryView) {
        View view = retryView.findViewById(R.id.id_btn_retry);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "retry event invoked", Toast.LENGTH_SHORT).show();
                mLoadingAndRetryManager.showLoading();
                loadData();
            }
        });
    }

    private void loadData() {


        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                if (Math.random() > 0.6)
                if (0.7 > 0.6) {
                    mLoadingAndRetryManager.showContent();
                } else {
                    mLoadingAndRetryManager.showRetry();
                }
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.button, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


