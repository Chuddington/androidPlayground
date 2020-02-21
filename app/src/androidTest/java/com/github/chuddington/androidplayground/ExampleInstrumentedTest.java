package com.github.chuddington.androidplayground;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.github.chuddington.androidplayground.junit.AndroidJUnit5Extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@ExtendWith(AndroidJUnit5Extension.class)
class ExampleInstrumentedTest {
    @Test
    void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.github.chuddington.androidplaygroundmk2", appContext.getPackageName());
    }

}
