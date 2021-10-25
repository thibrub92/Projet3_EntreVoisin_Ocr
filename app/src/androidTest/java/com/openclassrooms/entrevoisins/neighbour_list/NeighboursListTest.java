
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }
    @Test
    public void myNeighboursList_AddAction_shouldAddItem(){
        onView(withId(R.id.add_neighbour)).perform(click());

        onView(withId(R.id.name)).perform(typeText("name"));
        onView(withId(R.id.name)).perform(pressImeActionButton());

        onView(withId(R.id.phoneNumber)).perform(typeText("0707070707"));
        onView(withId(R.id.phoneNumber)).perform(pressImeActionButton());

        onView(withId(R.id.address)).perform(typeText("1 rue de rivoli, 75001 Paris"));
        onView(withId(R.id.address)).perform(pressImeActionButton());

        onView(withId(R.id.aboutMe)).perform(typeText("about me"));

        pressBack();

        onView(withId(R.id.create)).perform(click());

        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT +1));

        ITEMS_COUNT = ITEMS_COUNT +1;

    }
    @Test
    public void myNeighboursList_check_favoriteList_removeItem(){
        onView(withId(R.id.item_list_name)).perform(click());

        onView(withId(R.id.favoriteButton)).perform(click());

        pressBack();

        onView(withId(R.id.tabItem2)).check(withItemCount(ITEMS_COUNT +1));

        onView(withId(R.id.item_list_delete_button)).perform(click());

        onView(withId(R.id.list_favorite_neighbours)).check(withItemCount(ITEMS_COUNT -1));

        ITEMS_COUNT = ITEMS_COUNT -1;
    }
}