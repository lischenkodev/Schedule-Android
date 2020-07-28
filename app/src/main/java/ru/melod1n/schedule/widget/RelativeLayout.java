package ru.melod1n.schedule.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ru.melod1n.schedule.common.EventInfo;
import ru.melod1n.schedule.common.ThemeEngine;
import ru.melod1n.schedule.items.ThemeItem;

public class RelativeLayout extends android.widget.RelativeLayout {

    private ThemeItem theme;

    public RelativeLayout(Context context) {
        this(context, null);
    }

    public RelativeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public RelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        theme = ThemeEngine.getCurrentTheme();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceive(EventInfo<ThemeItem> info) {
        String key = info.getKey();
        if (EventInfo.KEY_THEME_UPDATE.equals(key)) {
            theme = info.getData();
            init();
        }
    }

    private void init() {
        setBackgroundColor(theme.getColorBackground());
    }
}
