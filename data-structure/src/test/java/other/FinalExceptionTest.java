package other;

import java.nio.charset.CharacterCodingException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public class FinalExceptionTest {

    public static void exception(){
        try {
            new ReentrantLock(false).lock();
            throw new CharacterCodingException();
        }catch (final Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        exception();
    }
}
