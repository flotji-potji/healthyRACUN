package com.example.android.myfishy.ui.profile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.User;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private String username;
    private String firstname;
    private String surname;
    private long birthday;
    private String gender;
    private float weight;
    private float height;

    User userinfo = new User(username, firstname, surname,birthday, gender,weight,height);

    private TextView txtusername;
    private TextView txtname;
    private TextView txtbirthday;
    private TextView txtgender;
    private TextView txtweight;
    private TextView txtheight;




    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2)
    {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtusername = (TextView) view.findViewById(R.id.text_username);
        txtname = (TextView) view.findViewById(R.id.text_name);
        txtgender = (TextView) view.findViewById(R.id.text_sex);
        txtbirthday = (TextView) view.findViewById(R.id.text_birthday);
        txtweight = (TextView) view.findViewById(R.id.text_weight);
        txtheight = (TextView) view.findViewById(R.id.text_height);

        if(username == null || firstname == null || surname == null || birthday == 0 || gender == null || weight == 0 || height == 0 )
        {
            String placeholder = "Placeholder";
            txtusername.setText(placeholder);
            txtname.setText(placeholder);
            txtbirthday.setText(placeholder);
            txtgender.setText(placeholder);
            txtweight.setText(placeholder);
            txtheight.setText(placeholder);
        }
        else
            {
                txtusername.setText(username);
                txtname.setText(firstname+" " + surname);
                txtbirthday.setText(Math.toIntExact(birthday));
                txtgender.setText(gender);
                txtweight.setText(Float.toString(weight));
                txtheight.setText(Float.toString(height));
            }


        return view;
    }
}