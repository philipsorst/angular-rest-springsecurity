package net.dontdrinkandroot.example.angularrestspringsecurity.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TokenUtils.class, MessageDigest.class})
public class TokenUtilsTest {

    private UserDetails userDetails;

    private UserDetails secondUser;

    @Before
    public void setup() {
        //mocking the system class to generate a constant time always
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.currentTimeMillis()).thenReturn(1453210000000L);

        //mocking the userdetails to retuen provided username and password
        userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn("Tom");
        Mockito.when(userDetails.getPassword()).thenReturn("pass");

        secondUser = Mockito.mock(UserDetails.class);
        Mockito.when(secondUser.getUsername()).thenReturn("Peter");
        Mockito.when(secondUser.getPassword()).thenReturn("password");
    }

    @Test
    public void testCreateToken() throws Exception {
        //when
        String token = TokenUtils.createToken(userDetails);

        //then
        assertEquals("Tom:1453213600000:3b9a243b4c39e7da5f3b8de187c25a36", token);
    }

    @Test
    public void testComputeSignature() throws Exception {
        //when
        String signature = TokenUtils.computeSignature(userDetails, 1000);

        //then
        assertEquals("e3a0186e2fe21128fb94f9ee5c97ac4a", signature);
    }

    @Test
    public void testComputeSignature_ForExceptionScenario() throws Exception {
        //given
        PowerMockito.mockStatic(MessageDigest.class);
        PowerMockito.doThrow(new NoSuchAlgorithmException()).when(MessageDigest.class);
        MessageDigest.getInstance("MD5");

        try {
            //when
            TokenUtils.computeSignature(userDetails, 1000);

            //then
            fail("an IllegalArgument Exception  ust have already occurred");
        } catch (IllegalStateException ex) {

            //then
            assertEquals("No MD5 algorithm available!", ex.getMessage());
        }
    }

    @Test
    public void testGetUserNameFromToken() throws Exception {
        //when
        String username = TokenUtils.getUserNameFromToken("Tom:1453273439335:68dcf777ddbaed7b1cecfb0034ec9155");

        //then
        assertEquals("Tom", username);
    }

    @Test
    public void testGetUserNameFromToken_forNullToken() throws Exception {
        //when
        String username = TokenUtils.getUserNameFromToken(null);

        //then
        assertEquals(null, username);
    }

    @Test
    public void testValidateToken() throws Exception {

        assertTrue(TokenUtils.validateToken("Tom:1453273439335:68dcf777ddbaed7b1cecfb0034ec9155", userDetails));

        assertFalse(TokenUtils.validateToken("Tom:1453273439335:68dcf777ddbaed7b1cecfb0034ec9155", secondUser));
    }

    @Test
    public void testValidateToken_forExpiredOnes() throws Exception {
        //given
        String twoHourOneMilliOldToken = "Tom:1453209999999:68dcf777ddbaed7b1cecfb0034ec9155";

        //then
        assertFalse(TokenUtils.validateToken(twoHourOneMilliOldToken, userDetails));
    }
}
