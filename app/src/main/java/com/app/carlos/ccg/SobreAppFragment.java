package com.app.carlos.ccg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SobreAppFragment extends Fragment {
    TextView textView;

    public SobreAppFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre_camelo, container, false);
        textView = view.findViewById(R.id.textViewSobreApp);
String sobre = "CCG Camelódromo /n" +
        " Versão 1.0.0";


        textView.setText(sobre);
    return view;
    }
}
