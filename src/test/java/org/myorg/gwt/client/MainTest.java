package org.myorg.gwt.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

import org.myorg.gwt.client.rpc.LoginRpcService;
import org.myorg.gwt.client.rpc.LoginRpcServiceAsync;
import org.myorg.gwt.client.utils.TimeBorder;

import org.myorg.gwt.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import org.myorg.gwt.shared.UserDTO;

import java.util.Date;


public class MainTest extends GWTTestCase {

    public String getModuleName() {
        return "org.myorg.gwt.MainJUnit";
    }

    public void testFieldVerifier() {
        assertFalse(FieldVerifier.isValidName(null));
        assertFalse(FieldVerifier.isValidName(""));
        assertFalse(FieldVerifier.isValidName("a"));
        assertFalse(FieldVerifier.isValidName("ab"));
        assertFalse(FieldVerifier.isValidName("abc"));
        assertTrue(FieldVerifier.isValidName("abcd"));
    }

    public  void testTimeBorder(){
        checkTimePeriod("06:00:00.000", TimeBorder.Border.MORNING);
        checkTimePeriod("08:59:59.999", TimeBorder.Border.MORNING);
        checkTimePeriod("09:00:00.000", TimeBorder.Border.DAY);
        checkTimePeriod("18:59:59.999", TimeBorder.Border.DAY);
        checkTimePeriod("19:00:00.000", TimeBorder.Border.EVENING);
        checkTimePeriod("22:59:59.999", TimeBorder.Border.EVENING);
        checkTimePeriod("23:00:00.000", TimeBorder.Border.NIGHT);
        checkTimePeriod("05:59:59.999", TimeBorder.Border.NIGHT);
    }

    private static void checkTimePeriod(String time, TimeBorder.Border timeBorderExpected){
        Date date = DateTimeFormat.getFormat("HH:mm:ss.S").parse(time);
        TimeBorder.Border timeBorder = TimeBorder.getBorder(date);
        assertEquals(timeBorder, timeBorderExpected);
    }

    public void testLoginService() {
        // Create the service that we will test.
        LoginRpcServiceAsync loginService = GWT.create(LoginRpcService.class);
        ServiceDefTarget target = (ServiceDefTarget) loginService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "gwtuser/login");
        // up to 10 seconds before timing out.
        delayTestFinish(10000);
        // Send a request to the server.
        loginService.loginServer("ivan", "secret", new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(UserDTO userDTO) {
                // Verify that the response is correct.
                assertTrue(userDTO.getLoggedIn());
                finishTest();
            }
        });
    }
}
