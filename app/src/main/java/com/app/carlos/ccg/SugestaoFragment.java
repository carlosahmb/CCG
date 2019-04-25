package com.app.carlos.ccg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SugestaoFragment extends Fragment {
    TextView textView;

    public SugestaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre_camelo, container, false);
        textView = view.findViewById(R.id.textViewSobreCamelo);
String sobre = "O Camelódromo de Campo Grande possui hoje mais de 450 Boxes padronizados para a comercialização " +
        "dos mais diversos tipos de produtos, trazendo geração de emprego, estabilidade e sustento. \n\n\n" +
        "Tem no Camelódromo: \n\n" +
        "*Acessibilidade para cadeirantes \n" +
        "*Área Climatizada com Ventiladores/Umidificadores \n" +
        "*Banheiro Químico \n" +
        "*Pronto Atendimento \n" +
        "*Telefone Público \n" +
        "*Lanchonete \n";


        textView.setText(sobre);
    return view;
    }
}
