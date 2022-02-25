import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class test {


    public static void main(String[] args) throws IOException {

        String test = testEquipement("mousqueton Mousqueton"); // on teste si on décompose bien les 2 Champs de cette chaine de caratère en 2 mot distinct
        System.out.println(test);

        List<String> testMateriel = testEquipementAgent("test"); // on teste si l'on retrouve le bon équipement à partir de l'id d'un agent
        for (String a : testMateriel){
            System.out.println(a);
        }
    }


    public static String testEquipement(String s)
    {

        String t = Gestionhtml.Lastmot(s);
        System.out.println(t);

        String tt = Gestionhtml.Firstmot(s);
        return tt ;
    }

    public static List<String> testEquipementAgent(String s) throws IOException {
        List<String> str = Gestionhtml.decomposeMateriel(s);
        return str;


    }


}
