package org.myorg.gwt.client;

import org.myorg.gwt.client.rpc.LoginRpcService;
import org.myorg.gwt.client.rpc.LoginRpcServiceAsync;
import org.myorg.gwt.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import org.myorg.gwt.shared.UserDTO;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class GWTUserTest extends GWTTestCase {

    /**
     * Must refer to a valid module that sources this class.
     */
    public String getModuleName() {
        return "org.myorg.gwt.GWTUserJUnit";
    }

    /**
     * Tests the FieldVerifier.
     */
    public void testFieldVerifier() {
        assertFalse(FieldVerifier.isValidName(null));
        assertFalse(FieldVerifier.isValidName(""));
        assertFalse(FieldVerifier.isValidName("a"));
        assertFalse(FieldVerifier.isValidName("ab"));
        assertFalse(FieldVerifier.isValidName("abc"));
        assertTrue(FieldVerifier.isValidName("abcd"));
    }

    /**
     * This test will send a request to the server using the login method in
     * LoginRPCService and verify the response.
     */
    public void testLoginService() {
        // Create the service that we will test.
        LoginRpcServiceAsync loginService = GWT.create(LoginRpcService.class);
        ServiceDefTarget target = (ServiceDefTarget) loginService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "gwtuser/login");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
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
