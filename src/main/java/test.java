import java.io.IOException;

public class test {


    public static void main(String[] args) throws IOException {

      String test = "mousqueton Mousqueton";
      String t = Gestionhtml.Lastmot(test);
      System.out.println(t);

        String tt = Gestionhtml.Firstmot(test);
        System.out.println(tt);
    }
}
