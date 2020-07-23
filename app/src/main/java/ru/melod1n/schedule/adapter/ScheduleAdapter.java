package ru.melod1n.schedule.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.melod1n.schedule.R;
import ru.melod1n.schedule.common.ThemeEngine;
import ru.melod1n.schedule.current.BaseAdapter;
import ru.melod1n.schedule.current.BaseHolder;
import ru.melod1n.schedule.items.LessonItem;
import ru.melod1n.schedule.items.LocationItem;
import ru.melod1n.schedule.items.SubjectItem;

public class ScheduleAdapter extends BaseAdapter<LessonItem, ScheduleAdapter.ViewHolder> {

    public ScheduleAdapter(Context context, ArrayList<LessonItem> items) {
        super(context, items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {
        View v = getInflater().inflate(R.layout.fragment_schedule_item, vg, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull @NonNull ScheduleAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(position);
    }

    @Override
    public void destroy() {

    }

    class ViewHolder extends BaseHolder {

        @BindView(R.id.lessonType)
        TextView lessonType;

        @BindView(R.id.lessonName)
        TextView lessonName;

        @BindView(R.id.lessonClassroom)
        TextView lessonClassroom;

        @BindView(R.id.lessonStartTime)
        TextView lessonStartTime;

        @BindView(R.id.lessonEndTime)
        TextView lessonEndTime;

        @BindView(R.id.lessonLine)
        View lessonLine;

//        @BindView(R.id.lessonTeacher)
//        TextView lessonTeacher;

        int[] colors = !ThemeEngine.isDark() ? ThemeEngine.COLOR_PALETTE_DARK : ThemeEngine.COLOR_PALETTE_LIGHT;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }

        @Override
        public void bind(final int position) {
            LessonItem item = getItem(position);

            lessonType.setText(String.format(Locale.getDefault(), "%d: %s", position + 1, getType(new Random().nextInt(4))));

            SubjectItem subject = item.getSubject();
            lessonName.setText(subject.getTitle());

            LocationItem location = item.getClassRoom();
            lessonClassroom.setText(String.format("%s, %s", location.getTitle(), location.getBuilding()));

            int color = colors[subject.getColorPosition()];

            lessonLine.setBackgroundColor(color);
            lessonType.setTextColor(color);

//            TeacherItem teacher = item.getTeacher();
//            lessonTeacher.setText(teacher.getTitle());
//            lessonTeacher.setVisibility(View.GONE);

            lessonStartTime.setText("8:00");
            lessonEndTime.setText("8:40");
        }
    }

    private String getType(int i) {
        return i == 0 ? "Лекция" : i == 1 ? "Практика" : i == 2 ? "Лабораторная" : "Дополнительное";
    }
}
