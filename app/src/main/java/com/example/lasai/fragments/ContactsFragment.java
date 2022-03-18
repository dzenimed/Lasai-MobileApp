package com.example.lasai.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lasai.R;


public class ContactsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts,
                container, false);
        Button button = (Button) view.findViewById(R.id.button_contacts);
        EditText txt1=view.findViewById(R.id.editTextPhone);
        EditText txt2=view.findViewById(R.id.editTextPhone2);
        EditText txt3=view.findViewById(R.id.editTextPhone2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("key1",txt1.getText().toString());
                bundle.putString("key2",txt2.getText().toString());
                bundle.putString("key3",txt3.getText().toString());
                getParentFragmentManager().setFragmentResult("key",bundle);


            }
        });


        return view;
    }

}

