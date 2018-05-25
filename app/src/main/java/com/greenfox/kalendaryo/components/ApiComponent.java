package com.greenfox.kalendaryo.components;

import com.greenfox.kalendaryo.ChooseAccountActivity;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.fragments.KalendarFragment;
import com.greenfox.kalendaryo.providers.ApiProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by a- on 20/03/2018.
 */

@Component(modules = ApiProvider.class)
@Singleton
public interface ApiComponent {
    void inject(LoginActivity loginActivity);
    void inject(ChooseAccountActivity chooseAccountActivity);
    void inject(SelectCalendarActivity selectCalendarActivity);
    void inject(KalendarFragment kalendarFragment);
    void inject(KalendarAdapter kalendarAdapter);
}
