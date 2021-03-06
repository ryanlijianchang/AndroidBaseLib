package com.ryan.demo.utils;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryan.androidbaselib.R;
import com.ryan.common.wrapper.OnRecyclerItemClickListener;
import com.ryan.demo.utils.sub.DensityUtilsDemoAty;
import com.ryan.demo.utils.sub.DevicesUtilsDemoAty;
import com.ryan.demo.utils.sub.TelephonyUtilsDemoAty;

import static com.ryan.baselib.util.AppUtils.getContext;

/**
 * @author RyanLee
 */
public class UtilsFragment extends Fragment implements OnRecyclerItemClickListener {

    public UtilsFragment() {
    }

    public static UtilsFragment newInstance() {
        UtilsFragment fragment = new UtilsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_utils, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.rv_utils_fm_list);
        UtilsRecyclerAdapter adapter = new UtilsRecyclerAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), DensityUtilsDemoAty.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), TelephonyUtilsDemoAty.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(), DevicesUtilsDemoAty.class));
            default:
                break;
        }
    }
}
