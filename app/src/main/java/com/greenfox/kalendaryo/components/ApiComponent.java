package com.greenfox.kalendaryo.components;

import com.greenfox.kalendaryo.ChooseAccountActivity;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.MainActivity;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.WeekViewActivity;
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.InformationAndDeleteActivity;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.fragments.AccountsFragment;
import com.greenfox.kalendaryo.fragments.KalendarFragment;
import com.greenfox.kalendaryo.providers.ApiProvider;
import com.greenfox.kalendaryo.services.AccountService;
import com.greenfox.kalendaryo.services.EventService;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = ApiProvider.class)
@Singleton
public interface ApiComponent {
    void inject(LoginActivity loginActivity);
    void inject(ChooseAccountActivity chooseAccountActivity);
    void inject(SelectCalendarActivity selectCalendarActivity);
    void inject(KalendarFragment kalendarFragment);
    void inject(AccountsFragment accountsFragment);
    void inject(AccountAdapter accountAdapter);
    void inject(AccountService accountService);
    void inject(KalendarAdapter kalendarAdapter);
    void inject(InformationAndDeleteActivity informationAndDeleteActivity);
    void inject(MainActivity mainActivity);
    void inject(WeekViewActivity weekViewActivity);
    void inject(EventService eventService);
}
