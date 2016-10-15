package com.adonisarifi.fybermobileofferapi;

import android.content.Context;
import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.adonisarifi.fybermobileofferapi.ui.activity.InputApiDataActivity;
import com.adonisarifi.fybermobileofferapi.ui.activity.MainActivity;
import com.adonisarifi.fybermobileofferapi.ui.view.RequestContract;
import com.adonisarifi.fybermobileofferapi.ui.view.RequestPresenter;
import com.adonisarifi.fybermobileofferapi.util.Constants;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Rule
    public ActivityTestRule<InputApiDataActivity> inputApiDataActivityActivityTestRule =
            new ActivityTestRule<>(InputApiDataActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule ;

    RequestPresenter requestPresenter;
    RequestContract.View requestContract;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        requestPresenter = new RequestPresenter(appContext, requestContract);
        assertEquals("com.adonisarifi.fybermobileofferapi", appContext.getPackageName());
    }

    @Test
    public void validateInputFiled() {
        String newNoteDescription = "UI testing for Android";
        onView(withId(R.id.edittext_input_uid)).perform(typeText(Constants.UID), closeSoftKeyboard());
        onView(withId(R.id.edittext_input_apikey)).perform(typeText(Constants.API_KEY), closeSoftKeyboard());
        onView(withId(R.id.edittext_input_appid)).perform(typeText(Constants.APP_ID), closeSoftKeyboard());
        onView(withId(R.id.edittext_input_pub0)).perform(typeText(Constants.Pub0), closeSoftKeyboard());
        onView(withId(R.id.button_getoffer)).perform(click());
        // Scroll notes list to added note, by finding its description
        onView(withId(R.id.recycler_view_offers)).perform(RecyclerViewActions.scrollToPosition(10));
        onView(withId(R.id.recycler_view_offers)).perform(click());
        onView(withId(R.id.imageView_dialog_close)).perform(click());


    }

    public static Matcher<View> isDisplayed() {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is displayed on the screen to the user");
            }

            @Override
            public boolean matchesSafely(View view) {
                return view.getGlobalVisibleRect(new Rect())
                        && withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE).matches(view);
            }
        };
    }
}
