package com.greenfox.kalendaryo;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.greenfox.kalendaryo.models.responses.GoogleCalendarsResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SelectCalendarActivityTest {

    @Rule
    public ActivityTestRule<SelectCalendarActivity> selectCalendarActivityActivityTestRule =
            new ActivityTestRule<>(SelectCalendarActivity.class);


    @Before
    public void setUp() throws Exception{
        Intents.init();
    }

    @Test
    public void selectCalendarAndStepForwardTest() {
        onView(ViewMatchers.withId(R.id.listView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.gotochooseaccount))
                .perform(click());

        selectCalendarActivityActivityTestRule.launchActivity(new Intent());
        intended(hasComponent(ChooseAccountActivity.class.getName()));
    }
}