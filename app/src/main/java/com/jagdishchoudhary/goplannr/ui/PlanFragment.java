package com.jagdishchoudhary.goplannr.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.jagdishchoudhary.goplannr.viewmodel.PlanViewModel;
import com.jagdishchoudhary.goplannr.R;
import com.jagdishchoudhary.goplannr.model.Plan;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<Plan> planList = new ArrayList<>();
    private RecyclerView planRecycler;
    private PlanAdapter planAdapter;
    PlanViewModel planViewModel;
    LiveData<DataSnapshot> liveData;
    private ProgressBar progressBar;

    public PlanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanFragment newInstance(String param1, String param2) {
        PlanFragment fragment = new PlanFragment();
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
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        Context context = getActivity();
        SharedPreferences sp = context.getSharedPreferences("USER_DETAILS", Context.MODE_PRIVATE);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        planRecycler = (RecyclerView)view.findViewById(R.id.planRecycler);
        planViewModel = ViewModelProviders.of(this).get(PlanViewModel.class);
        liveData = planViewModel.getDataSnapshotLiveData();

        final String userAge = sp.getString("Age", "1 years");
        String userSalary = sp.getString("Salary", "1 LPA");
        final int userAgeInt = Integer.parseInt(userAge.substring(0, userAge.length()-6));
        final int userSalaryInt = Integer.parseInt(userSalary.substring(0, userSalary.length()-4));

        liveData.observe(getViewLifecycleOwner(), new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Plan plan = postSnapshot.getValue(Plan.class);
                        if ((userAgeInt < plan.getMaxAge()) && (userAgeInt > plan.getMinAge())){
                            if ((userSalaryInt < plan.getMaxSalary()) && (userSalaryInt > plan.getMinSalary())){
                                planList.add(plan);
                                planAdapter.setPlanList(planList);
                                Toast.makeText(getActivity(), "Here we are", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }

                }
            }


        });
        //Toast.makeText(getActivity(), "List size after: " + Integer.toString(planList.size()), Toast.LENGTH_SHORT).show();
        planAdapter = new PlanAdapter(planList);
        //planAdapter.setPlanList(planList);

        planRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        planRecycler.setItemAnimator(new DefaultItemAnimator());
        planRecycler.setAdapter(planAdapter);

        //planAdapter.notifyDataSetChanged();


        return view;
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
