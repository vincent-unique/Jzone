package other;

import java.nio.charset.CharacterCodingException;

/**
 * Created by Vincent on 2017/12/14 0014.
 */
public class FinalExceptionTest {

    public static void exception(){
        try {
            throw new CharacterCodingException();
        }catch (final Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        exception();
    }
}
