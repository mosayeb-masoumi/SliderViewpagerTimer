package com.example.viewpagertimer;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


public class MyFragment extends Fragment {

    TextView txt_fragment;
    RelativeLayout root;
    Button btn;
    int position;

    public MyFragment() {
        // Required empty public constructor
    }


    public static MyFragment newInstance(int position) {
        MyFragment fragment = new MyFragment();

        fragment.position = position;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        txt_fragment = view.findViewById(R.id.txt_fragment);
        root = view.findViewById(R.id.root);
        btn = view.findViewById(R.id.btn);
        txt_fragment.setText("fragment " + position);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position % 2 == 0) {
                    Toast.makeText(getContext(), "fragment "+position + "selected" +" even", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "fragment "+position + "selected" +" odd", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (position % 2 == 0) {
          root.setBackgroundColor(Color.BLUE);
        } else {
            root.setBackgroundColor(Color.RED);
        }


        return view;
    }
}