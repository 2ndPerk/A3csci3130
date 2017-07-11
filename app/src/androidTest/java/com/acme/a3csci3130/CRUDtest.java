package com.acme.a3csci3130;

/**
 * Created by Perk on 2017-07-11.
 */

import org.junit.Test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CRUDtest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void test1Create(){
        onView(withId(R.id.newContactButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("newContact"));
        onView(withId(R.id.busiNum)).perform(typeText("12345"));
        onView(withId(R.id.address)).perform(typeText("bob st"));
        onView(withId(R.id.fisherRadio)).perform(click());
        onView(withId(R.id.provAB)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.newContactButton)).perform(click());

    }

    @Test
    public void test2Update(){
        onView(withText("newContact")).perform(click());
        onView(withId(R.id.address)).perform(typeText("reet"));
        onView(withId(R.id.Fisher)).perform(click());
        onView(withId(R.id.provAB)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());
    }

    @Test
    public void test3CheckData(){
        onView(withText("newContact")).perform(click());
        onView(withId(R.id.name)).check(matches(withText("newContact")));
        onView(withId(R.id.busiNum)).check(matches(withText("12345")));
        onView(withId(R.id.address)).check(matches(withText("bob street")));
    }

    @Test
    public void test4Delete(){
        onView(withText("newContact")).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }

}
