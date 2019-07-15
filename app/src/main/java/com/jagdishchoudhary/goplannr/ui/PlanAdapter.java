package com.jagdishchoudhary.goplannr.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.jagdishchoudhary.goplannr.R;
import com.jagdishchoudhary.goplannr.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private final static String TAG = "Adapter";
    private List<Plan> mPlanList;


    void setPlanList(final List<Plan> planList) {
        mPlanList = planList;
        notifyDataSetChanged();
    }

    public PlanAdapter(List<Plan> mPlanList) {
        this.mPlanList = mPlanList;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_view,parent,false);

        return new PlanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlanViewHolder holder, int position) {
            Plan plan = mPlanList.get(position);
            holder.planName.setText(plan.getName());
            ArrayList<String> featureArry = plan.getFeatures();
            int featureArray = featureArry.size();
            for (int i = 0; i< featureArray; i++){
                holder.features.append(" - "+featureArry.get(i));
                holder.features.append("\n");
            }
        ArrayList<String> hospitalArry = plan.getAvailableHospitals();
        int hospitalArray = hospitalArry.size();
        for (int i = 0; i< hospitalArray; i++){
            holder.hospitals.append(" - "+hospitalArry.get(i));
            holder.hospitals.append("\n");
        }
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.btnMore.getText().equals("Show More")){
                        holder.btnMore.setText("Show Less");
                        holder.layoutMore.setVisibility(View.VISIBLE);
                    }
                    else {
                        holder.btnMore.setText("Show More");
                        holder.layoutMore.setVisibility(View.GONE);
                    }
                }
            });
        holder.cover.setText("Cover : " + plan.getCover());
        holder.price.setText("Pay : "+ (plan.getPrice()) + "per month");


    }

    @Override
    public int getItemCount() {
        return mPlanList == null? 0 : mPlanList.size();
    }


    public class PlanViewHolder extends RecyclerView.ViewHolder{
        private TextView planName, features, hospitals, cover, price;
        private MaterialButton btnMore;
        private LinearLayout layoutMore;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            planName = (TextView)itemView.findViewById(R.id.planName);
            features = (TextView)itemView.findViewById(R.id.featuresText);
            hospitals = (TextView)itemView.findViewById(R.id.hospitalsText);
            btnMore = (MaterialButton) itemView.findViewById(R.id.showMore);
            layoutMore = (LinearLayout)itemView.findViewById(R.id.layoutMore);
            cover = (TextView)itemView.findViewById(R.id.planCover);
            price = (TextView)itemView.findViewById(R.id.planPrice);
        }
    }
}



