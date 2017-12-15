/*
 * Henry O'Connor
 */
package texasholdem;

import java.net.Socket;
import org.junit.Test;
import static org.junit.Assert.*;
import static texasholdem.HoldemConstants.PORT_NUMBER;
import static texasholdem.HoldemConstants.SERVER_NAME;

/**
 * Test of Client's network connectivity
 * 
 * @author henoc
 */
public class ClientTest {
    
    public ClientTest() {
    }
    

    /**
     * Tests result of connection attempt
     * 
     * @throws Exception 
     */
    @Test 
    public void testConnectToServer() throws Exception {
            Socket socket = new Socket(SERVER_NAME, PORT_NUMBER);
            assertTrue(socket.isConnected());
    }
}
