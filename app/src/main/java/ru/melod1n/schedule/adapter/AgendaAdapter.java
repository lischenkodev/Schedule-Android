package ru.melod1n.schedule.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.melod1n.schedule.R;
import ru.melod1n.schedule.items.HomeworkItem;

public class AgendaAdapter extends RecyclerAdapter<HomeworkItem, AgendaAdapter.ViewHolder> {

    public AgendaAdapter(Fragment f, ArrayList<HomeworkItem> values) {
        super(f, values);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.fragment_agenda_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(position);
    }

    @Override
    public boolean onQueryItem(HomeworkItem item, String lowerQuery) {
        if (item.getTitle().toLowerCase().contains(lowerQuery))
            return true;

        return super.onQueryItem(item, lowerQuery);
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.homework_text)
        TextView homework;

        @BindView(R.id.homework_date)
        TextView date;

        @BindView(R.id.homework_subject)
        TextView subject;

        @BindView(R.id.homework_card)
        MaterialCardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(int position) {
            HomeworkItem item = getItem(position);

            date.setText(item.getDate());
            subject.setText(item.getTitle().replace("\n", " "));

            String homework = item.getText();
            if (homework.length() > 450) {
                homework = homework.substring(0, 446) + "...";
            }

            this.homework.setText(homework);
        }
    }
}