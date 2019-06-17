package de.rvwbk.eit72.bombenentschaerfenapp.overview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.rvwbk.eit72.bombenentschaerfenapp.R;
import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconViewDetail;

import static de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconStatus.ACTIVE;
import static de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconStatus.PASSED;
import static de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconStatus.PENDING;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<BeaconViewDetail> mDataset;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.waypointtext);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaskAdapter(List<BeaconViewDetail> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.waypoint, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // Hier noch die Abfrage welchen Status die verschiedenen Aufgaben haben. (Testweise anhand der Position festgelegt)
        // Abfrage in welchem Status sich eine Aufgabe befindet
        switch (mDataset.get(position).getStatus()) {
            case ACTIVE:
                holder.textView.setText(mDataset.get(position).getHintText());
                break;

            case PASSED:
                holder.itemView.findViewById(R.id.disabled).setActivated(true);
                holder.itemView.findViewById(R.id.timeline).setBackgroundColor(Color.rgb(153, 153, 153));
                holder.itemView.findViewById(R.id.dot).setActivated(true);
                holder.textView.setText(mDataset.get(position).getHintText());
                break;

            case PENDING:
                holder.itemView.findViewById(R.id.timeline).setBackgroundColor(Color.rgb(153, 153, 153));
                holder.itemView.findViewById(R.id.dot).setActivated(true);
                break;
        }
       //  holder.itemView.setEnabled(false);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
