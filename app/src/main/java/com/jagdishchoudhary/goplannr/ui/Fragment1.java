package com.jagdishchoudhary.goplannr.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.jagdishchoudhary.goplannr.R;
import com.jagdishchoudhary.goplannr.model.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Button btnContinue;
    private TextInputEditText textName, textPhone;
    private Gson gson;

    public static final String PREFS_NAME = "USER_PREFS";


    private static final String TAG = "UserDetails1";



    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        Context context = getActivity();
        SharedPreferences sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        textName = (TextInputEditText)v.findViewById(R.id.textName);
        textPhone = (TextInputEditText)v.findViewById(R.id.textPhone);
        btnContinue = (Button)v.findViewById(R.id.btnContinue);
//        gson = new Gson();
//        String getJson = sp.getString("MyUser", "" );
//        User user = gson.fromJson(getJson, User.class);
        //Toast.makeText(getActivity(), "User values:" + user.getName(), Toast.LENGTH_SHORT).show();
        if ((sp.contains("Name")) && (sp.contains("Phone"))) {
            String userName = sp.getString("Name", "User Name");
            textName.setText(userName);
            String userPhone = sp.getString("Phone", "User Phone");
            textPhone.setText(userPhone);

            Toast.makeText(context, "Shared Pref:" + userName, Toast.LENGTH_SHORT).show();
        }




        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                User user = new User(textName.getText().toString(), textPhone.getText().toString(), 1,1);
//
//                String json = gson.toJson(User.class);
//                editor.putString("MyUser", json);
//                editor.commit();
                if ((textName.length() == 0) || (textPhone.length() < 10)){
                    Toast.makeText(getActivity(), "Please enter your details", Toast.LENGTH_SHORT).show();
                }
                else {
                editor.putString("Name", textName.getText().toString());
                editor.putString("Phone", textPhone.getText().toString());
                editor.commit();
                Navigation.findNavController(v).navigate(R.id.fragmentAtoB);
            }}
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
