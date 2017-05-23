package com.example.cier.recycleviewtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by cier on 2017/5/16.
 */
@RunWith(AndroidJUnit4.class)
public class MyTestCase {
    @Rule
    ActivityTestRule<MainActivity> mActivity=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRecyclerView(){
//        onView(withId(R.id.recycleList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()))
    }
}
