package com.jagdishchoudhary.goplannr.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.jagdishchoudhary.goplannr.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayAdapter<String> ageAdapter, salaryAdapter;
    private Button btnContinue;
    public static final String PREFS_NAME = "USER_DETAILS";

    private OnFragmentInteractionListener mListener;
    private String userAge, userSalary;
    private TextInputEditText editAge;
    private TextView editSalary;
    private boolean ageSpinnertouched, salarySpinnertouched;
    private Spinner salarySpinner;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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

        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);

        Context context = getActivity();
        //editAge = (TextInputEditText)v.findViewById(R.id.editAge);
        //editSalary = (TextView) v.findViewById(R.id.editSalary);

        final Spinner ageSpinner = v.findViewById(R.id.ageSpinner);
        SharedPreferences sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        btnContinue = (Button)v.findViewById(R.id.btnNext);

        if ((sp.contains("Age")) && (sp.contains("Salary"))){
            String userAge = sp.getString("Age", "1 years");

            String userSalary = sp.getString("Salary", "1 LPA");
            Toast.makeText(context, "details:" + userAge + userSalary, Toast.LENGTH_SHORT).show();
        }

        final List<String> age = new ArrayList<>();
        age.add("Select your age");
        for (int i = 1; i <= 100; i++) {
            age.add(Integer.toString(i) + " years");
        }
        final List<String> salary = new ArrayList<>();
        salary.add("Select your salary");
        for (int i = 1; i <= 40; i++) {
            salary.add(Integer.toString(i) + " LPA");
        }


        ageAdapter =  new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                age
        );

        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ageSpinner.setAdapter(ageAdapter);
        ageAdapter.notifyDataSetChanged();


        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = age.get(position);
                    userAge = ageSpinner.getSelectedItem().toString();
                    //editAge.setText(item);
                    //Toast.makeText(getActivity(), "Age Selected : "+ item, Toast.LENGTH_SHORT).show();

                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        salarySpinner = v.findViewById(R.id.salarySpinner);
        salaryAdapter =  new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                salary
        );
        salaryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        salarySpinner.setAdapter(salaryAdapter);
        salaryAdapter.notifyDataSetChanged();
        salarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userSalary = salarySpinner.getSelectedItem().toString();
               // editSalary.setText(parent.getSelectedItem().toString());
               // Toast.makeText(getActivity(), "Salary Selected : "+ salarySpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((userAge.equals("Select your age")) || (userSalary.equals("Select your salary"))) {
                    Toast.makeText(getActivity(), "Please provide details", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("Age", userAge);
                    editor.putString("Salary", userSalary);
                    editor.commit();
                    Navigation.findNavController(v).navigate(R.id.fragmentBtoC);
                }
            }
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
