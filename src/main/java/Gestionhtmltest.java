import org.junit.Assert;
import org.junit.Test;

public class Gestionhtmltest
{

    @Test
    public void testEquipement()
    {
        String t = "mousqueton Mousqueton" ;
        String test = Gestionhtml.Lastmot(t);
        Assert.assertEquals(test, "Mousqueton");
    }

}
